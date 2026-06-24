import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 225 - Implement Stack using Queues
 *
 * 문제: 단방향 큐(Queue 인터페이스)만으로 LIFO 스택 구현.
 *
 * --- 인터페이스 ---
 *   push(x), pop(): int, top(): int, empty(): boolean
 *
 * --- 시간복잡도 목표 ---
 *   push: O(n) (큐 회전)
 *   pop/top/empty: O(1)
 *   ※ Queue using 2 Stacks 와 trade-off (어디서 비용 치를지의 차이)
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
class StackUsingQueue {

    // TODO: 필요한 필드를 선언하세요

    public StackUsingQueue() {
        // TODO: 초기화
    }

    public void push(int x) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public int pop() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public int top() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean empty() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        StackUsingQueue s = new StackUsingQueue();
        s.push(1);
        s.push(2);
        assert s.top() == 2 : "top should be 2";
        assert s.pop() == 2 : "pop should be 2";
        assert s.top() == 1 : "top should be 1";
        assert !s.empty();
        assert s.pop() == 1;
        assert s.empty();

        // push/pop 섞기 — LIFO 순서 검증
        StackUsingQueue s2 = new StackUsingQueue();
        s2.push(1);
        s2.push(2);
        s2.push(3);
        assert s2.pop() == 3;
        s2.push(4);
        assert s2.pop() == 4;
        assert s2.pop() == 2;
        assert s2.pop() == 1;
        assert s2.empty();

        System.out.println("✅ StackUsingQueue: All tests passed");
    }
}
