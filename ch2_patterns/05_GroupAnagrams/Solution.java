import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode 49 - Group Anagrams  [정답]
 *
 * 핵심: 문자열을 정렬한 결과를 키로 삼아 같은 키끼리 묶는다.
 * 불변식: 매 반복 후 map[key]의 모든 문자열은 서로 애너그램.
 * 함정: 빈 문자열도 그 자체로 그룹 1개, 그룹/내부 순서는 임의 허용.
 * 복잡도: 정렬 키 방식 O(N·K log K), 공간 O(N·K). K = 평균 문자열 길이.
 * 자세한 해설 → SOLUTION.md
 */
class GroupAnagrams {

    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> map = new HashMap<>();

            for(String s : strs){
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                String word = new String(chars);

                map.computeIfAbsent(word, w -> new ArrayList<>()).add(s);
            }

            return new ArrayList<>(map.values());
        }
    }

    private static int totalSize(List<List<String>> g) {
        int total = 0;
        for (List<String> sub : g) total += sub.size();
        return total;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        List<List<String>> r1 = sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        // 그룹 3개, 원소 총합 6
        assert r1.size() == 3 : "should be 3 groups";
        assert totalSize(r1) == 6 : "total 6 strings";

        List<List<String>> r2 = sol.groupAnagrams(new String[]{""});
        assert r2.size() == 1 && r2.get(0).size() == 1 : "single empty";

        List<List<String>> r3 = sol.groupAnagrams(new String[]{"a"});
        assert r3.size() == 1 && r3.get(0).get(0).equals("a") : "single char";

        List<List<String>> r4 = sol.groupAnagrams(new String[]{});
        assert r4.isEmpty() : "empty input";

        System.out.println("✅ GroupAnagrams: All tests passed");
    }
}
