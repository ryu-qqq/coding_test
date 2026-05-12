import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ═══════════════════════════════════════════════════════════
 *  Problem: Implement Queue using Stacks  (LC 232)
 * ═══════════════════════════════════════════════════════════
 *
 * Implement a FIFO queue using only two LIFO stacks.
 * `java.util.Queue`, `LinkedList` 큐 사용 금지. 단 `Deque`(스택 용도) 는 OK.
 *
 * --- Function Description ---
 *
 *   class MyQueue {
 *     MyQueue()
 *     void    push(int x)         // push to the back of queue
 *     int     pop()               // remove and return front
 *     int     peek()              // return front without removing
 *     boolean empty()             // true if empty
 *   }
 *
 * --- Constraints ---
 *
 *   1 <= number of operations <= 10^5
 *   pop() / peek() are only called when queue is non-empty.
 *
 * --- Sample Operations ---
 *
 *   push(1)
 *   push(2)
 *   peek()     → 1
 *   pop()      → 1
 *   empty()    → false
 *   pop()      → 2
 *   empty()    → true
 *
 * --- Time Complexity Target ---
 *
 *   push:  O(1)
 *   pop / peek:  amortized O(1)   ← 면접 단골 follow-up
 *   empty:  O(1)
 *
 * --- Hints (스스로 떠올려볼 것) ---
 *
 *   • mainStack 에 push, subStack 에서 pop/peek.
 *   • pop/peek 호출 시:
 *       - subStack 이 비어있으면 mainStack 의 모든 원소를 subStack 으로 옮긴다.
 *       - subStack 이 비어있지 않으면 그냥 subStack 에서 꺼냄.
 *   • 옮기면 순서가 뒤집힘 (LIFO → LIFO 통해 결국 FIFO).
 *   • amortized O(1) 의 직관:
 *       각 원소는 평생 main push, main pop, sub push, sub pop = 총 4회 스택 연산.
 *       n 개 원소 → 총 4n. 큐 연산당 평균 4 = O(1).
 */
class MyQueue {

    private final Deque<Integer> mainStack;   // push 전용
    private final Deque<Integer> subStack;    // pop/peek 전용

    public MyQueue() {
        mainStack = new ArrayDeque<>();
        subStack  = new ArrayDeque<>();
    }

    // ───────────────────────────────────────────
    // push: mainStack 에 그대로
    // ───────────────────────────────────────────
    public void push(int x) {
        mainStack.offer(x);
    }

    // ───────────────────────────────────────────
    // pop: subStack 준비 후 pop
    // ───────────────────────────────────────────
    public int pop() {
        transferIfNeeded();
        return subStack.pop();
    }

    // ───────────────────────────────────────────
    // peek: subStack 준비 후 peek
    // ───────────────────────────────────────────
    public int peek() {
        transferIfNeeded();
        return subStack.peek();
    }

    public boolean empty() {
        return mainStack.isEmpty() && subStack.isEmpty();
    }

    // ── helper (sub 가 비었으면 main 의 전체를 옮김) ──
    private void transferIfNeeded() {
        if(!mainStack.isEmpty()){
            Integer val = mainStack.poll();
            subStack.offer(val);
        }
    }


    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        assert q.peek() == 1 : "peek should be 1";
        assert q.pop()  == 1 : "pop should be 1";
        assert !q.empty();
        assert q.pop() == 2;
        assert q.empty();

        // push/pop 섞기 (FIFO 순서)
        MyQueue q2 = new MyQueue();
        q2.push(1);
        q2.push(2);
        assert q2.pop() == 1;
        q2.push(3);
        q2.push(4);
        assert q2.pop() == 2;
        assert q2.pop() == 3;
        assert q2.pop() == 4;
        assert q2.empty();

        // 단일 원소 반복
        MyQueue q3 = new MyQueue();
        for (int i = 1; i <= 5; i++) q3.push(i);
        for (int i = 1; i <= 5; i++) assert q3.pop() == i : "ordered " + i;
        assert q3.empty();

        System.out.println("✅ MyQueue: All tests passed");
    }
}
