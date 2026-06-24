import java.util.*;

/**
 * 미팅룸 예약 (구간 겹침, 실무 설계형)  [정답]
 *
 * 문제: 여러 회의실 중 그 시간에 비어있는 방 하나에 [start, end) 구간을 예약한다.
 *       모든 방이 그 시간과 겹치면 거절(-1). 시간은 당일 "HH:MM".
 *
 * 핵심 아이디어: 방마다 예약 구간 리스트를 두고, 구간 [s1,e1)와 [s2,e2)는
 *   s1 < e2 && s2 < e1 일 때 겹친다(반열린 구간이라 끝=시작은 안 겹침).
 *   가장 작은 번호의 빈 방부터 배정.
 * 복잡도: book 은 O(방 수 × 방당 예약 수).
 *
 * 설계 포인트:
 *   - 시간 파싱(toMinutes)과 방 배정 정책(overlaps / 빈 방 탐색)을 분리.
 *     시간 표현이 바뀌어도(예: epoch millis) 겹침/배정 로직은 그대로.
 * 자세한 해설 → SOLUTION.md
 */
class MeetingRoomBooking {

    // 방 번호 → 그 방에 잡힌 예약 구간들 [start, end)
    private final List<List<int[]>> rooms;

    public MeetingRoomBooking(int roomCount) {
        rooms = new ArrayList<>();
        for (int i = 0; i < roomCount; i++) {
            rooms.add(new ArrayList<>());
        }
    }

    // 빈 방에 예약 → 배정된 방 번호(0-based). 모든 방이 겹치면 -1.
    public int book(String start, String end) {
        int s = toMinutes(start);
        int e = toMinutes(end);

        for (int room = 0; room < rooms.size(); room++) {
            if (isFree(room, s, e)) {
                rooms.get(room).add(new int[]{s, e});
                return room;
            }
        }
        return -1;
    }

    // 해당 방이 [s, e) 구간에 비어있는가
    private boolean isFree(int room, int s, int e) {
        for (int[] r : rooms.get(room)) {
            if (overlaps(s, e, r[0], r[1])) {
                return false;
            }
        }
        return true;
    }

    // 반열린 구간 겹침 판정: [s1,e1) 와 [s2,e2)
    private boolean overlaps(int s1, int e1, int s2, int e2) {
        return s1 < e2 && s2 < e1;
    }

    // "HH:MM" → 분 단위 int
    private int toMinutes(String t) {
        return Integer.parseInt(t.substring(0, 2)) * 60 + Integer.parseInt(t.substring(3, 5));
    }

    public static void main(String[] args) {
        // 방 2개
        MeetingRoomBooking b = new MeetingRoomBooking(2);

        // 첫 예약 → 0번 방
        assert b.book("09:00", "10:00") == 0 : "첫 예약은 0번 방";

        // 겹치는 예약 → 0번이 차 있으니 1번 방
        assert b.book("09:30", "10:30") == 1 : "겹치면 다음 빈 방(1번)";

        // 둘 다 그 시간에 겹침 → 거절 -1
        assert b.book("09:15", "09:45") == -1 : "모든 방 겹치면 -1";

        // 경계: [09:00,10:00) 와 [10:00,11:00) 은 끝=시작이라 안 겹침 → 0번 방 재사용
        assert b.book("10:00", "11:00") == 0 : "끝=시작은 안 겹침 → 0번 재사용";

        // 다른 시간대는 0번에 또 들어감
        assert b.book("11:00", "12:00") == 0 : "이어지는 구간 0번";

        // 방 1개일 때 겹침 → 바로 -1
        MeetingRoomBooking single = new MeetingRoomBooking(1);
        assert single.book("13:00", "14:00") == 0 : "단일 방 첫 예약";
        assert single.book("13:30", "14:30") == -1 : "단일 방 겹침 거절";
        assert single.book("14:00", "15:00") == 0 : "단일 방 경계 인접 OK";

        // 방 0개면 항상 -1
        MeetingRoomBooking none = new MeetingRoomBooking(0);
        assert none.book("09:00", "10:00") == -1 : "방 없으면 -1";

        // 자정 직전 경계 파싱
        MeetingRoomBooking edge = new MeetingRoomBooking(1);
        assert edge.book("23:00", "23:59") == 0 : "늦은 시간 파싱";

        System.out.println("✅ MeetingRoomBooking: All tests passed");
    }
}
