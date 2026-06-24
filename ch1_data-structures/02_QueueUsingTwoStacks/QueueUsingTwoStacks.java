import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 232 - Implement Queue using Stacks
 *
 * 문제: 두 개의 스택만으로 FIFO 큐 구현
 *
 * --- 인터페이스 ---
 *   push(x), pop(): int, peek(): int, empty(): boolean
 *
 * --- 시간복잡도 목표 ---
 *   push: O(1)
 *   pop/peek: amortized O(1)  ← 핵심 면접 포인트
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- Amortized 분석 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class QueueUsingTwoStacks {

    // TODO: 필요한 필드를 선언하세요

    public QueueUsingTwoStacks() {
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

    public int peek() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean empty() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        QueueUsingTwoStacks q = new QueueUsingTwoStacks();
        q.push(1);
        q.push(2);
        assert q.peek() == 1 : "peek should be 1";
        assert q.pop() == 1  : "pop should be 1";
        assert !q.empty();
        assert q.pop() == 2;
        assert q.empty();

        // 섞어서 호출해도 순서가 유지되는지
        QueueUsingTwoStacks q2 = new QueueUsingTwoStacks();
        q2.push(1);
        q2.push(2);
        assert q2.pop() == 1;
        q2.push(3);
        q2.push(4);
        assert q2.pop() == 2;
        assert q2.pop() == 3;
        assert q2.pop() == 4;
        assert q2.empty();

        System.out.println("✅ QueueUsingTwoStacks: All tests passed");
    }
}
