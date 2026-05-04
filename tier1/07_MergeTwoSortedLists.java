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
 * --- 핵심 아이디어 (수도코드) ---
 *   더미 헤드(dummy)를 두고 tail 포인터로 한 노드씩 붙여 나간다.
 *
 *   dummy = new node
 *   tail = dummy
 *   while l1 != null and l2 != null:
 *     if l1.val <= l2.val:
 *       tail.next = l1; l1 = l1.next
 *     else:
 *       tail.next = l2; l2 = l2.next
 *     tail = tail.next
 *   tail.next = (l1 != null ? l1 : l2)   // 남은 부분 그대로 연결
 *   return dummy.next
 *
 * --- 불변식 ---
 *   매 반복 시점에서 dummy.next ~ tail 까지는 정렬된 상태로 유지된다.
 *
 * --- 함정 ---
 *   - 마지막에 남은 리스트를 그대로 이어주는 것을 잊으면 안 된다.
 *   - 한쪽이 null인 경우 그대로 다른 쪽을 반환.
 *   - 동일 값일 때 안정 정렬을 원한다면 <= 사용 (l1 우선).
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
