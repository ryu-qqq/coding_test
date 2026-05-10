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
 */
class QueueUsingTwoStacks {

    private final Deque<Integer> mainQueue;
    private final Deque<Integer> subQueue;



    public QueueUsingTwoStacks() {
        mainQueue = new ArrayDeque<>();
        subQueue = new ArrayDeque<>();
    }

    public void push(int x) {
        mainQueue.push(x);
    }

    public int pop() {
        if(subQueue.isEmpty()){
            while(!mainQueue.isEmpty()){
                Integer val = mainQueue.pop();
                subQueue.push(val);
            }
        }

        return subQueue.pop();
    }

    public int peek() {
        if(subQueue.isEmpty()){
            while(!mainQueue.isEmpty()){
                Integer val = mainQueue.pop();
                subQueue.push(val);
            }
        }

        return subQueue.peek();
    }

    public boolean empty() {
        
        return mainQueue.isEmpty() && subQueue.isEmpty();
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
