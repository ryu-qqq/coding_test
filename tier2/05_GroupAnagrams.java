import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 49 - Group Anagrams
 *
 * 문제: 문자열 배열을 받아 서로 애너그램끼리 그룹핑한다.
 *
 * --- 인터페이스 ---
 *   List<List<String>> groupAnagrams(String[] strs)
 *
 * --- 시간복잡도 목표 ---
 *   정렬 키 방식: O(N * K log K), K = 평균 문자열 길이
 *   카운트 키 방식: O(N * K)
 *   공간 O(N * K)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   같은 애너그램은 정렬한 결과가 같다는 점을 이용.
 *
 *   map = {}                   // key(String) → list<String>
 *   for s in strs:
 *     key = sorted(s)          // 또는 길이 26 카운트 배열을 문자열화
 *     map[key].add(s)
 *   return map.values()
 *
 *   대안 (소문자만 가정 시):
 *     int[26] count; "#a#b#..." 같은 형태로 직렬화해 키로 사용 → O(K).
 *
 * --- 불변식 ---
 *   매 반복 후 map[key]에 들어 있는 문자열들은 모두 서로 애너그램.
 *
 * --- 함정 ---
 *   - 빈 문자열도 그 자체로 하나의 그룹.
 *   - 결과 그룹의 순서나 그룹 내부 순서는 LeetCode에선 임의로 허용.
 */
class GroupAnagrams {

    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            // TODO: 정렬한 키 또는 26-length 카운트 키로 그룹핑
            return new ArrayList<>();
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
