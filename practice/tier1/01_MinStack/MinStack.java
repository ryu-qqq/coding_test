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
 *
 * ====================================
 * === 재채점 (2026-05-11, 2회차) ===
 * ====================================
 *
 * --- 이전 채점 항목 반영 현황 ---
 *   ✅ 🔴 getMin() 오타 → minStack.peek() 으로 수정. 빈 가드도 예외로 격상.
 *   ✅ 🔴 push 비교 조건 → `min >= val` 로 등호 포함. 손 시뮬레이션 통과.
 *   ✅ 🟡 push 두 if 통합 → OR 로 합침. (단 아래 #1 참고)
 *   ✅ 🟡 pop 의 불필요 null 가드 제거.
 *   ✅ 🟡 빈 스택 -1 반환 → IllegalStateException 으로 격상. (단 아래 #2 참고)
 *   ⬜ 🟢 음수+중복 복합 테스트는 아직 없음.
 *
 * 🔴 결정적 오류:
 *   (없음 — 표준 풀이와 동치. 손 시뮬레이션 [-2,0,-3] / [1,1,2] 모두 통과)
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   1. push() 의 조건문에 중복 검사가 남아있다. (line 81)
 *        `if(min == null || (min != null && min >= val))`
 *      → 앞 절에서 `min == null` 을 검사한 직후, 뒤 절에서 또 `min != null` 을 검사하고 있다.
 *      → OR 연산의 short-circuit 평가 규칙: 왼쪽이 true 면 오른쪽은 평가 자체가 안 된다.
 *      → 즉, 뒤 절이 평가되는 시점에 min 이 null 일 가능성이 있나? 없다면 `min != null &&` 는 무엇을 막고 있나?
 *      → 한 절 지워도 NPE 안 나는지, 머리로 한 번 따라가봐.
 *
 *   2. getMin() 의 예외 메시지가 "mainStack Empty Status". (line 105)
 *      → 그런데 정작 검사하고 있는 스택은 무엇인가? (line 105 의 isEmpty 체크 대상)
 *      → 메시지가 실제 검사 대상과 다르면, 나중에 면접관/리뷰어가 디버깅할 때 혼란.
 *      → top() 의 메시지는 맞는데 getMin() 만 메시지가 안 맞음. 복붙 흔적.
 *
 * 🟢 개선 가능:
 *
 *   3. 메인 테스트의 복합 케이스 부재 (이전 채점에서 지적, 미반영).
 *      → 예: [-2, -2, -3, -3] push 후 pop 두 번 했을 때 getMin() 은?
 *      → 이번 push 조건 수정이 음수 + 중복 동시에 발생할 때도 안전한지 보장하려면 추가하는 게 좋다.
 *
 *   4. (선택) pop() 안에 빈 줄이 두 개 연속으로 있다. (line 89-90, 95-96)
 *      → 메서드가 짧을 땐 빈 줄로 단락 분리 안 해도 됨. 가독성 ↑.
 *
 * --- 종합 평가 ---
 *   알고리즘 자체는 정답. 면접 통과 가능 수준이다.
 *   남은 건 "코드 위생" — 죽은 조건 (#1) + 메시지 오타 (#2) 두 개만 정리하면 깔끔.
 *
 * --- 다음 단계 ---
 *   1) 위 🟡 #1, #2 한 줄씩 수정 (둘 다 1초 짜리).
 *   2) 🟢 #3 의 음수+중복 테스트 케이스 추가하고 java -ea 로 통과 확인.
 *   3) 통과하면 다음 문제 (02_) 로 넘어가도 됨.
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
        Integer min = minStack.peek();
        if(min == null || min >= val){
            minStack.push(val);
        }
    }

    public void pop() {
        if(mainStack.isEmpty()) return;
        Integer val = mainStack.pop();


        Integer min = minStack.peek();    
        if(min.equals(val)){
            minStack.pop();
        }
    
     
    }

    public int top() {
        if(mainStack.isEmpty()) throw new IllegalStateException("mainStack Empty Status");
        return mainStack.peek();
    }

    public int getMin() {
        if(minStack.isEmpty()) throw new IllegalStateException("minStack Empty Status");
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
