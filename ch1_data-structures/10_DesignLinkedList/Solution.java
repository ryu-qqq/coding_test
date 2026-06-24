/**
 * LeetCode 707 - Design Linked List  [정답]
 *
 * 핵심: head/tail 두 sentinel(더미)을 둔 이중 연결 리스트. 양끝 더미로 모든 삽입/삭제를
 *       "prev/next 재배선" 한 가지 패턴으로 통일(경계 분기 제거).
 * 불변식: head.next ~ tail.prev 사이에 정확히 size개의 실제 노드.
 * 복잡도: get/addAtIndex/deleteAtIndex O(N), addAtHead/addAtTail O(1)(양끝 더미 덕).
 * 자세한 해설 → SOLUTION.md
 */
class DesignLinkedList {

    private static class ListNode {
        int val;
        ListNode prev, next;
        ListNode(int v) { val = v; }
    }

    private final ListNode head;
    private final ListNode tail;
    private int size;

    public DesignLinkedList() {
        head = new ListNode(0);
        tail = new ListNode(0);
        size=0;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        if(index < 0 || index >= size) return -1;
        ListNode cur = head.next;
        for(int i =0; i <index; i ++){
            cur = cur.next;
        }

        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        ListNode old = head.next;
        old.prev = newNode;

        newNode.next = old;
        newNode.prev = head;
        head.next = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode old = tail.prev;
        old.next = newNode;
        newNode.prev = old;
        newNode.next = tail;
        tail.prev = newNode;

        size++;
    }

    public void addAtIndex(int index, int val) {
        if(index < 0 || index >= size) return;

        ListNode cur = head;
        for(int i =0; i <index; i ++){
            cur = cur.next;
        }
        //   idx
        // 1  3 (4) 5

        ListNode newNode = new ListNode(val);
        ListNode next = cur.next;

        newNode.prev = cur;    // 4 -> 3
        newNode.next = next;   // 4 -> 5

        cur.next = newNode; // 3 -> 4
        next.prev = newNode; // 5 -> 4

        size ++;

    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) return;
        ListNode cur = head.next;
        for(int i =0; i <index; i ++){
            cur = cur.next;
        }

        //   idx
        // 1  (3)  5
        ListNode prevNode = cur.prev;
        ListNode nextNode = cur.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        size --;

    }

    public static void main(String[] args) {
        DesignLinkedList list = new DesignLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2); // 1 -> 2 -> 3
        assert list.get(1) == 2 : "index 1 should be 2";
        list.deleteAtIndex(1); // 1 -> 3
        assert list.get(1) == 3 : "after delete, index 1 should be 3";
        assert list.get(3) == -1 : "out of bound should return -1";

        // 빈 리스트 엣지 케이스
        DesignLinkedList empty = new DesignLinkedList();
        assert empty.get(0) == -1 : "empty list get should return -1";
        empty.deleteAtIndex(0); // 무시되어야 함

        System.out.println("✅ DesignLinkedList: All tests passed");
    }
}
