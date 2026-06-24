import java.util.*;
/**
 * LeetCode 146 - LRU Cache  [정답]
 *
 * 핵심: HashMap<key,Node>로 O(1) 검색 + Doubly Linked List로 O(1) 임의 노드 제거/head 이동.
 *       evict는 tail 노드 삭제. Node에 key도 저장해야 evict 시 map에서도 지울 수 있다.
 * 복잡도: get/put 모두 O(1), 공간 O(capacity).
 * 자세한 해설 → SOLUTION.md
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
        // dummy head/tail sentinel → null 체크 제거
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node ==null) return -1;
        moveToHead(node);   // 최근 사용으로 갱신
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
                Node lru = removeTail();    // 가장 오래된 것 evict
                map.remove(lru.key);        // Node에 key가 있어서 map도 정리 가능
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
