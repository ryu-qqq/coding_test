import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 225 - Implement Stack using Queues  [정답]
 *
 * 핵심: push 후 큐를 (size-1)번 회전시켜 새 원소를 맨 앞(=peek 위치)으로 보낸다 → LIFO 성립.
 * 복잡도: push O(N), pop/top/empty O(1), 공간 O(N).
 * 함정: 회전 횟수는 반드시 size-1. size로 돌면 원래 자리로 돌아온다.
 * 자세한 해설 → SOLUTION.md
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
