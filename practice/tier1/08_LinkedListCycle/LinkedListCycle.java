/**
 * LeetCode 141 - Linked List Cycle
 *
 * 문제: 연결 리스트에 사이클이 존재하는지 판별한다.
 *
 * --- 인터페이스 ---
 *   boolean hasCycle(ListNode head)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(1)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
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
 *   ❌ AssertionError "no cycle" at line 60. 사이클 없는 리스트인데 true 반환.
 *
 * --- 가장 큰 문제: 알고리즘 자체가 사이클을 검출하지 못한다 ---
 *
 *   너 코드:
 *       ListNode cur = head;
 *       while(head.next != null){
 *           if(cur.equals(head)) return true;
 *           head = head.next;
 *       }
 *       return false;
 *
 *   손 시뮬레이션 (사이클 없음: 1 → 2):
 *     초기: cur = head = node(1)
 *     iter 1: head.next != null (node2) → 진입
 *             cur.equals(head)? → node(1).equals(node(1)) → **true** → return true ❌
 *
 *   문제: cur 과 head 는 처음부터 같은 노드. 그래서 첫 비교에서 항상 true.
 *
 *   너 의도를 추측하면 "head 가 움직이다가 처음 자리 (cur) 로 돌아오면 사이클" 인 것 같은데,
 *   그렇다 해도:
 *     1) 진입 직후 (head 가 안 움직였을 때) 비교하면 무조건 같음 → 즉시 true 반환.
 *     2) 만약 cur.equals(head) 가 head 이동 후로 옮겨가도, 사이클이 첫 노드를 안 거치면 못 잡음.
 *        예: 1 → 2 → 3 → 2 (3 의 next 가 2 로 돌아가는 사이클). head=1 은 사이클에 안 포함됨.
 *        head 가 2, 3, 2, 3, ... 무한 순환하지만 head==cur(=1) 은 절대 안 됨. 무한 루프.
 *
 *   또한 null 입력 시:
 *     head=null → while head.next → 💥 NPE
 *
 * --- 핵심 통찰 (직접 답 X) ---
 *
 *   "사이클이 있다" 의 정의: 어딘가에서 next 를 따라가다 보면 **이미 방문한 노드로 돌아온다**.
 *
 *   해결 방법 두 가지:
 *
 *   [방법 1] 방문 기록 (Set 사용) — 직관적이지만 공간 O(N)
 *     - HashSet<ListNode> visited 만들고, 순회하며 이미 들어있으면 사이클.
 *     - 시간 O(N), 공간 O(N). 면접에선 "1차 답" 으로 OK.
 *
 *   [방법 2] **Floyd 의 토끼와 거북이 (Two Pointers)** — 공간 O(1) 의 정답 답안
 *     - slow 는 한 칸씩, fast 는 두 칸씩 전진.
 *     - 사이클이 있으면 fast 가 slow 를 따라잡는다 (원형 트랙에서 빠른 사람이 결국 한 바퀴 더 돌아 만남).
 *     - 사이클이 없으면 fast 가 먼저 null 에 닿는다.
 *
 *   비유 — 트랙 위의 두 주자:
 *     - 사이클 있는 트랙: 빠른 주자가 결국 느린 주자를 추월하기 직전에 같은 자리에 도착.
 *     - 일직선 트랙: 빠른 주자가 결승선(null) 을 먼저 통과. 만날 일 없음.
 *
 * --- Floyd 패턴 스켈레톤 ---
 *
 *   public boolean hasCycle(ListNode head) {
 *       ListNode slow = head;
 *       ListNode fast = head;
 *       while (fast != null && fast.next != null) {   // fast 가 두 칸 가야 하니 null 검사 2단계
 *           slow = slow.next;
 *           fast = fast.next.next;
 *           if (slow == fast) return true;
 *       }
 *       return false;
 *   }
 *
 *   주의:
 *   - while 조건이 **fast != null && fast.next != null** 이다. 두 조건 모두 필요.
 *     - fast 가 null 이면 사이클 없음 (null 도달).
 *     - fast.next 가 null 이면 fast.next.next 가 NPE.
 *   - 비교는 `slow == fast` (객체 참조 동일성 — equals 가 아니라!).
 *     ※ ListNode 가 equals 를 오버라이드 안 했으면 == 와 동일하지만, 의미상 == 가 더 명확.
 *
 * --- 손 시뮬레이션: 사이클 있음 (a → b → c → d → b) ---
 *   초기: slow=a, fast=a
 *   iter 1: slow=b, fast=c. slow!=fast.
 *   iter 2: slow=c, fast=b (d.next=b 라 fast 가 d->b 로). slow!=fast.
 *   iter 3: slow=d, fast=d (b.next=c, c.next=d → fast 가 c->d 로). slow==fast → return true ✓
 *
 * --- 손 시뮬레이션: 사이클 없음 (1 → 2) ---
 *   초기: slow=1, fast=1
 *   iter 1: slow=2, fast=null (1.next=2, 2.next=null → fast.next.next = null). slow!=fast.
 *   iter 2: while 조건 fast!=null 위반 → 종료. return false ✓
 *
 * --- 손 시뮬레이션: 자기 자신 사이클 (g → g) ---
 *   초기: slow=g, fast=g
 *   iter 1: slow=g.next=g, fast=g.next.next=g. slow==fast → return true ✓
 *
 * 🔴 결정적 오류:
 *
 *   1. cur 과 head 가 같은 자리에서 시작, 첫 비교에서 무조건 true. (line 31, 34)
 *      → 사이클 없는 리스트도 즉시 true 반환.
 *
 *   2. 두 포인터 속도 차이가 없다. (line 31-39)
 *      → cur 은 안 움직이고 head 만 움직임. 단일 포인터 순회.
 *      → 사이클 검출의 핵심은 "다른 속도로 가는 두 포인터" — 이 개념이 빠짐.
 *
 *   3. null 입력 NPE. (line 33)
 *      → `while(head.next != null)` 가 head=null 일 때 폭발.
 *      → Floyd 패턴은 `fast != null && fast.next != null` 로 자연스럽게 처리됨.
 *
 *   4. cur.equals(head) 대신 == 가 더 명확. (line 34)
 *      → ListNode 의 equals 오버라이드가 없으면 Object.equals 가 호출되고 그건 == 와 동일.
 *      → 다만 의미상 "같은 노드 인가" 는 == (참조 동일성) 으로 표현하는 게 정석.
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   5. 단일 노드 + 자기 사이클 (g.next=g):
 *      → Floyd 로도 잘 잡힘 (위 손 시뮬레이션 참고). while 첫 iter 만에 slow==fast.
 *
 *   6. 길이 2 + 자기 사이클 (1 → 2 → 1):
 *      → slow 가 한 바퀴 도는 동안 fast 는 두 바퀴 → 어디선가 만남. 사이클 검출 ✓.
 *
 * 🟢 개선 가능:
 *
 *   7. Floyd 알고리즘 이름 외워두기 — 면접에서 "이 알고리즘 이름이 뭔지" 물을 수 있음.
 *      → "Floyd's Cycle Detection" 또는 "Tortoise and Hare".
 *
 * --- 다음 단계 ---
 *   1) 두 포인터 (slow/fast) 의 의미를 종이에 적기.
 *   2) Floyd 패턴 스켈레톤을 본인 표현으로 다시 짜기 (답 보지 말고).
 *   3) 세 케이스 손 시뮬레이션 모두 검증.
 *   4) java -ea ... 통과 확인.
 */
class LinkedListCycle {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static class Solution {
        public boolean hasCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;


            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast) return true;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 사이클 있음: 3 -> 2 -> 0 -> -4 -> (2)
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(-4);
        a.next = b; b.next = c; c.next = d; d.next = b;
        assert sol.hasCycle(a) : "should detect cycle";

        // 사이클 없음: 1 -> 2
        ListNode e = new ListNode(1);
        ListNode f = new ListNode(2);
        e.next = f;
        assert !sol.hasCycle(e) : "no cycle";

        // null
        assert !sol.hasCycle(null) : "null head";

        // 자기 자신을 가리키는 사이클
        ListNode g = new ListNode(1);
        g.next = g;
        assert sol.hasCycle(g) : "self loop";

        System.out.println("✅ LinkedListCycle: All tests passed");
    }
}
