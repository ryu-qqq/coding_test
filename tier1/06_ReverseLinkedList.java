/**
 * LeetCode 206 - Reverse Linked List
 *
 * 문제: 단일 연결 리스트를 뒤집어 새로운 head를 반환한다.
 *
 * --- 인터페이스 ---
 *   ListNode reverseList(ListNode head)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(1) (반복문 버전)
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
class ReverseLinkedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            
            //null -> 1
            //1 -> 2
            //2 -> 3


            //3 -> 2
            //2 -> 1
            //1 -> null

            
            while(curr != null){
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }
    }

    // 헬퍼: 배열 → 리스트
    private static ListNode build(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : arr) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 헬퍼: 리스트 → 배열
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

        ListNode r1 = sol.reverseList(build(new int[]{1, 2, 3, 4, 5}));
        int[] a1 = toArray(r1);
        assert java.util.Arrays.equals(a1, new int[]{5, 4, 3, 2, 1}) : "reverse 12345";

        ListNode r2 = sol.reverseList(build(new int[]{1, 2}));
        assert java.util.Arrays.equals(toArray(r2), new int[]{2, 1}) : "reverse 12";

        // 빈 리스트
        ListNode r3 = sol.reverseList(null);
        assert r3 == null : "reverse null should be null";

        // 단일 노드
        ListNode r4 = sol.reverseList(build(new int[]{7}));
        assert java.util.Arrays.equals(toArray(r4), new int[]{7}) : "single node";

        System.out.println("✅ ReverseLinkedList: All tests passed");
    }
}
