/**
 * LeetCode 622 - Design Circular Queue  [정답]
 *
 * 핵심: 모듈러 산술로 인덱스를 회전 — tail=(tail+1)%capacity, head=(head+1)%capacity.
 * 초기화 트릭: tail=-1로 시작하면 첫 enQueue가 (-1+1)%cap=0으로 분기 없이 처리됨.
 * 불변식: 0 ≤ size ≤ capacity, head/tail 모두 [0, capacity) 범위. 모든 연산 O(1).
 * 자세한 해설 → SOLUTION.md
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
