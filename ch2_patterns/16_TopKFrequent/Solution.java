import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 347 - Top K Frequent Elements  [정답]
 *
 * 핵심: 빈도 카운트 후 크기 K인 min-heap(빈도 기준)에 키를 넣어 빈도 낮은 것부터 잘라낸다.
 * 불변식: 매 반복 후 heap에는 지금까지 본 값 중 빈도 상위 ≤ K개만 남는다.
 * 복잡도: 시간 O(N log K), 공간 O(N).
 * 자세한 해설 → SOLUTION.md
 */
class TopKFrequent {

    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for(int n : nums){
                map.put(n, map.getOrDefault(n,0 ) + 1);
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>(
                (a, b) -> map.get(a) - map.get(b)
            );

            for(int key : map.keySet()){
                heap.add(key);
                if(heap.size() > k) heap.poll();
            }

            int[] results = new int[k];

            for(int i =0; i < k; i ++){
                results[i] = heap.poll();
            }


            return results;
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
