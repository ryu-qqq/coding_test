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
 * --- 핵심 아이디어 (수도코드) ---
 *   1) 빈도 카운트:
 *        freq = HashMap<Integer, Integer>
 *        for v in nums: freq[v]++
 *
 *   2) 해시 + 최소 힙(O(N log K)):
 *        heap = min-heap by frequency, size limit = k
 *        for (val, cnt) in freq:
 *          heap.offer((cnt, val))
 *          if heap.size() > k: heap.poll()
 *        결과 = heap의 val들
 *
 *   3) 버킷 정렬 (O(N)):
 *        buckets[i] = "i번 등장한 값들의 리스트", i in 0..N
 *        뒤(=빈도 큰 쪽)부터 K개 채워서 반환.
 *
 * --- 불변식 ---
 *   힙 방식: 매 반복 후 heap에는 "현재까지 본 값들 중 빈도 상위 ≤ K개"가 들어 있다.
 *
 * --- 함정 ---
 *   - k가 distinct 개수보다 작거나 같음을 가정. (LC 보장)
 *   - 정렬 풀이는 O(N log N)이라 목표 시간복잡도 미달일 수 있음.
 */
class TopKFrequent {

    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // TODO: HashMap + min-heap (또는 bucket sort)
            return new int[0];
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
