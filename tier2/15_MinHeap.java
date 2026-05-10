import java.util.Arrays;
import java.util.List;

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

    private int[] arr;
    private int size;
    private int capacity;
    private static final int DEFAULT_SIZE = 16;

    public MinHeap(){
        capacity = DEFAULT_SIZE;
        size = 0;
        arr = new int[DEFAULT_SIZE];
    }

    public void insert(int val){
        if(size  == capacity){
            arr = Arrays.copyOf(arr, capacity * 2);
            capacity *= 2;
        }
        arr[size] = val;
        size++;
        siftUp(size-1);
        
    }

    public int extractMin(){
        int min = arr[0];
        arr[0] = arr[size-1];
        size--;
        if(size>0) siftDown(0);
        return min;
    }

    private void siftUp(int i){
        while(i > 0){
            int parent = (i - 1) / 2;
            if(arr[i] >= arr[parent]) break;

            int temp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = temp;
            i = parent;
        }

    }

    private void siftDown(int i){
        while(true){
            int left = 2*i + 1;
            int right = 2*i + 2;
            int smallest = i;

            if(left < size && arr[left] < arr[smallest]){
                smallest = left;
            }
            if(right < size && arr[right]< arr[smallest]){
                smallest = right;
            }

            if(smallest == i) break;

            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            i = smallest;
        }
        

    }

    

    public int peek(){
        return arr[0];
    }

    public int size(){
        return size;
    }

    
    public static void main(String[] args) {                                                                                                                                                                                
        MinHeap h = new MinHeap();                                                                           
        h.insert(5);                             
        h.insert(3);                        
        h.insert(8);
        h.insert(1);                                                                                                                                                                                                        
        h.insert(7);
                                                                                                                                                                                                                            
        assert h.peek() == 1 : "min should be 1";                                                            
        assert h.size() == 5 : "size should be 5";
                                                                                                                                                                                                                            
        assert h.extractMin() == 1;         
        assert h.extractMin() == 3;                                                                                                                                                                                         
        assert h.extractMin() == 5;                                                                                                                                                                                         
        assert h.extractMin() == 7;              
        assert h.extractMin() == 8;                                                                                                                                                                                         
        assert h.size() == 0;                                                                                                                                                                                               
                                                 
        // grow 테스트 (capacity=16 넘김)                                                                                                                                                                                   
        MinHeap big = new MinHeap();                                                                         
        for (int i = 100; i >= 1; i--) big.insert(i);                                                                                                                                                                       
        for (int i = 1; i <= 100; i++) {    
            assert big.extractMin() == i : "should pop in order";                                                                                                                                                           
        }                                                                                                                                                                                                                   
                                                 
        System.out.println("✅ MinHeap: All tests passed");                                                                                                                                                                 
    }                


}
