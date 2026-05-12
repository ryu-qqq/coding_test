/**
 * LeetCode 19 - Remove Nth Node From End of List
 *
 * 문제: 연결 리스트의 끝에서 N번째 노드를 제거한다.
 *
 * --- 인터페이스 ---
 *   ListNode removeNthFromEnd(ListNode head, int n)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(L) (한 번의 패스), 공간 O(1)
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
 *   ❌ NullPointerException at line 40. 첫 테스트부터 사망.
 *
 * --- 두 가지 큰 문제 ---
 *
 *   [문제 1] dummy.next = head 가 빠졌다. (line 32-33)
 *     - dummy 노드만 만들고 dummy.next = head 로 연결 안 했다.
 *     - 그래서 dummy.next = null. for 루프 들어가자마자 cur = cur.next = null → NPE.
 *
 *   [문제 2] 알고리즘이 "끝에서 N번째" 가 아니라 "앞에서 N번째" 로 동작하려 한다.
 *     - 너 코드는 n-1 번 전진 후 cur.next.next 로 자른다.
 *     - 이건 사실상 "앞에서 n 번째 노드를 자른다" 의 패턴.
 *     - 이 문제는 "끝에서 N번째" 라서 다른 접근이 필요하다.
 *
 *     "끝에서 N번째" 를 한 번의 패스로 찾으려면:
 *       리스트 길이를 모르므로 단순히 "앞에서 (length-N) 번째" 로 변환할 수 없다 (= 2-pass 필요).
 *       1-pass 로 하려면 **두 포인터 + 간격 N** 트릭을 써야 한다.
 *
 * --- 핵심 통찰: 두 포인터 + 간격 ---
 *
 *   비유: 막대 두 개를 한쪽 끝에서 N칸 떨어뜨려 잡고, 같이 평행 이동시킨다.
 *     - 앞 막대 (fast) 가 끝 (null) 에 닿는 순간,
 *     - 뒤 막대 (slow) 는 정확히 "끝에서 N번째" 자리에 있다.
 *
 *   왜? 두 막대 사이 간격이 N 이니까, fast 가 끝에 닿을 때 slow 의 위치는 fast - N.
 *   즉, fast = end, slow = end - N → slow 는 "끝에서 N번째 노드".
 *
 *   다만 우리는 **자르려면 "직전 노드"** 가 필요하다 (cur.next = cur.next.next 패턴).
 *   그러려면 slow 가 "끝에서 N+1번째" 에 있어야 한다.
 *   해결: fast 를 **N+1 칸** 먼저 보내고 시작 (또는 fast 가 null 직전에 멈추도록 조정).
 *
 * --- 표준 패턴 (1-pass, dummy + two-pointer) ---
 *
 *   ListNode dummy = new ListNode(0);
 *   dummy.next = head;                       // ← 너 코드에 빠진 핵심 한 줄
 *   ListNode slow = dummy;
 *   ListNode fast = dummy;
 *
 *   // fast 를 N+1 칸 앞으로
 *   for (int i = 0; i <= n; i++) fast = fast.next;
 *
 *   // 두 포인터 같이 전진 (fast 가 null 될 때까지)
 *   while (fast != null) {
 *       slow = slow.next;
 *       fast = fast.next;
 *   }
 *
 *   // 이제 slow.next 가 "끝에서 N번째 노드". 잘라낸다.
 *   slow.next = slow.next.next;
 *
 *   return dummy.next;   // ← 너 코드는 cur.next 반환 (잘못)
 *
 *   왜 dummy 가 필요한가?
 *     - head 자체가 삭제 대상이 될 수 있다 (예: [1], n=1 → [], [1,2], n=2 → [2]).
 *     - dummy 를 두면 "head 의 직전 노드" 가 항상 존재 → 모든 경우 동일하게 처리.
 *
 * --- 손 시뮬레이션 (1→2→3→4→5, n=2) ---
 *   dummy → 1 → 2 → 3 → 4 → 5 → null
 *   초기: slow = fast = dummy
 *   fast 를 n+1=3 칸 전진:
 *     fast = dummy.next = 1
 *     fast = 1.next = 2
 *     fast = 2.next = 3
 *   같이 전진 (fast != null 동안):
 *     slow=dummy→1, fast=3→4
 *     slow=1→2,    fast=4→5
 *     slow=2→3,    fast=5→null
 *   while 종료. slow=3. slow.next = 4 (끝에서 2번째 = 자를 노드).
 *   slow.next = slow.next.next → 3.next = 5
 *   결과: 1 → 2 → 3 → 5 ✓
 *
 * --- 손 시뮬레이션 (head 삭제 케이스, [1,2], n=2) ---
 *   dummy → 1 → 2 → null
 *   초기: slow=fast=dummy
 *   fast 를 3 칸 전진:
 *     fast=1
 *     fast=2
 *     fast=null  ← 정확히 null. 그래서 0 번 더 전진.
 *   while 안 들어감 (fast=null).
 *   slow=dummy. slow.next = 1 (= 자를 노드).
 *   slow.next = slow.next.next → dummy.next = 2
 *   결과: dummy.next = 2 ✓ (head 가 1 → 2 로 바뀐다)
 *
 *   ※ dummy 가 없으면 head 자체를 수정해야 해서 코드가 분기로 복잡해진다. dummy 의 효용.
 *
 * 🔴 결정적 오류:
 *
 *   1. dummy.next = head 누락. (line 32-33)
 *      → 가장 간단한 1줄 빠진 것. 이거 빼면 NPE 폭발.
 *
 *   2. 알고리즘이 "앞에서 N번째 삭제" 의 흉내만 냄. (line 35-40)
 *      → "끝에서 N번째" 를 1-pass 로 찾으려면 두 포인터 + 간격 패턴 필요.
 *      → 또는 2-pass: 먼저 길이 L 측정 → (L-N) 번 전진 → 자르기. 이것도 답 (시간 동일 O(L)).
 *
 *   3. return cur.next 잘못. (line 44)
 *      → "방금 자른 후의 다음 노드" 를 반환. 의미가 안 맞음.
 *      → 표준은 `return dummy.next;` (수정된 리스트의 head).
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   4. head 가 삭제되는 경우 (n == 길이).
 *      → dummy 패턴이 자동으로 처리. dummy 없으면 if 분기 필요.
 *
 *   5. tail 이 삭제되는 경우 (n == 1).
 *      → 표준 패턴에서 자연스럽게 처리. slow 가 마지막 직전 노드까지 가서 자름.
 *
 *   6. 단일 노드 + n=1 케이스 ([1], n=1 → []).
 *      → dummy=null, fast 가 2 칸 가면 null. while 안 들어감. slow=dummy. slow.next=null. return dummy.next=null. ✓
 *
 * 🟢 개선 가능:
 *
 *   7. 2-pass (길이 측정 후 자르기) 도 알아두기 — 더 직관적이라 면접에서 "1차 답" 으로 OK.
 *      → 첫 패스: 길이 L 측정.
 *      → 둘째 패스: (L-N) 번 전진 후 자르기.
 *      → 시간 O(L) 동일. 면접관이 "1-pass 로 할 수 있나요?" 물으면 그때 Floyd-style 두 포인터 답.
 *
 *   8. n 검증 (1 <= n <= length) 은 문제 명세상 보장. 굳이 가드 안 해도 됨.
 *
 * --- 다음 단계 ---
 *   1) 두 포인터 + 간격 N 의 의미를 종이에 적기 ("막대 두 개" 비유).
 *   2) dummy 가 왜 필요한지 한 줄로 설명할 수 있어야 (head 삭제 케이스 처리).
 *   3) 표준 패턴 본인 표현으로 다시 짜기 (답 보지 말고).
 *   4) 손 시뮬레이션 [1,2] n=2 까지 검증.
 *   5) java -ea ... 통과 확인.
 */
