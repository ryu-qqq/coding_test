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
 *
 * ====================================
 * === 채점 결과 (2026-05-11) ===
 * ====================================
 *
 * --- 실행 결과 ---
 *   ❌ AssertionError "index 1 should be 2" at line 106. 첫 assert 부터 실패.
 *
 * --- 가장 큰 문제: get() 의 오프 바이 원 ---
 *
 *   리스트: dummy → 1 → 2 → 3
 *   get(1) 호출 시:
 *     cur = dummy
 *     for i=0; i<1: cur = cur.next = node(1)
 *     return cur.val = 1   ← 기대값은 2!
 *
 *   즉 너 코드는 항상 "인덱스 index 의 노드" 가 아니라 "인덱스 index-1 의 노드" 를 반환한다.
 *
 *   원인: cur = dummy 에서 시작해서 index 번 next 를 타면, 도달하는 곳은 "**인덱스 (index-1) 의 노드**".
 *   왜냐하면 dummy → [0번 노드] → [1번 노드] → ... 이므로 dummy 에서 1번 next 타면 0번 노드.
 *
 *   다른 메서드들 (addAtIndex, deleteAtIndex) 은 같은 패턴으로 "직전 노드" 를 찾는 게 맞다 — 거기서 cur.next 조작.
 *   하지만 get 은 "직전" 이 아니라 "그 노드 자체" 가 필요하다 → 한 칸 더 가야 함.
 *
 *   고치는 두 가지 방법:
 *     (a) for 한 번 더: `for (int i = 0; i <= index; i++) cur = cur.next;`
 *     (b) 시작점을 dummy.next 로: `cur = dummy.next; for (int i = 0; i < index; i++) cur = cur.next;`
 *
 *   둘 중 어느 게 더 일관성 있을지 직접 따져봐 (다른 메서드들의 패턴과 비교).
 *
 * 🔴 결정적 오류:
 *
 *   1. get() 의 오프 바이 원. (line 47-51)
 *      → 위 설명대로. 항상 한 칸 앞 노드의 값을 반환.
 *
 *   2. get() 의 OOB 처리가 명세와 다르다. (line 44)
 *      → 명세: out-of-bound 면 **-1 반환**.
 *      → 코드: IllegalStateException 던짐.
 *      → 테스트 line 109: `assert list.get(3) == -1` → 예외 던지면 깨짐.
 *      → 또한 조건 `size < index` 도 부정확. `index >= size` 또는 `index < 0` 이 OOB.
 *        `size < index` 는 `index > size` 와 같음. 즉 `index == size` 케이스를 놓침 (그것도 OOB 여야).
 *
 *   3. 빈 리스트 get(0) 동작 잘못. (line 47-51)
 *      → size=0, index=0. `size < index` (0 < 0) false → for 0 번 → cur=dummy → return dummy.val = 0.
 *      → 기대값: -1.
 *      → 테스트 line 113: `assert empty.get(0) == -1` 깨질 것.
 *
 *   4. deleteAtIndex() 의 가드 없음. (line 89-99)
 *      → index >= size 면 cur.next 가 null 인데 cur.next.next → NPE.
 *      → 빈 리스트에서 deleteAtIndex(0) 호출 시 NPE (테스트 line 114).
 *      → 명세: index 가 유효하지 않으면 **아무것도 안 함** (조용히 무시).
 *      → 가드: `if (index < 0 || index >= size) return;`
 *
 *   5. addAtIndex() 의 가드 없음. (line 74-87)
 *      → 명세:
 *           - index <= 0 → addAtHead 처럼 동작
 *           - 0 < index < size → 중간 삽입
 *           - index == size → addAtTail 처럼 동작 (정확히 끝)
 *           - index > size → 아무것도 안 함
 *      → 너 코드는 index > size 일 때 for 가 너무 멀리 가서 cur=cur.next 에서 NPE 가능.
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   6. size++ / size-- 의 위치.
 *      → 가드 통과 후에만 ++ / -- 해야 한다. 무효한 호출에서 size 가 바뀌면 후속 동작이 다 깨짐.
 *      → 가드 추가하면서 같이 정리할 것.
 *
 *   7. 명세 재확인:
 *      - get: invalid → -1 반환
 *      - addAtHead/Tail: 항상 성공
 *      - addAtIndex: 위 케이스 분기 (index 0~size 는 삽입, > size 는 무시)
 *      - deleteAtIndex: 0 <= index < size 만 삭제, 그 외 무시
 *
 * 🟢 개선 가능:
 *
 *   8. addAtTail 이 매번 O(N). (line 63-72)
 *      → docstring 의 시간복잡도 목표는 O(1).
 *      → tail 포인터 필드 (`private ListNode tail`) 를 두면 O(1) 가능.
 *      → 단 tail 유지는 add/delete 모든 케이스에서 신경써야 (특히 마지막 노드 삭제 시 tail 갱신).
 *      → 면접에선 "처음엔 O(N) 으로 짜고, follow-up 으로 tail 도입" 흐름이 자연스럽다.
 *
 *   9. addAtHead 의 temp 변수는 불필요. (line 56-58)
 *      → `newNode.next = dummy.next; dummy.next = newNode;` 두 줄로 충분.
 *      → 단 순서 중요: newNode.next 먼저 잡고 dummy.next 갱신.
 *
 * --- 다음 단계 ---
 *   1) get 의 오프 바이 원 + OOB 가드 + 빈 리스트 처리 한 번에 정리.
 *   2) deleteAtIndex / addAtIndex 에 가드 추가.
 *   3) size++ / size-- 가드 통과 후로 옮기기.
 *   4) java -ea ... 통과 확인.
 *   5) (선택) tail 포인터 도입해 addAtTail O(1) 으로.
 */
class DesignLinkedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    private ListNode dummy;
    private int size;

    public DesignLinkedList() {
        dummy = new ListNode(0);
        size=0;
        
    }

    public int get(int index) {
        if(index < 0 || size <= index) return -1;

        ListNode cur = dummy;
        for(int i =0; i <=index; i ++){
            cur = cur.next;
        }

        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        ListNode temp = dummy.next;
        newNode.next = temp;
        dummy.next = newNode;

        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode cur = dummy;
        while(cur.next != null){
            cur = cur.next;
        }

        cur.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;

        ListNode newNode = new ListNode(val);
        ListNode cur = dummy;

        for(int i =0; i <index; i ++){
            cur = cur.next;
        }

        ListNode temp = cur.next;
        cur.next = newNode;
        newNode.next = temp;

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index > size) return;

        ListNode cur = dummy;

        for(int i =0; i <index; i ++){
            cur = cur.next;
        }

        cur.next = cur.next.next;

        size--;
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
