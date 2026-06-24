/**
 * 오픈채팅방 (Programmers 42888, LV2, 카카오 2019 블라인드)  [정답]
 *
 * 핵심: 닉네임 변경이 과거 메시지까지 소급 적용되므로, 모든 record 를 먼저 훑어
 *       uid → 최종 닉네임 매핑을 확정(1-pass)한 뒤, 다시 훑으며 Enter/Leave 만
 *       "최종 닉"으로 메시지를 만든다(2-pass).
 * 자료구조: Map<String,String> (uid → 최종 nick), List<String> (출력).
 * 복잡도: record N개에 대해 O(N). 자세한 해설 → SOLUTION.md
 */
import java.util.*;

class OpenChatRoom {

    public String[] solution(String[] record) {
        // 1-pass: uid → 최종 닉네임. Enter/Change 는 닉을 덮어쓴다.
        Map<String, String> nick = new HashMap<>();
        for (String line : record) {
            String[] t = line.split(" ");
            if (t[0].equals("Enter") || t[0].equals("Change")) {
                nick.put(t[1], t[2]);
            }
        }

        // 2-pass: Enter/Leave 만 최종 닉으로 메시지 생성. Change 는 출력 없음.
        List<String> out = new ArrayList<>();
        for (String line : record) {
            String[] t = line.split(" ");
            if (t[0].equals("Enter")) {
                out.add(nick.get(t[1]) + "님이 들어왔습니다.");
            } else if (t[0].equals("Leave")) {
                out.add(nick.get(t[1]) + "님이 나갔습니다.");
            }
        }

        return out.toArray(new String[0]);
    }

    public static void main(String[] args) {
        OpenChatRoom s = new OpenChatRoom();

        String[] record = {
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"
        };

        String[] expected = {
            "Prodo님이 들어왔습니다.",
            "Ryan님이 들어왔습니다.",
            "Prodo님이 나갔습니다.",
            "Prodo님이 들어왔습니다."
        };
        assert Arrays.equals(s.solution(record), expected);

        System.out.println("✅ OpenChatRoom: All tests passed");
    }
}
