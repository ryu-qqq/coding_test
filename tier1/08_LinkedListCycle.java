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
