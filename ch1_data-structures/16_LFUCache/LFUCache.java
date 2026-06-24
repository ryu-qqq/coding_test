import java.util.*;
/**
 * LeetCode 460 - LFU Cache
 *
 * 문제: 용량 capacity인 LFU(Least Frequently Used) 캐시.
 *       동률(같은 빈도)일 때는 그 중에서 LRU 적용.
 *
 * --- 인터페이스 ---
 *   get(key): int   (없으면 -1)
 *   put(key, value)
 *
 * --- 시간복잡도 목표 ---
 *   get/put 모두 O(1)  ← LRU보다 훨씬 어려움
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 핵심 포인트 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 면접 포인트 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
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

    // 빈도별 LRU 리스트로 쓰는 이중 연결 리스트 (발판: 그대로 사용)
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

    // TODO: 필요한 필드를 선언하세요 (capacity, minFreq, keyMap, freqMap 등)

    public LFUCache(int capacity) {
        // TODO: 초기화
    }

    public int get(int key) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public void put(int key, int value) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    private void increaseFreq(Node node){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    private void evict(){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
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
