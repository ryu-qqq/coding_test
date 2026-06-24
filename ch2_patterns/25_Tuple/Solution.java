/**
 * 튜플 (Programmers 64065, LV2, 카카오 2019 인턴)  [정답]
 *
 * 핵심 관찰: 집합 표현에서 원소의 등장 횟수가 곧 튜플 순서다.
 *   크기 1 집합에만 있는 원소가 1등(모든 집합에 등장), 크기 2에서 새로 추가된 게 2등 ...
 *   → 각 원소의 "등장 빈도" 내림차순 = 원래 튜플 순서.
 * 파싱: 정규식 \\{(\\d+(?:,\\d+)*)\\} 으로 집합 단위를 잡고, 그 안 숫자들을 카운트.
 * 복잡도: O(L) 파싱 + O(M log M) 정렬 (M = 서로 다른 원소 수). 자세한 해설 → SOLUTION.md
 */
import java.util.*;
import java.util.regex.*;

class Tuple {

    public int[] solution(String s) {
        // 집합 단위 {숫자,숫자,...} 를 매칭, 그룹 1에 내부 숫자들을 캡처
        Map<Integer, Integer> freq = new HashMap<>();
        Matcher m = Pattern.compile("\\{(\\d+(?:,\\d+)*)\\}").matcher(s);
        while (m.find()) {
            for (String num : m.group(1).split(",")) {
                freq.merge(Integer.parseInt(num), 1, Integer::sum);
            }
        }

        // 등장 빈도 내림차순 정렬 → key 만 추출
        return freq.entrySet().stream()
            .sorted(Comparator.comparingInt(Map.Entry<Integer, Integer>::getValue).reversed())
            .mapToInt(Map.Entry::getKey)
            .toArray();
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
