import java.util.*;
/**
 * LeetCode 146 - LRU Cache
 *
 * 문제: 용량 capacity인 LRU(Least Recently Used) 캐시
 *
 * --- 인터페이스 ---
 *   get(key): int   (없으면 -1)
 *   put(key, value)
 *
 * --- 시간복잡도 목표 ---
 *   get/put 모두 O(1)  ← 핵심 제약
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 핵심 트릭 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 왜 DLL인가? ---
 *   (스스로 떠올려볼 것)
 *
 * --- 대안 ---
 *   (스스로 떠올려볼 것)
 */
class LRUCache {

    static class Node{
        int key, value;
        Node prev, next;

        Node(int k, int v){
            key =k;
            value =v;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        // TODO: capacity 저장, HashMap, dummy head/tail 노드 초기화
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node ==null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null){
            node.value = value;
            moveToHead(node);
        }else{
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            if(map.size() > capacity){
                Node lru = removeTail();
                map.remove(lru.key);
            }
        }
    }

    private void addToHead(Node node) {
        Node frontNode = head.next;
        
        node.prev = head;
        node.next = frontNode;

        frontNode.prev = node;
        head.next = node;
    }
                                                                  
    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

    }
    
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }                                                                                                                                                                      
    
    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }


    public static void main(String[] args) {
        // 시나리오 1: 기본
        LRUCache c = new LRUCache(2);
        c.put(1, 1);
        c.put(2, 2);
        assert c.get(1) == 1;     // 1을 최근으로 이동
        c.put(3, 3);              // 2 evict (가장 오래됨)
        assert c.get(2) == -1;
        c.put(4, 4);              // 1 evict
        assert c.get(1) == -1;
        assert c.get(3) == 3;
        assert c.get(4) == 4;

        // 시나리오 2: 덮어쓰기
        LRUCache c2 = new LRUCache(2);
        c2.put(1, 1);
        c2.put(1, 10);            // 같은 key 덮어쓰기
        assert c2.get(1) == 10;

        // 시나리오 3: capacity 1
        LRUCache c3 = new LRUCache(1);
        c3.put(1, 1);
        c3.put(2, 2);
        assert c3.get(1) == -1;
        assert c3.get(2) == 2;

        System.out.println("✅ LRUCache: All tests passed");
    }
}
