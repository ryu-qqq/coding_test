import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 347 - Top K Frequent Elements
 *
 * 문제: 정수 배열에서 빈도 상위 K개를 (임의 순서로) 반환한다.
 *
 * --- 인터페이스 ---
 *   int[] topKFrequent(int[] nums, int k)
 *
 * --- 시간복잡도 목표 ---
 *   해시 + 힙: O(N log K)
 *   해시 + 버킷 정렬: O(N)  (인덱스 = 빈도)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class TopKFrequent {

    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
        }
    }

    private static java.util.Set<Integer> setOf(int[] arr) {
        java.util.Set<Integer> s = new java.util.HashSet<>();
        for (int v : arr) s.add(v);
        return s;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] r1 = sol.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        assert r1.length == 2 : "size 2";
        assert setOf(r1).equals(java.util.Set.of(1, 2)) : "top2 = {1,2}";

        int[] r2 = sol.topKFrequent(new int[]{1}, 1);
        assert r2.length == 1 && r2[0] == 1 : "single";

        int[] r3 = sol.topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2);
        assert setOf(r3).equals(java.util.Set.of(-1, 2)) : "top2 = {-1,2}";

        // 사용하지 않더라도 import 검증 (PriorityQueue는 빈 상태로 둔다)
        Map<Integer, Integer> _u = new HashMap<>();
        PriorityQueue<int[]> _q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        _u.put(0, 0); assert _q.isEmpty();

        System.out.println("✅ TopKFrequent: All tests passed");
    }
}
