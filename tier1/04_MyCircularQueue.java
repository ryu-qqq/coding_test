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
 * --- 핵심 아이디어 (수도코드) ---
 *   고정 길이 배열 + head, tail, size 추적
 *   tail = (tail + 1) % capacity 로 회전
 *
 *   enQueue(v):
 *     if isFull(): return false
 *     tail = (tail + 1) % capacity
 *     arr[tail] = v
 *     size++
 *     return true
 *
 *   deQueue():
 *     if isEmpty(): return false
 *     head = (head + 1) % capacity
 *     size--
 *     return true
 *
 *   Front(): return isEmpty() ? -1 : arr[head]
 *   Rear():  return isEmpty() ? -1 : arr[tail]
 *
 * --- 초기화 트릭 ---
 *   head=0, tail=-1 로 시작하면 enQueue 첫 호출에서 tail이 0이 됨.
 *   또는 head=tail=0 으로 두고 size로만 비/참 판단해도 됨 (취향).
 *
 * --- 불변식 ---
 *   - 0 <= size <= capacity
 *   - size == 0 ⇔ isEmpty()
 *   - size == capacity ⇔ isFull()
 *   - head, tail 모두 [0, capacity) 범위에 있음
 */
class MyCircularQueue {

    private final int[] arr;
    private final int capacity;
    private int size;
    private int head;
    private int tail;



    public MyCircularQueue(int k) {
        this.capacity = k;
        this.arr = new int[k];
        this.size = 0;
        this.head = 0;
        this.tail = -1;
    }

    //tail   head
    // 0    [ () , () ]
    //
    // tail =  0 % 2 -> 0
    // 1    [ val1 , () ]

    //       tail   head
    //  0   [ val1 , () ]

    // tail = (0+1) % 2 = 0
    //       tail     head,
    //  1   [ val2 ,  () ]


    // tail = 2 % 2 = 1
    //              head, tail
    //  2   [ val2 , val3 ]



    // haed = 2 % 2 = 0
    //       head     tail
    //  1   [ val2 , val3 ]

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
