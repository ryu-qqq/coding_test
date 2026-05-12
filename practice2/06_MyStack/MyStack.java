import java.util.ArrayDeque;
import java.util.Queue;

/**
 * ═══════════════════════════════════════════════════════════
 *  Problem: Implement Stack using Queues  (LC 225)
 * ═══════════════════════════════════════════════════════════
 *
 * Implement a LIFO stack using only Queue operations (offer, poll, peek, size, isEmpty).
 * 단방향 Queue 인터페이스만 사용. (`ArrayDeque` 의 stack 메서드 사용 금지)
 *
 * --- Function Description ---
 *
 *   class MyStack {
 *     MyStack()
 *     void    push(int x)        // push to top
 *     int     pop()              // remove and return top
 *     int     top()              // return top without removing
 *     boolean empty()
 *   }
 *
 * --- Constraints ---
 *
 *   1 <= number of operations <= 100
 *   pop() / top() are only called when stack is non-empty.
 *
 * --- Sample Operations ---
 *
 *   push(1)
 *   push(2)
 *   top()      → 2
 *   pop()      → 2
 *   top()      → 1
 *   empty()    → false
 *   pop()      → 1
 *   empty()    → true
 *
 * --- Time Complexity Target ---
 *
 *   push:  O(n)
 *   pop / top / empty:  O(1)
 *   ※ "stack-using-2-queues" 와 trade-off — 거기는 push O(1), pop O(n). 어디서 비용 치를지의 선택.
 *
 * --- Hints (스스로 떠올려볼 것) ---
 *
 *   [1-Queue 회전 트릭] — 권장
 *     • offer(x) 한 뒤, 앞쪽 (size-1) 개를 차례로 poll → offer 로 뒤로 보냄.
 *     • 결과: 방금 넣은 x 가 큐의 맨 앞 (head).
 *     • 그 다음부터 pop/top 은 그냥 poll/peek.
 *
 *   [2-Queue 방식 — 면접 follow-up 대비]
 *     (a) push-heavy: 보조 큐에 x 먼저 → mainQ 의 모든 원소를 보조로 → swap.
 *     (b) pop-heavy:  mainQ 에 그냥 쌓다가 pop 시 size-1 만큼 보조로 옮기고 마지막 1 개 poll.
 *     1-큐 방식이 메모리도 적고 더 깔끔.
 *
 *   루프 함정:
 *     for (int i = 0; i < queue.size() - 1; i++)   // size() 가 매 반복마다 호출됨
 *       → poll 1 + offer 1 = size 변화 없음 → 안전. 그래도 size 를 캐시하면 의도가 더 명확.
 */
class MyStack {

    private final Queue<Integer> q;

    public MyStack() {
        q = new ArrayDeque<>();
    }

    // ───────────────────────────────────────────
    // push: offer 후 (size-1) 번 회전
    // ───────────────────────────────────────────
    public void push(int x) {
        q.offer(x);
        int size = q.size();

        for(int i =0; i < size - 1; i ++){
            Integer val = q.poll();
            q.offer(val);
        }
    }

    // ───────────────────────────────────────────
    // pop: O(1)
    // ───────────────────────────────────────────
    public int pop() {
        if(q.isEmpty()) return -1;
        return q.poll();
    }

    // ───────────────────────────────────────────
    // top: O(1)
    // ───────────────────────────────────────────
    public int top() {
        if(q.isEmpty()) return -1;
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }


    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(1);
        s.push(2);
        assert s.top() == 2 : "top should be 2";
        assert s.pop() == 2 : "pop should be 2";
        assert s.top() == 1 : "top should be 1";
        assert !s.empty();
        assert s.pop() == 1;
        assert s.empty();

        // push/pop 섞기 — LIFO 순서 검증
        MyStack s2 = new MyStack();
        s2.push(1);
        s2.push(2);
        s2.push(3);
        assert s2.pop() == 3;
        s2.push(4);
        assert s2.pop() == 4;
        assert s2.pop() == 2;
        assert s2.pop() == 1;
        assert s2.empty();

        // 단일 원소 시퀀스
        MyStack s3 = new MyStack();
        s3.push(42);
        assert s3.top() == 42;
        assert s3.pop() == 42;
        assert s3.empty();

        System.out.println("✅ MyStack: All tests passed");
    }
}
