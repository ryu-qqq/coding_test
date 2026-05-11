/**
 * LeetCode 707 - Design Linked List
 *
 * 문제: 단일 또는 이중 연결 리스트를 직접 구현한다.
 *
 * --- 인터페이스 ---
 *   int get(int index)
 *   void addAtHead(int val)
 *   void addAtTail(int val)
 *   void addAtIndex(int index, int val)
 *   void deleteAtIndex(int index)
 *
 * --- 시간복잡도 목표 ---
 *   get / addAtIndex / deleteAtIndex : O(N)
 *   addAtHead / addAtTail            : O(1) (tail 포인터 유지 시)
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
class DesignLinkedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public DesignLinkedList() {
        // TODO: dummy head, size 초기화
    }

    public int get(int index) {
        // TODO
        return -1;
    }

    public void addAtHead(int val) {
        // TODO
    }

    public void addAtTail(int val) {
        // TODO
    }

    public void addAtIndex(int index, int val) {
        // TODO
    }

    public void deleteAtIndex(int index) {
        // TODO
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
