import java.util.*;
/**
 * LeetCode 460 - LFU Cache  [정답]
 *
 * 핵심: 빈도(freq)별로 별도 DLL을 둬서 같은 빈도 안에서는 LRU 적용. evict는 minFreq DLL의 tail.
 *       minFreq는 새 원소 put 시 1로 리셋, increaseFreq 후 old DLL이 비고 minFreq==old일 때만 +1.
 * 복잡도: get/put 모두 O(1), 공간 O(capacity).
 * 자세한 해설 → SOLUTION.md
 */
class LFUCache {

    static class Node{
        int key, value, freq;
        Node prev, next;
        Node(int k, int v){
            this.key = k;
            this.value = v;
            this.freq = 1;
        }
    }

    static class DLL{
        Node head, tail;
        int size;
        DLL(){
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;

        }


        public void addToHead(Node node){
            Node frontNode = head.next;


            node.next = frontNode;
            node.prev =head;

            head.next = node;
            frontNode.prev = node;
            size++;
        }


        public void removeNode(Node node){
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }

        public Node removeTail(){
            Node lru = tail.prev;
            removeNode(lru);

            return lru;
        }


    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> keyMap;
    private final Map<Integer, DLL> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.minFreq =0;
    }

    public int get(int key) {
        Node node = keyMap.get(key);
        if(node == null) return -1;
        increaseFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(capacity ==0) return;
        Node node = keyMap.get(key);
        if(node != null){
            node.value = value;
            increaseFreq(node);
        }else{
            if(keyMap.size() >= capacity) evict();
            Node newNode = new Node(key, value);
            keyMap.put(key, newNode);
            freqMap.computeIfAbsent(1, k-> new DLL()).addToHead(newNode);
            minFreq = 1;   // 새 원소는 freq=1 → minFreq 강제 리셋
        }
    }

    private void increaseFreq(Node node){
        DLL oldDll = freqMap.get(node.freq);
        oldDll.removeNode(node);
        // old DLL이 비었고 그게 현재 minFreq였으면 minFreq를 올린다
        if(node.freq == minFreq && oldDll.size ==0) minFreq ++;
        node.freq ++;
        freqMap.computeIfAbsent(node.freq, k -> new DLL()).addToHead(node);
    }

    private void evict(){
        DLL oldDll = freqMap.get(minFreq);
        Node lru = oldDll.removeTail();   // 최소 빈도 그룹의 LRU
        keyMap.remove(lru.key);
    }

    public static void main(String[] args) {
        // 시나리오: LeetCode 공식 예제
        LFUCache c = new LFUCache(2);
        c.put(1, 1);
        c.put(2, 2);
        assert c.get(1) == 1;       // freq[1]=2, freq[2]=1
        c.put(3, 3);                // capacity 초과 → key 2 evict (freq 가장 낮음)
        assert c.get(2) == -1;
        assert c.get(3) == 3;       // freq[1]=2, freq[3]=2
        c.put(4, 4);                // 동률 freq=2 → LRU(=key 1) evict
        assert c.get(1) == -1;
        assert c.get(3) == 3;
        assert c.get(4) == 4;

        // capacity 0
        LFUCache c0 = new LFUCache(0);
        c0.put(0, 0);
        assert c0.get(0) == -1;

        System.out.println("✅ LFUCache: All tests passed");
    }
}
