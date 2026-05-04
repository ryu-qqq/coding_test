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
 * --- 핵심 아이디어 (수도코드) ---
 *   Floyd's Tortoise and Hare (토끼와 거북이) 알고리즘.
 *   slow는 한 칸씩, fast는 두 칸씩 전진한다.
 *   사이클이 있다면 fast가 결국 slow를 따라잡는다(=만난다).
 *   사이클이 없다면 fast가 null에 먼저 도달한다.
 *
 *   slow = head; fast = head
 *   while fast != null and fast.next != null:
 *     slow = slow.next
 *     fast = fast.next.next
 *     if slow == fast: return true
 *   return false
 *
 * --- 불변식 ---
 *   사이클이 존재하면 fast - slow 의 거리(모듈러 사이클 길이)는 매 스텝마다 1씩 줄어든다.
 *
 * --- 함정 ---
 *   - fast.next.next 에 NPE 나지 않도록 fast와 fast.next 둘 다 null 체크.
 *   - HashSet으로 방문 체크하는 풀이도 가능하지만 공간 O(N)이라 비효율.
 */
class LinkedListCycle {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static class Solution {
        public boolean hasCycle(ListNode head) {
            // TODO
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
