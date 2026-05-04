import java.util.PriorityQueue;

/**
 * LeetCode 703 - Kth Largest Element in a Stream
 * (LC 215의 스트림 변형 버전)
 *
 * 문제: 정수 스트림에서 매 add 호출마다 현재까지 들어온 값 중 K번째로 큰 값을 반환한다.
 *
 * --- 인터페이스 ---
 *   KthLargest(int k, int[] nums)
 *   int add(int val)
 *
 * --- 시간복잡도 목표 ---
 *   생성: O(N log K)
 *   add : O(log K)
 *   공간 : O(K)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   add 종료 직후 heap.size() == min(k, 지금까지 들어온 원소 수).
 *   힙에는 "현재 시점에서 가장 큰 K개의 값"이 들어 있다.
 *
 * --- 함정 ---
 *   - max-heap을 쓰고 매번 K-1개를 빼는 방식은 비효율(O(K log N)).
 *   - 초기 nums에 K보다 적은 값이 들어올 수도 있음 → 호출 시 size 체크 필요.
 *   - PriorityQueue는 기본이 min-heap (자연 순서) 임을 기억.
 */
class KthLargest {

    private final int k;
    private final PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();
        // TODO: nums의 모든 값을 add 한다.
    }

    public int add(int val) {
        // TODO
        return -1;
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
