import java.util.PriorityQueue;

/**
 * LeetCode 703 - Kth Largest Element in a Stream  [정답]
 *
 * 핵심: 크기 K인 min-heap을 유지하면 top이 곧 K번째로 큰 값.
 * 불변식: heap에는 지금까지 본 값 중 가장 큰 K개가 있고, top은 그 중 최솟값(=K번째 큰 값).
 * 복잡도: 생성 O(N log K), add O(log K), 공간 O(K).
 * 자세한 해설 → SOLUTION.md
 */
class KthLargest {

    private final int k;
    private final PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();
        for(int i : nums){
            add(i);
        }
    }

    public int add(int val) {
        heap.offer(val);
        if(heap.size() > k) heap.poll();
        return heap.peek();
    }

    public static void main(String[] args) {
        // k = 3, 초기 [4, 5, 8, 2]
        KthLargest kth = new KthLargest(3, new int[]{4, 5, 8, 2});
        assert kth.add(3) == 4 : "after add 3, third largest = 4";
        assert kth.add(5) == 5 : "after add 5, third largest = 5";
        assert kth.add(10) == 5 : "after add 10, third largest = 5";
        assert kth.add(9) == 8 : "after add 9, third largest = 8";
        assert kth.add(4) == 8 : "after add 4, third largest = 8";

        // 빈 초기 배열
        KthLargest k2 = new KthLargest(1, new int[]{});
        assert k2.add(-3) == -3 : "single element";
        assert k2.add(-2) == -2 : "after add -2, top = -2";

        System.out.println("✅ KthLargest: All tests passed");
    }
}
