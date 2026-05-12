import java.util.Arrays;

/**
 * Min Heap (Priority Queue) 직접 구현
 *
 * 문제: 배열 기반 이진 힙 구현 (Java PriorityQueue 사용 금지)
 *
 * --- 인터페이스 ---
 *   insert(val)
 *   extractMin(): int
 *   peek(): int
 *   size(): int
 *
 * --- 시간복잡도 목표 ---
 *   insert: O(log n)
 *   extractMin: O(log n)
 *   peek: O(1)
 *   buildHeap (배치): O(n)  (도전 과제)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
**/

public class MinHeap {

    private final static int DEFAULT_SIZE = 16;
    private int[] arr;
    private int size;
    private int capacity;


    public MinHeap(){
        arr = new int[DEFAULT_SIZE];
        size =0;
        capacity = DEFAULT_SIZE;
    }


    public void insert(int val){
        if(size == capacity){
            capacity = capacity * 2;
            arr = Arrays.copyOf(arr, capacity);
        }


        arr[size] = val;
        size++;
        siftUp(size-1);
    }

    public int extractMin(){
        int min = arr[0];
        arr[0] = arr[size - 1];
        size--;
        if(size >0) siftDown(0);
        return min;
    }

    public void siftDown(int i){
        while(true){
            int leftIdx = 2*i + 1;
            int rightIdx = 2*i + 2;

            int smallest = i;

            if(leftIdx < size && arr[leftIdx] < arr[smallest]) {
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

    public void siftUp(int i){
        while(i >0){
            int parent = (i - 1) / 2;
            if(arr[i] >= arr[parent]) break;

            int temp = arr[parent];
            arr[parent] = arr[i];
            arr[i] = temp;
            i = parent;
        }
    }

    public int peek(){
        return arr[0];
    }

    public int size(){
        return size;
    }




}
