import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 155 - Min Stack
 *
 * 문제: getMin()이 O(1)인 스택 구현
 *
 * --- 인터페이스 ---
 *   push(val), pop(), top(), getMin()
 *
 * --- 시간복잡도 목표 ---
 *   모든 연산 O(1)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   메인 스택 외에 보조 스택(minStack)을 둔다.
 *   minStack의 top은 "현재 시점에서의 최솟값"을 항상 가리킨다.
 *
 *   push(val):
 *     mainStack.push(val)
 *     if minStack 비었거나 val <= minStack.top():
 *       minStack.push(val)
 *
 *   pop():
 *     popped = mainStack.pop()
 *     if popped == minStack.top():
 *       minStack.pop()
 *
 *   top():     return mainStack.top()
 *   getMin():  return minStack.top()
 *
 * --- 불변식 ---
 *   minStack.top()은 항상 mainStack에 현재 살아있는 모든 원소의 최솟값.
 *
 * --- 엣지 케이스 ---
 *   - 같은 값이 중복 push 될 때 minStack에도 같이 들어가야 pop 시 짝이 맞음
 *     (그래서 push 조건이 < 가 아니라 <= )
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

        if(minStack.isEmpty() || val <= minStack.peek()){
            minStack.push(val);
        }
    }

    public void pop() {
        Integer pop = mainStack.pop();

        if(pop.equals(minStack.peek())){
            minStack.pop();
        }
    }

    public int top() {
        if(!mainStack.isEmpty()){
            return mainStack.peek();
        }
        return 0;
    }

    public int getMin() {
        if(!minStack.isEmpty()){
            return minStack.peek();
        }
        return 0;
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

        // 중복 값 엣지 케이스
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
