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
 *   각 원소는 inStack에 1번 push, outStack으로 1번 이동, outStack에서 1번 pop
 *   = 원소당 최대 3번의 연산. 따라서 평균 O(1).
 *
 * --- 함정 ---
 *   pop 할 때마다 in→out 이동을 하면 안 됨 (outStack이 비었을 때만!)
 *   그렇지 않으면 순서가 망가진다.
 */
class QueueUsingTwoStacks {

    public QueueUsingTwoStacks() {
        // TODO: 자료구조 초기화
    }

    public void push(int x) {
        // TODO
    }

    public int pop() {
        // TODO
        return 0;
    }

    public int peek() {
        // TODO
        return 0;
    }

    public boolean empty() {
        // TODO
        return true;
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