class RemoveNthFromEnd {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode fast = dummy;
            ListNode slow = dummy;

            for(int i = 0; i <= n; i ++){
                fast = fast.next;
            }

            while(fast != null){
                slow = slow.next;
                fast = fast.next;
            }

            slow.next = slow.next.next;
            return dummy.next;
        }
    }

    private static ListNode build(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : arr) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    private static int[] toArray(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;
        int[] arr = new int[n];
        int i = 0;
        for (ListNode p = head; p != null; p = p.next) arr[i++] = p.val;
        return arr;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 일반 케이스: 1->2->3->4->5, n=2 → 1->2->3->5
        ListNode r1 = sol.removeNthFromEnd(build(new int[]{1, 2, 3, 4, 5}), 2);
        assert java.util.Arrays.equals(toArray(r1), new int[]{1, 2, 3, 5}) : "n=2";

        // head 삭제: [1], n=1 → []
        ListNode r2 = sol.removeNthFromEnd(build(new int[]{1}), 1);
        assert r2 == null : "remove only node";

        // head 삭제 (길이 == n): [1,2], n=2 → [2]
        ListNode r3 = sol.removeNthFromEnd(build(new int[]{1, 2}), 2);
        assert java.util.Arrays.equals(toArray(r3), new int[]{2}) : "remove head";

        // tail 삭제: [1,2], n=1 → [1]
        ListNode r4 = sol.removeNthFromEnd(build(new int[]{1, 2}), 1);
        assert java.util.Arrays.equals(toArray(r4), new int[]{1}) : "remove tail";

        System.out.println("✅ RemoveNthFromEnd: All tests passed");
    }
}
