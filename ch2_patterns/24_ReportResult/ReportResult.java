/**
 * 신고 결과 받기 (Programmers 92334, LV1)
 *
 * 문제: 유저가 서로 신고. 한 명을 여러 번 신고해도 1회. k회 이상 신고당하면 정지.
 *       정지된 유저를 신고한 사람들은 메일 1통씩 받는다. 유저별 받은 메일 수를 반환.
 * 입력: id_list(유저 목록), report("신고자 피신고자" 문자열들), k(임계값)
 * 예: id=["muzi","frodo","apeach","neo"],
 *     report=["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"], k=2 → [2,1,1,0]
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것: 중복 신고를 어떤 자료구조로 1회 처리할까? 2-pass?)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
import java.util.*;

class ReportResult {

    public int[] solution(String[] id_list, String[] report, int k) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        ReportResult s = new ReportResult();

        String[] id1 = {"muzi", "frodo", "apeach", "neo"};
        String[] r1 = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        assert Arrays.equals(s.solution(id1, r1, 2), new int[]{2, 1, 1, 0});

        String[] id2 = {"con", "ryan"};
        String[] r2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        assert Arrays.equals(s.solution(id2, r2, 3), new int[]{0, 0});

        System.out.println("✅ ReportResult: All tests passed");
    }
}
