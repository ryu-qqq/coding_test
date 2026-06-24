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
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class KthLargest {

    // TODO: 필요한 필드를 선언하세요 (k, 힙 등)

    public KthLargest(int k, int[] nums) {
        // TODO: 초기화
    }

    public int add(int val) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
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
