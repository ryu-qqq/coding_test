class MyCircularDeque {

    private final int[] arr;
    private final int capacity;

    private int size;
    private int head;
    private int tail;

    public MyCircularDeque(int k) {
        arr = new int[k];
        capacity = k;
        this.size = 0;
        this.head = 0;
        this.tail = -1;
    }

    public boolean insertFront(int value) {
        if(isFull()) return false;
        head = (head - 1 +  capacity) % capacity;
        arr[head] = value;
        size++;

        return true;
    }


    public boolean insertLast(int value) {
        if(isFull()) return false;
        head = (head + 1) % capacity;
        arr[head] = value;
        size++;

        return true;
    }


    public boolean deleteFront() {
        if(isEmpty()) return false;
        tail = (tail + 1) % capacity;
        size --;

        return true;
    }

    public boolean deleteLast() {
        if(isEmpty()) return false;
        tail = (tail -1 + capacity) % capacity;
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
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }


}
