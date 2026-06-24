/**
 * LeetCode 19 - Remove Nth Node From End of List  [정답]
 *
 * 핵심: dummy + 두 포인터. fast를 먼저 n칸 전진(여기선 dummy 기준 간격 n)시킨 뒤 둘이 같이 이동,
 *       fast.next가 null일 때 slow가 "삭제 대상의 직전".
 * 함정: dummy를 안 쓰면 head 자체 삭제 케이스에서 분기가 필요해진다.
 * 복잡도: 시간 O(L) 한 패스, 공간 O(1).
 * 자세한 해설 → SOLUTION.md
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
            ListNode slow = dummy;
            ListNode fast = dummy;

            dummy.next = head;

            for(int i =0; i <n; i ++){
                fast = fast.next;
            }

            while(fast.next != null){
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
