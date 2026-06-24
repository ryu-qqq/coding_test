import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 232 - Implement Queue using Stacks  [정답]
 *
 * 핵심: subQueue(out)가 비었을 때만 mainQueue(in)를 통째로 옮긴다 → 옮기며 역순이 되어 FIFO 성립.
 * 복잡도: push O(1), pop/peek amortized O(1) (각 원소는 평생 최대 3번 연산만 받음).
 * 함정: 매 pop마다 옮기면 순서가 깨짐. 반드시 out이 비었을 때만 옮긴다.
 * 자세한 해설 → SOLUTION.md
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
