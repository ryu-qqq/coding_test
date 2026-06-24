/**
 * [3차] 압축 (Programmers 17684, LV2, 카카오 2018)
 *
 * 문제: LZW 단순화. 사전에 A~Z(1~26) 등록 후, 매 단계 사전에 있는 가장 긴 접두사 w 를 찾아
 *   그 번호를 출력하고, (w + 다음 글자) 를 사전 다음 번호로 등록. 입력이 빌 때까지 반복.
 *   "KAKAO" → [11, 1, 27, 15]
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것: 사전을 어떤 자료구조로? "가장 긴 접두사" 는 어떻게 찾을까?)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
import java.util.*;

class Compression {

    public int[] solution(String msg) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        Compression s = new Compression();

        assert Arrays.equals(s.solution("KAKAO"), new int[]{11, 1, 27, 15});
        assert Arrays.equals(s.solution("TOBEORNOTTOBEORTOBEORNOT"),
            new int[]{20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34});
        assert Arrays.equals(s.solution("ABABABABABABABAB"), new int[]{1, 2, 27, 29, 28, 31, 30});

        System.out.println("✅ Compression: All tests passed");
    }
}
