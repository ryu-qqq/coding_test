import java.util.PriorityQueue;
import java.util.*;
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
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class FindMedianFromStream {

    // TODO: 필요한 필드를 선언하세요 (최대 힙 lo, 최소 힙 hi 등)

    public FindMedianFromStream() {
        // TODO: 초기화
    }

    public void addNum(int num) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public double findMedian() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
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
