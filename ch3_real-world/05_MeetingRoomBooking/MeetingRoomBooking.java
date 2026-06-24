import java.util.*;

/**
 * 미팅룸 예약 (구간 겹침, 실무 설계형)
 *
 * 문제: 여러 회의실 중 그 시간에 비어있는 방 하나에 [start, end) 구간을 예약한다.
 *       모든 방이 그 시간과 겹치면 거절(-1). 시간은 당일 "HH:MM".
 *
 * --- 인터페이스 ---
 *   MeetingRoomBooking(int roomCount)
 *   int book(String start, String end)   // 배정된 방 번호(0-based), 모두 겹치면 -1
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것 — 두 구간 [s1,e1), [s2,e2) 가 겹치는 조건은?)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것 — 반열린 구간, 끝=시작 경계, 동시간 같은 방 중복 배정)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class MeetingRoomBooking {

    // TODO: 필요한 필드를 선언하세요 (방별 예약 구간들)

    public MeetingRoomBooking(int roomCount) {
        // TODO: 초기화 (roomCount 개의 빈 방)
        throw new UnsupportedOperationException("TODO");
    }

    public int book(String start, String end) {
        // TODO: 구현 (빈 방 찾아 배정하고 방 번호 반환, 없으면 -1)
        throw new UnsupportedOperationException("TODO");
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
