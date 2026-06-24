/**
 * [3차] 압축 (Programmers 17684, LV2, 카카오 2018)  [정답]
 *
 * 핵심: LZW. 사전에 A~Z(1~26) 를 미리 등록하고, 매 단계마다 사전에 있는 "가장 긴 접두사 w" 를
 *       찾아 그 번호를 출력하고, (w + 다음 글자) 를 사전 다음 번호로 등록한다.
 * 최장 접두사 찾기: 길이를 1씩 늘리며 사전에 있는 동안 확장(점진 확장).
 * 복잡도: 글자당 상수 횟수의 사전 조회, O(N) 수준. 자세한 해설 → SOLUTION.md
 */
import java.util.*;

class Compression {

    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            dict.put(String.valueOf(c), c - 'A' + 1);
        }
        int next = 27;  // 다음 사전 번호

        List<Integer> out = new ArrayList<>();
        int i = 0;
        while (i < msg.length()) {
            // 사전에 있는 가장 긴 접두사 w = msg[i..j) 찾기
            int j = i + 1;
            while (j <= msg.length() && dict.containsKey(msg.substring(i, j))) {
                j++;
            }
            j--;  // 마지막으로 사전에 있었던 길이
            String w = msg.substring(i, j);
            out.add(dict.get(w));

            // (w + 다음 글자) 를 사전에 등록 (다음 글자가 있을 때만)
            if (j < msg.length()) {
                dict.put(msg.substring(i, j + 1), next++);
            }
            i = j;  // w 만큼 진행
        }

        return out.stream().mapToInt(Integer::intValue).toArray();
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
