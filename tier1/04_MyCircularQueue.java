/**
 * LeetCode 622 - Design Circular Queue
 *
 * 문제: 고정 용량의 원형 큐. 다 차면 새 원소 거부.
 *
 * --- 인터페이스 ---
 *   enQueue(value): boolean   (성공 시 true)
 *   deQueue(): boolean        (성공 시 true)
 *   Front(): int              (-1 if empty)
 *   Rear(): int               (-1 if empty)
 *   isEmpty(): boolean
 *   isFull(): boolean
 *
 * --- 시간복잡도 목표 ---
 *   모든 연산 O(1)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 초기화 트릭 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 */
class MyCircularQueue {

    private final int[] arr;
    private final int capacity;              
    private int head;
    private int tail;
    private int size;


    public MyCircularQueue(int k) {
        arr = new int[k];
        capacity = k;
        head =0;
        tail =-1;
        size = 0;
    }

    public boolean enQueue(int value) {
        if(isFull()) return false;
        tail = (tail + 1) % capacity;
        arr[tail] = value;
        size++;
    
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()) return false;
        head = (head + 1) % capacity;
        size--;

        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return arr[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return arr[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        MyCircularQueue q = new MyCircularQueue(3);
        assert q.enQueue(1);
        assert q.enQueue(2);
        assert q.enQueue(3);
        assert !q.enQueue(4);          // 가득 참
        assert q.Rear() == 3;
        assert q.isFull();
        assert q.deQueue();
        assert q.enQueue(4);            // 회전
        assert q.Rear() == 4;
        assert q.Front() == 2;

        // 빈 상태
        MyCircularQueue q2 = new MyCircularQueue(2);
        assert q2.Front() == -1;
        assert q2.Rear() == -1;
        assert !q2.deQueue();

        System.out.println("✅ MyCircularQueue: All tests passed");
    }
}
