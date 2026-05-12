/**
 * LeetCode 206 - Reverse Linked List
 *
 * 야 이거 답이뭐냐 ;; 왜케 헷가릴지 스왑하는게 너무 헷갈려
 *
 * 단일 연결리스트 뒤집기 (반복문 O(1) 공간버전)
 * 
 * [핵심]
 * - 현재 노드(cur)에서 next를 따라가다 보면 기존 연결방향을 잃어버리니까 "다음 노드(next)"를 백업해둬야 함
 * - 방향을 바꿀 때는: cur.next = prev; // 여기서 진짜 연결 '역전' 발생!
 * - prev, cur, next 세 포인터를 한칸씩 밀면서 반복
 * 
 * - 최종적으로 prev가 새로운 head가 된다(cur=null로 끝났으니까)
 *
 * 손으로 1→2→3 따라가면:
 *   0) prev=null, cur=1
 *   1) next=2, 1.next=null, prev=1, cur=2
 *   2) next=3, 2.next=1,    prev=2, cur=3
 *   3) next=null, 3.next=2, prev=3, cur=null
 *   ==> prev(3) return!
 */
class ReverseLinkedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }
    // head
    //  a -> b -> c
    //  
    static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode cur = head;

            while(cur != null){
                //b
                ListNode next = cur.next;
                // a 의 이전을 null 바꿈
                cur.next = prev;
                // 전을 a
                prev =cur;
                cur = next;
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
