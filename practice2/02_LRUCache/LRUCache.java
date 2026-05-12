/**
 * ═══════════════════════════════════════════════════════════
 *  Problem: LRU Cache — Design with O(1) operations
 * ═══════════════════════════════════════════════════════════
 *
 * Design a data structure for the Least Recently Used (LRU) cache.
 * Both get(key) and put(key, value) must run in O(1).
 *
 * `java.util.LinkedHashMap` 사용 금지. HashMap + Doubly LinkedList 직접 결합.
 *
 * --- Function Description ---
 *
 *   class LRUCache {
 *     LRUCache(int capacity)
 *     int    get(int key)               // returns value, or -1 if not found
 *     void   put(int key, int value)    // insert/update; evicts LRU if over capacity
 *   }
 *
 * --- Behavior ---
 *
 *   • get(key):
 *       - 키가 있으면 value 반환 + 그 키를 "가장 최근 사용" 위치로 이동.
 *       - 없으면 -1 반환.
 *
 *   • put(key, value):
 *       - 이미 있는 키 → value 갱신 + 가장 최근 위치로 이동.
 *       - 새 키 → 추가. capacity 초과 시 LRU (가장 오래된 항목) 제거.
 *
 *   • LRU 정의: get/put 으로 가장 마지막에 접근된 시점이 가장 오래된 키.
 *
 * --- Constraints ---
 *
 *   1 <= capacity <= 3000
 *   1 <= key, value <= 10^4
 *   At most 10^5 calls to get and put combined.
 *
 * --- Sample Operations (capacity = 2) ---
 *
 *   put(1, 1)
 *   put(2, 2)
 *   get(1)         → 1
 *   put(3, 3)      // capacity 초과 → key 2 제거 (1 이 더 최근)
 *   get(2)         → -1
 *   put(4, 4)      // capacity 초과 → key 1 제거 (3 이 더 최근)
 *   get(1)         → -1
 *   get(3)         → 3
 *   get(4)         → 4
 *
 * --- Time Complexity Target ---
 *
 *   get: O(1)
 *   put: O(1)
 *
 * --- Hints (스스로 떠올려볼 것) ---
 *
 *   • HashMap<Integer, Node> : key → 노드 직접 접근 (O(1) 검색)
 *   • Doubly LinkedList     : 사용 순서 유지. head 쪽이 "최근", tail 쪽이 "오래된"
 *   • 더미 head, 더미 tail 두 개 두면 boundary 케이스 제거됨 (null 체크 없이 깔끔)
 *   • get/put 시 노드를 list 의 가장 앞 (head 바로 뒤) 으로 이동
 *   • 용량 초과 시 tail 바로 앞 노드 제거 + map 에서도 제거
 */

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private static class Node {
        int key, val;
        Node prev, next;
        Node(int k, int v) { key = k; val = v; }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;     // 더미 노드 (boundary 제거)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // ───────────────────────────────────────────
    // get: 키 있으면 value + 가장 앞으로 이동, 없으면 -1
    // ───────────────────────────────────────────
    public int get(int key) {
        Node node = map.get(key);
        if(node == null) return -1;
        moveToFront(node);
        return node.val;
    }

    // ───────────────────────────────────────────
    // put: 이미 있으면 갱신 + 앞으로 이동.
    //       없으면 추가. 용량 초과 시 LRU 제거.
    // ───────────────────────────────────────────
    public void put(int key, int value) {
        Node node = map.get(key);
        if(node == null){
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToFront(newNode);

            if(map.size() > capacity){
                Node lru = removeTail();
                map.remove(lru.key);
            }
        }else{
            node.val = value;
            moveToFront(node);
        }
    }

    private void moveToFront(Node node) {   // 자주 쓰는 패턴 묶기
        unlink(node);
        addToFront(node);
    }


    // ── helpers (직접 구현) ──
    // 노드를 list 에서 떼어냄 (prev <-> next 직결)
    private Node removeTail() {
        Node remove = tail.prev;
        unlink(remove);
        return remove;
    }

    private void unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    // 노드를 head 바로 뒤에 삽입 (= 가장 최근 위치)
    private void addToFront(Node node) {
        Node temp = head.next;
                
        node.next = temp;
        node.prev = head;

        temp.prev = node;
        head.next = node;
    }


    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);
        c.put(1, 1);
        c.put(2, 2);
        assert c.get(1) == 1 : "get(1) should be 1";

        c.put(3, 3);                  // 2 evicted (1 더 최근에 접근됨)
        assert c.get(2) == -1 : "2 evicted";

        c.put(4, 4);                  // 1 evicted (3 이 더 최근)
        assert c.get(1) == -1 : "1 evicted";
        assert c.get(3) == 3;
        assert c.get(4) == 4;

        // 같은 키 업데이트
        LRUCache c2 = new LRUCache(2);
        c2.put(1, 100);
        c2.put(1, 200);               // 값 갱신
        assert c2.get(1) == 200 : "update value";
        c2.put(2, 2);                 // capacity 안 넘침 (1 갱신만 했으니)
        c2.put(3, 3);                 // 1 또는 2 중 LRU 가 evict
        assert c2.get(1) == -1 : "1 evicted (2 가 1 보다 최근)";
        assert c2.get(2) == 2;
        assert c2.get(3) == 3;

        // capacity 1
        LRUCache c3 = new LRUCache(1);
        c3.put(1, 1);
        c3.put(2, 2);
        assert c3.get(1) == -1;
        assert c3.get(2) == 2;

        // get 이 순서를 바꾸는지
        LRUCache c4 = new LRUCache(2);
        c4.put(1, 1);
        c4.put(2, 2);
        c4.get(1);                    // 1 이 가장 최근
        c4.put(3, 3);                 // 2 evicted (1 이 더 최근)
        assert c4.get(2) == -1;
        assert c4.get(1) == 1;
        assert c4.get(3) == 3;

        System.out.println("✅ LRUCache: All tests passed");
    }
}
