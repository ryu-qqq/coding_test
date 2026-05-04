/**
 * LeetCode 641 - Design Circular Deque
 *
 * 문제: 고정 용량의 원형 디큐 (양방향 삽입/삭제).
 *
 * --- 인터페이스 ---
 *   insertFront(value): boolean
 *   insertLast(value): boolean
 *   deleteFront(): boolean
 *   deleteLast(): boolean
 *   getFront(): int     (-1 if empty)
 *   getRear(): int      (-1 if empty)
 *   isEmpty(): boolean
 *   isFull(): boolean
 *
 * --- 시간복잡도 목표 ---
 *   모든 연산 O(1)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   - 데이터는 절대 안 움직인다. head/tail 포인터만 이동.
 *   - 0 <= size <= capacity
 *   - size 가 진실의 원천 (head/tail 의 대소 관계 의미 X)
 *
 * --- 함정 ---
 *   - 인덱스를 뒤로 갈 때 (insertFront, deleteLast) 자바 % 가 음수면 음수 반환.
 *     반드시 + capacity 보정: (i - 1 + capacity) % capacity
 */
class MyCircularDeque {

    public MyCircularDeque(int k) {
        // TODO: 배열 + head/tail/size/capacity 초기화
    }

    public boolean insertFront(int value) {
        // TODO
        return false;
    }

    public boolean insertLast(int value) {
        // TODO
        return false;
    }

    public boolean deleteFront() {
        // TODO
        return false;
    }

    public boolean deleteLast() {
        // TODO
        return false;
    }

    public int getFront() {
        // TODO
        return -1;
    }

    public int getRear() {
        // TODO
        return -1;
    }

    public boolean isEmpty() {
        // TODO
        return true;
    }

    public boolean isFull() {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        MyCircularDeque dq = new MyCircularDeque(3);
        assert dq.insertLast(1);
        assert dq.insertLast(2);
        assert dq.insertFront(3);
        assert !dq.insertFront(4);   // 가득 참
        assert dq.getRear() == 2;
        assert dq.isFull();
        assert dq.deleteLast();
        assert dq.insertFront(4);
        assert dq.getFront() == 4;

        // 빈 상태
        MyCircularDeque dq2 = new MyCircularDeque(2);
        assert dq2.isEmpty();
        assert dq2.getFront() == -1;
        assert dq2.getRear() == -1;
        assert !dq2.deleteFront();
        assert !dq2.deleteLast();

        System.out.println("✅ MyCircularDeque: All tests passed");
    }
}
