/**
 * LeetCode 141 - Linked List Cycle  [정답]
 *
 * 핵심: Floyd의 토끼-거북이. slow 1칸, fast 2칸. 사이클이 있으면 둘은 반드시 만난다.
 * 함정: NPE 방지 위해 while 조건에서 fast와 fast.next 둘 다 null 체크.
 * 복잡도: 시간 O(N), 공간 O(1) (HashSet 풀이는 O(N) 공간이라 열등).
 * 자세한 해설 → SOLUTION.md
 */
class LinkedListCycle {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static class Solution {
        public boolean hasCycle(ListNode head) {
            if(head == null) return false;
            ListNode slow = head;
            ListNode fast = head;

            while(fast != null && fast.next!= null){
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast ) return true;
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
