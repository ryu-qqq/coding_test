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
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class RemoveNthFromEnd {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
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
