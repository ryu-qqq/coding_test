/**
 * 튜플 (Programmers 64065, LV2, 카카오 2019 인턴)
 *
 * 문제: 집합으로 표현된 튜플 문자열에서 원래 튜플 (a1,...,an) 을 복원.
 *   "{{2},{2,1},{2,1,3},{2,1,3,4}}" → [2, 1, 3, 4]
 *   집합들의 순서는 뒤섞일 수 있다.
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것: 각 원소가 "몇 개의 집합에 등장하는가" 와 튜플 순서의 관계는?)
 *
 * --- 파싱 / 정규식 포인트 ---
 *   (스스로 떠올려볼 것: 집합 단위 {..} 를 어떻게 잡아낼까?)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
import java.util.*;
import java.util.regex.*;

class Tuple {

    public int[] solution(String s) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        Tuple sol = new Tuple();

        assert Arrays.equals(sol.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"), new int[]{2, 1, 3, 4});
        assert Arrays.equals(sol.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"), new int[]{2, 1, 3, 4});
        assert Arrays.equals(sol.solution("{{20,111},{111}}"), new int[]{111, 20});
        assert Arrays.equals(sol.solution("{{123}}"), new int[]{123});
        assert Arrays.equals(sol.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"), new int[]{3, 2, 4, 1});

        System.out.println("✅ Tuple: All tests passed");
    }
}
