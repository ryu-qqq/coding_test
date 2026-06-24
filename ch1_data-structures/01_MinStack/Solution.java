import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 155 - Min Stack  [정답]
 *
 * 핵심: 보조 스택(minStack)의 top이 항상 "현재 살아있는 원소들의 최솟값"이도록 유지.
 * 복잡도: 모든 연산 O(1), 공간 O(N).
 * 자세한 해설 → SOLUTION.md
 */
class MinStack {

    private final Deque<Integer> mainStack;
    private final Deque<Integer> minStack;

    public MinStack() {
        mainStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        mainStack.push(val);
        // 함정: 반드시 <= (중복 최솟값 처리), 결합은 || (첫 push 누락 방지)
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        Integer pop = mainStack.pop();
        // 함정: == 가 아닌 equals() (Integer 박싱 캐시 범위 밖 주의)
        if (pop.equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack s = new MinStack();
        s.push(-2);
        s.push(0);
        s.push(-3);
        assert s.getMin() == -3 : "getMin should be -3";
        s.pop();
        assert s.top() == 0 : "top should be 0";
        assert s.getMin() == -2 : "getMin should be -2";

        MinStack s2 = new MinStack();
        s2.push(1);
        s2.push(1);
        s2.push(2);
        assert s2.getMin() == 1;
        s2.pop();
        assert s2.getMin() == 1;
        s2.pop();
        assert s2.getMin() == 1;

        System.out.println("✅ MinStack: All tests passed");
    }
}
