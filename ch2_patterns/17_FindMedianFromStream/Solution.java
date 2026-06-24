import java.util.PriorityQueue;
import java.util.*;
/**
 * LeetCode 295 - Find Median from Data Stream  [정답]
 *
 * 핵심: 두 힙 균형 — lo(최대 힙, 작은 절반) + hi(최소 힙, 큰 절반)으로 중앙값을 O(1)에 본다.
 * 불변식: lo의 모든 원소 ≤ hi의 모든 원소, 그리고 lo.size == hi.size 또는 lo.size == hi.size + 1.
 * 복잡도: addNum O(log N), findMedian O(1), 공간 O(N).
 * 자세한 해설 → SOLUTION.md
 */
class FindMedianFromStream {

    private final PriorityQueue<Integer> lo;  // 최대 힙
    private final PriorityQueue<Integer> hi;  // 최소 힙

    public FindMedianFromStream() {
        this.lo = new PriorityQueue<>(Comparator.reverseOrder());
        this.hi = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(lo.isEmpty() || num <= lo.peek()){
            lo.offer(num);
        }else{
            hi.offer(num);
        }

        if(lo.size() > hi.size() + 1){
            hi.offer(lo.poll());
        }else if(hi.size() > lo.size()){
            lo.offer(hi.poll());
        }

    }

    public double findMedian() {
        if(lo.size() == hi.size()){
            return (lo.peek() + hi.peek()) / 2.0;
        }
        return lo.peek();
    }

    public static void main(String[] args) {
        FindMedianFromStream m = new FindMedianFromStream();
        m.addNum(1);
        m.addNum(2);
        assert Math.abs(m.findMedian() - 1.5) < 1e-9 : "median 1.5";
        m.addNum(3);
        assert Math.abs(m.findMedian() - 2.0) < 1e-9 : "median 2";

        // 단일 값
        FindMedianFromStream m2 = new FindMedianFromStream();
        m2.addNum(5);
        assert Math.abs(m2.findMedian() - 5.0) < 1e-9 : "single";

        // 음수 / 큰 값
        FindMedianFromStream m3 = new FindMedianFromStream();
        m3.addNum(-1);
        m3.addNum(-2);
        m3.addNum(-3);
        assert Math.abs(m3.findMedian() - (-2.0)) < 1e-9 : "negatives median";

        System.out.println("✅ FindMedianFromStream: All tests passed");
    }
}
