/**
 * LeetCode 21 - Merge Two Sorted Lists
 *
 * 문제: 오름차순 정렬된 두 연결 리스트를 하나로 병합한다.
 *
 * --- 인터페이스 ---
 *   ListNode mergeTwoLists(ListNode l1, ListNode l2)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N + M), 공간 O(1) (반복 버전)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 */
class MergeTwoSortedLists {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // TODO
            return null;
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

        ListNode r1 = sol.mergeTwoLists(build(new int[]{1, 2, 4}), build(new int[]{1, 3, 4}));
        assert java.util.Arrays.equals(toArray(r1), new int[]{1, 1, 2, 3, 4, 4}) : "merge 124,134";

        ListNode r2 = sol.mergeTwoLists(null, null);
        assert r2 == null : "both null";

        ListNode r3 = sol.mergeTwoLists(null, build(new int[]{0}));
        assert java.util.Arrays.equals(toArray(r3), new int[]{0}) : "one null";

        ListNode r4 = sol.mergeTwoLists(build(new int[]{1, 5, 9}), build(new int[]{2}));
        assert java.util.Arrays.equals(toArray(r4), new int[]{1, 2, 5, 9}) : "interleave";

        System.out.println("✅ MergeTwoSortedLists: All tests passed");
    }
}
