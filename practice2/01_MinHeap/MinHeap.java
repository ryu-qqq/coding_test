/**
 * ═══════════════════════════════════════════════════════════
 *  Problem: Min Heap (Priority Queue) — Direct Implementation
 * ═══════════════════════════════════════════════════════════
 *
 * Implement a Min-Heap data structure backed by an array.
 * The use of `java.util.PriorityQueue` is NOT allowed.
 *
 * --- Function Description ---
 *
 *   class MinHeap {
 *     void   push(int val)        // insert a value
 *     int    pop()                // remove and return the minimum
 *     int    peek()               // return the minimum without removing
 *     int    size()               // current number of elements
 *   }
 *
 *   Indexing convention (0-based):
 *     parent(i) = (i - 1) / 2
 *     left(i)   = 2*i + 1
 *     right(i)  = 2*i + 2
 *
 * --- Constraints ---
 *
 *   1 <= number of operations <= 10^5
 *   -10^9 <= val <= 10^9
 *   pop() and peek() are only called when size() > 0.
 *
 * --- Sample Operations ---
 *
 *   push(5)
 *   push(3)
 *   push(8)
 *   push(1)
 *   peek()         → 1
 *   pop()          → 1
 *   pop()          → 3
 *   peek()         → 5
 *   size()         → 2
 *
 * --- Time Complexity Targets ---
 *
 *   push:  O(log n)
 *   pop:   O(log n)
 *   peek:  O(1)
 *   size:  O(1)
 *
 * --- Hints (스스로 떠올려볼 것) ---
 *
 *   • siftUp / siftDown 의 비교 방향 (min-heap → 자식이 더 작으면 swap)
 *   • pop 시 마지막 원소를 루트로 이동 후 siftDown
 *   • 배열 크기 부족하면 capacity 두 배로 확장 (Arrays.copyOf 의 반환값을 받아야 함!)
 */

import java.util.Arrays;

class MinHeap {

    private int[] arr;
    private int size;
    private static final int INIT_CAPACITY = 16;

    public MinHeap() {
        arr = new int[INIT_CAPACITY];
        size = 0;
    }

    // ───────────────────────────────────────────
    // push: 끝에 추가 + siftUp
    // ───────────────────────────────────────────
    public void push(int val) {
        if(size == INIT_CAPACITY){
            int capacity = 2 * INIT_CAPACITY;
            arr = Arrays.copyOf(arr, capacity);
        }

        arr[size] =val;
        size ++;
        siftUp(size - 1);
    }

    // ───────────────────────────────────────────
    // pop: 루트 빼고 → 마지막 원소를 루트로 → siftDown
    // ───────────────────────────────────────────
    public int pop() {
        int min = arr[0];
        arr[0] = arr[size- 1];
        size--;

        if(size >0) siftDown(0);

        return min;
    }

    // ───────────────────────────────────────────
    // peek: O(1)
    // ───────────────────────────────────────────
    public int peek() {
        // TODO
        return arr[0];
    }

    public int size() {
        return size;
    }

    // ── helpers (직접 구현) ──
    private void siftUp(int i) {
        while(i > 0){
            int parent = (i - 1) / 2;
            if(arr[parent] <= arr[i]) break;

            int temp =arr[parent];
            arr[parent] = arr[i];
            arr[i] = temp;
            i = parent;
        }
    }

    private void siftDown(int i) {
        while(true){
            int leftIdx = 2*i + 1;
            int rightIdx = 2*i + 2;
            int smallest = i;

            if(leftIdx < size && arr[leftIdx] < arr[smallest]){
                smallest = leftIdx;
            }

            if(rightIdx < size && arr[rightIdx] < arr[smallest]){
                smallest = rightIdx;
            }

            if(smallest == i) break;

            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            i = smallest;
        }
    }

    private void swap(int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }


    public static void main(String[] args) {
        MinHeap h = new MinHeap();
        h.push(5);
        h.push(3);
        h.push(8);
        h.push(1);
        assert h.peek() == 1 : "peek should be 1";
        assert h.size() == 4 : "size should be 4";

        assert h.pop() == 1 : "pop should be 1";
        assert h.pop() == 3 : "pop should be 3";
        assert h.peek() == 5 : "peek should be 5";
        assert h.size() == 2 : "size should be 2";

        // 단조 증가 입력
        MinHeap h2 = new MinHeap();
        for (int i = 1; i <= 10; i++) h2.push(i);
        for (int i = 1; i <= 10; i++) assert h2.pop() == i : "ordered pop " + i;

        // 단조 감소 입력 (siftUp 자주 발생)
        MinHeap h3 = new MinHeap();
        for (int i = 10; i >= 1; i--) h3.push(i);
        for (int i = 1; i <= 10; i++) assert h3.pop() == i : "reverse pop " + i;

        // 중복 값
        MinHeap h4 = new MinHeap();
        h4.push(2); h4.push(2); h4.push(1); h4.push(2);
        assert h4.pop() == 1;
        assert h4.pop() == 2;
        assert h4.pop() == 2;
        assert h4.pop() == 2;

        // capacity 초과 (16 -> 32 확장)
        MinHeap h5 = new MinHeap();
        for (int i = 1; i <= 20; i++) h5.push(i);
        assert h5.size() == 20;
        for (int i = 1; i <= 20; i++) assert h5.pop() == i;

        System.out.println("✅ MinHeap: All tests passed");
    }
}
