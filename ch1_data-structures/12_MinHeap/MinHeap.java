import java.util.Arrays;
import java.util.List;

/**
 * Min Heap (Priority Queue) 직접 구현
 *
 * 문제: 배열 기반 이진 힙 구현 (Java PriorityQueue 사용 금지)
 *
 * --- 인터페이스 ---
 *   insert(val)
 *   extractMin(): int
 *   peek(): int
 *   size(): int
 *
 * --- 시간복잡도 목표 ---
 *   insert: O(log n)
 *   extractMin: O(log n)
 *   peek: O(1)
 *   buildHeap (배치): O(n)  (도전 과제)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
**/

public class MinHeap {

    // TODO: 필요한 필드를 선언하세요 (배열, size, capacity 등)
    private static final int DEFAULT_SIZE = 16;

    public MinHeap(){
        // TODO: 초기화
    }

    public void insert(int val){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public int extractMin(){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    private void siftUp(int i){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    private void siftDown(int i){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public int peek(){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public int size(){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }


    public static void main(String[] args) {
        MinHeap h = new MinHeap();
        h.insert(5);
        h.insert(3);
        h.insert(8);
        h.insert(1);
        h.insert(7);

        assert h.peek() == 1 : "min should be 1";
        assert h.size() == 5 : "size should be 5";

        assert h.extractMin() == 1;
        assert h.extractMin() == 3;
        assert h.extractMin() == 5;
        assert h.extractMin() == 7;
        assert h.extractMin() == 8;
        assert h.size() == 0;

        // grow 테스트 (capacity=16 넘김)
        MinHeap big = new MinHeap();
        for (int i = 100; i >= 1; i--) big.insert(i);
        for (int i = 1; i <= 100; i++) {
            assert big.extractMin() == i : "should pop in order";
        }

        System.out.println("✅ MinHeap: All tests passed");
    }


}
