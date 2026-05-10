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
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 엣지 케이스 ---
 *   (스스로 떠올려볼 것)
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
        if(minStack.isEmpty() || minStack.peek() >= val){
            minStack.push(val);
        }
    }

    public void pop() {
        if(!mainStack.isEmpty()){
            Integer val = mainStack.pop();
            if(val.equals(minStack.peek())){
                minStack.pop();
            }
        }
    }

    public int top() {
        if(mainStack.isEmpty()){
            return 0;
        }
        return mainStack.peek();
    }

    public int getMin() {
        if(minStack.isEmpty()){
            return 0;
        }
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
