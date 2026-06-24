/**
 * 오픈채팅방 (Programmers 42888, LV2, 카카오 2019 블라인드)
 *
 * 문제: 채팅방 로그 처리. "Enter uid nick"(입장), "Leave uid"(퇴장), "Change uid nick"(닉변경, 출력없음).
 *   ⚠️ 닉네임 변경은 과거 메시지까지 소급 적용 — 모든 메시지는 그 사람의 "최종 닉"으로 출력.
 *   각 줄 처리 후 출력될 메시지 배열을 순서대로 반환.
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것: 최종 닉을 어떻게 확정하고 메시지를 만들까? 한 번에 될까, 두 번 훑어야 할까?)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
import java.util.*;

class OpenChatRoom {

    public String[] solution(String[] record) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
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
