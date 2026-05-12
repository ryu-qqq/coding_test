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
 *   하나의 큐에 푸시를 할때
 *   1
 *   1 2 
 *   스책은 2 를 뽑아야함 -> 회전시킨다
 *   
 * 큐 두개를 쓸 경우
 *   푸시 헤비
 *   서브 큐에 넣는다    (1)
 *   메인 큐가 빌때까지 서브큐에 모두 넣는다 (1 , 2, 3.... )
 *   그리고 메인큐와 서브큐를 바꾼다.
 *   
 *   그러면 메인큐는 꽉차있고 서브큐는 비어있는데 
 *   pop, peek 은 메인큐기준으로하면 먼저 넣은놈 먼저 뽑을 수 있으니 O(1) 만족
 *
 *   팝 헤비 
 *   메인 큐에 계속 쌓는다
 *   팝 또는 픽이 오면
 *   메인큐가 하나 남을때까지 서브에 넣는다 
 *   메인 -> ( 1, 2, 3) -> (3)
 *   서브 -> (1, 2)   
 *   그리고 메인에서 pop, peek 을 한다
 *   그리고 메인과 서브를 바꾼다 
 *  
 * 
 * 
 * 
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * ====================================
 * === 채점 결과 (2026-05-11) ===
 * ====================================
 *
 * --- 실행 결과 ---
 *   ✅ java -ea 통과: "✅ StackUsingQueue: All tests passed"
 *
 * 🔴 결정적 오류:
 *   (없음 — 회전 트릭 정확. 손 시뮬레이션 [1,2,3] push 후 다양한 pop 시퀀스 모두 통과)
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   1. push() 의 루프 조건 `i < queue.size() - 1` 에서 size() 가 매 반복마다 호출된다. (line 40)
 *      → 루프 안에서 poll 1회 + offer 1회 = size 는 변하지 않으므로 결과는 정확.
 *      → 다만 "size 가 안 변한다" 라는 사실은 한 번 사고를 거쳐야 보임. 면접관이 "이거 무한루프 아니에요?" 물을 수 있음.
 *      → size 를 루프 들어가기 전에 캐시 (`int n = queue.size() - 1;`) 하면 의도가 더 명확.
 *      → 이건 정답성 문제가 아니라 "읽는 사람을 위한 명시성" 문제.
 *
 *   2. 빈 큐에서 push(1) 호출 시 첫 push 의 회전 횟수는? (line 40)
 *      → size=1 일 때 size-1 = 0. 회전 0번. 안전.
 *      → 사고 거쳐서 "엣지에서 안 깨진다" 는 거 확신할 수 있어야.
 *
 * 🟢 개선 가능:
 *
 *   3. 회전 루프 본문이 두 줄. (line 41-42)
 *        Integer val = queue.poll();
 *        queue.offer(val);
 *      → 임시 변수 val 없이 한 줄로 가능: `queue.offer(queue.poll());`
 *      → Integer 박싱 1회 + 변수 선언 비용 절약. 가독성도 더 직관적 ("poll 한 걸 그대로 offer").
 *
 *   4. "--- 핵심 아이디어 ---" 섹션이 사고 흔적만 남아있다. (line 17-22)
 *      → 본인이 도달한 결론 ("새 원소를 큐 앞으로 회전시킨다") 을 한 줄로 정리해두면 외울 때 도움.
 *      → 예: "새 원소를 enqueue 한 뒤 앞쪽 (size-1) 개를 차례로 뒤로 옮겨, 새 원소가 큐의 head 가 되게 한다."
 *
 *   5. trade-off 한 줄 추가 권장 (면접 단골 follow-up).
 *      → 02번 (Queue using 2 Stacks) 와 비교: 거기는 amortized O(1), 여기는 push 가 worst case O(n).
 *      → "어디서 비용을 치를지의 선택" 이라는 한 줄이 핵심 아이디어 섹션에 있으면 좋음.
 *
 *   6. (선택) 2-큐 방식도 알고는 있어야 함.
 *      → "main 큐 + 보조 큐" 로 푸시 시 보조에 넣고 main 을 보조에 옮긴 뒤 swap.
 *      → 1-큐 회전 방식 (현재) 이 메모리도 적고 더 깔끔. 면접관이 "다른 방법은?" 물으면 답할 수 있어야.
 *      
 *
 * --- 종합 평가 ---
 *   알고리즘 정확, 코드 깔끔, 빈 가드까지 일관성 있음.
 *   "정답" 으로 끝내도 되지만, 🟢 #3 한 줄 정리 + #4-#5 docstring 채우기는 면접 가산점.
 *
 * --- 다음 단계 ---
 *   1) 🟢 #3 한 줄로 합쳐서 푸시 메서드 깔끔하게.
 *   2) 🟢 #4-#5 docstring 의 핵심 아이디어 / trade-off 한 줄 정리.
 *   3) 다음 문제 (04_) 로.
 */
class StackUsingQueue {

    private final Queue<Integer> queue;

    public StackUsingQueue() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        queue.offer(x);
        int size = queue.size() - 1 ;
        for(int i =0; i <size; i ++){
            Integer val  = queue.poll();
            queue.offer(val);
        }
    }

    public int pop() {
        if(empty()) throw new IllegalStateException("queue is Empty");
        return queue.poll();
    }

    public int top() {
        if(empty()) throw new IllegalStateException("queue is Empty");
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
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
