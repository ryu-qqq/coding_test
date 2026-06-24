import java.util.*;
/**
 * LeetCode 641 - Design Circular Deque  [정답]
 *
 * 핵심: 양쪽 회전 + 음수 모듈러 보정 — (head - 1 + capacity) % capacity.
 * 외우기: "Front 삽입은 head를 앞당기고(-1), Last 삽입은 tail을 뒤로 민다(+1)".
 * 함정: 자바 %는 음수에 음수를 반환 → +capacity 보정 필수. 모든 연산 O(1).
 * 자세한 해설 → SOLUTION.md
 */
class MyCircularDeque {

    private final int[] arr;
    private final int capacity;
    private int size, head, tail;



    public MyCircularDeque(int k) {
        arr = new int[k];
        capacity = k;
        size = 0;
        head = 0;
        tail = -1;
    }

    // [ () () () ]
    // 2 % 3 -> 0

    // 그러니까 앞에 넣는건 헤드만 움직이면 되는데 헤드가 앞으로 넣으려면 -1 해야한다
    public boolean insertFront(int value) {
        if(isFull()) return false;
        head = (head - 1 + capacity) % capacity;
        arr[head] = value;
        size ++;

        return true;
    }

     // 그러니까 뒤에 넣는건 꼬리만 움직이면 되는데 꼬리가 뒤로 움직여야하니 + 1 해야한다
    public boolean insertLast(int value) {
        if(isFull()) return false;
        tail = (tail + 1 ) % capacity;
        arr[tail] = value;
        size ++;
        return true;
    }

    // 그러니까 앞에 빼는건 헤드만 움직이면 되는데 헤드가 뒤로 움직여야하니 + 1 해야한다
    public boolean deleteFront() {
        if(isEmpty()) return false;
        head = (head + 1) % capacity;
        size --;
        return true;
    }

    // 그러니까 뒤에 빼는건 꼬리만 움직이면 되는데 꼬리가 앞으로 움직여야하니 -1 해야한다
    public boolean deleteLast() {
        if(isEmpty()) return false;
        tail = (tail - 1 + capacity) % capacity;
        size --;
        return true;
    }

    public int getFront() {
        if(isEmpty()) return -1;
        return arr[head];
    }

    public int getRear() {
        if(isEmpty()) return -1;
        return arr[tail];
    }

    public boolean isEmpty() {
        return size ==0;
    }

    public boolean isFull() {
        return size == capacity;
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
