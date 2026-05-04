import java.util.PriorityQueue;

/**
 * LeetCode 295 - Find Median from Data Stream
 *
 * 문제: 정수가 스트림으로 들어오는 자료구조. 매 호출 시 현재까지의 중앙값 반환.
 *
 * --- 인터페이스 ---
 *   void addNum(int num)
 *   double findMedian()
 *
 * --- 시간복잡도 목표 ---
 *   addNum    : O(log N)
 *   findMedian: O(1)
 *   공간       : O(N)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   - lo의 모든 원소 ≤ hi의 모든 원소.
 *   - lo.size() == hi.size() (짝수개) 또는 lo.size() == hi.size()+1 (홀수개).
 *
 * --- 함정 ---
 *   - PriorityQueue는 기본 min-heap → 최대 힙은 Comparator.reverseOrder() 또는 (a,b) -> b-a.
 *   - 정수 합 / 2 시 오버플로우 주의 → double로 캐스팅.
 *   - findMedian이 비어있는 경우는 LC에서 호출되지 않음.
 */
class FindMedianFromStream {

    private final PriorityQueue<Integer> lo;  // 최대 힙
    private final PriorityQueue<Integer> hi;  // 최소 힙

    public FindMedianFromStream() {
        this.lo = new PriorityQueue<>((a, b) -> b - a);
        this.hi = new PriorityQueue<>();
        // TODO: 필요시 추가 초기화
    }

    public void addNum(int num) {
        // TODO
    }

    public double findMedian() {
        // TODO
        return 0.0;
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
