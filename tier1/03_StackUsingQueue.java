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
 */
class StackUsingQueue {

    private final Queue<Integer> mainQueue;

    public StackUsingQueue() {
        mainQueue = new ArrayDeque<>();
    }

    public void push(int x) {
        mainQueue.add(x);
        for(int i =0; i< mainQueue.size()- 1; i ++){
            Integer val  = mainQueue.poll();
            mainQueue.add(val);
        }
    }

    public int pop() {
        if(mainQueue.isEmpty()) return 0;
        return mainQueue.poll();
    }

    public int top() {
        if(mainQueue.isEmpty()) return 0;
        return mainQueue.peek();
    }

    public boolean empty() {
        return mainQueue.isEmpty();
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
