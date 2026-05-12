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
 *    최악의 시나리오 경우 pop() 시 mainStack의 n개에 대한 원소를 substack으로 옮겨 최악 O(n) 이 걸린다
 *    다만 평균은 왜 O(1) 이냐면 원소는 이런 라이프사이클을 겪는데  메인 푸시 -> 메인 팝 -> 서브 푸시 -> 서브 팝
 *    원소 하나당 총 4개의 라이프사이클을 겪는다 그래서 n개의 원소를 넣고빼는데 4n이므로 4n/n = 4 -> O(1) 이다 
 *    
 *    
 *   
 *   
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * ====================================
 * === 재채점 (2026-05-11, 3회차) ===
 * ====================================
 *
 * --- 실행 결과 ---
 *   ✅ java -ea 통과: "✅ QueueUsingTwoStacks: All tests passed"
 *
 * --- 이전 채점 항목 반영 현황 ---
 *   ✅ 🔴 transferIfNeeded 가드 위치 → `empty()` 로 교체. 알고리즘 정상화.
 *   ✅ 🟢 amortized 분석 본인 말로 채움 (아래 평가).
 *
 * 🔴 결정적 오류:
 *   (없음 — 테스트 통과 확인)
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   1. 예외 메시지가 여전히 "mainStack is Empty". (line 120)
 *      → 정작 가드는 `empty()` 즉 "둘 다 비었을 때" 를 검사 중.
 *      → 메시지가 검사 대상과 어긋남. "queue is empty" 로 바꾸는 게 정확.
 *      → 자잘하지만 MinStack 의 메시지 오타 지적과 같은 결.
 *
 * 🟢 amortized 분석 평가 — 결론부터: **그 말 맞다. 면접에서 그대로 해도 통과.**
 *
 *   본인이 적은 것:
 *     "최악의 경우 pop() 시 mainStack 의 n 개 원소를 substack 으로 옮겨 최악 O(n).
 *      다만 평균이 O(1) 인 이유는 원소 하나가 [main push → main pop → sub push → sub pop]
 *      4개의 라이프사이클을 겪기 때문. n 개 원소 처리에 4n, 4n/n = 4 → O(1)."
 *
 *   체크리스트:
 *     ✅ worst case 가 O(n) 인 이유 명시 (transfer 비용).
 *     ✅ "왜 평균은 O(1)" 의 핵심 통찰 (각 원소가 정확히 4회의 스택 연산).
 *     ✅ 4n / n = 4 라는 산수까지. 상수가 떨어지므로 O(1).
 *     ✅ 본인 표현 — 외운 게 아니라 이해한 거 보임.
 *
 *   딱 한 가지 다듬을 점 (선택):
 *     "4n / n" 에서 분모의 n 이 무엇의 n 인지 한 번 더 명시하면 더 정확.
 *       → "n 번의 큐 연산 (push n 번 + pop n 번 = 2n 번의 큐 연산 / 또는 pop n 번만 셀 때)".
 *     하지만 면접에서는 굳이 거기까지 안 가도 됨. "총 작업이 4n, 연산 횟수로 나누면 상수" 라고만 해도 충분.
 *
 *   고수 버전 (선택, 면접관이 더 깊이 물으면):
 *     - amortized 분석 기법 이름: "aggregate analysis" 또는 "accounting method".
 *     - "각 push 시점에 transfer 비용을 미리 저금해놓는다" (accounting 비유) — 신용카드 비유랑 같음.
 *
 * --- 종합 평가 ---
 *   알고리즘 ✅, 리팩토링 ✅, amortized 분석 ✅.
 *   남은 건 예외 메시지 오타 1개. 이거 고치면 끝.
 *
 * --- 다음 단계 ---
 *   1) 🟡 #1 메시지를 "queue is empty" 로 변경.
 *   2) 다음 문제 (03_) 로 넘어가도 됨.
 */
class QueueUsingTwoStacks {

    private final Deque<Integer> mainStack;
    private final Deque<Integer> subStack;


    public QueueUsingTwoStacks() {
        mainStack = new ArrayDeque<>();
        subStack = new ArrayDeque<>();
    }

    public void push(int x) {
        mainStack.push(x);
    }

    public int pop() {
        transferIfNeeded();
        return subStack.pop();
    }

    public int peek() {
        transferIfNeeded();
        return subStack.peek();
    }

    private void transferIfNeeded(){
        if(empty()) throw new IllegalStateException("mainStack is Empty");

        if(subStack.isEmpty()){
            while(!mainStack.isEmpty()){
                subStack.push(mainStack.pop());
            }
        }
    }

    public boolean empty() {
        return mainStack.isEmpty() && subStack.isEmpty();
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
