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
 * --- 핵심 아이디어 (수도코드) ---
 *   HashMap<key, Node> + Doubly Linked List (DLL)
 *
 *   DLL 구조:
 *     head ←→ [most recent] ←→ ... ←→ [least recent] ←→ tail
 *     (head, tail은 dummy sentinel — null 체크 줄이기 위함)
 *
 *   class Node { int key, value; Node prev, next; }
 *
 *   get(key):
 *     if !map.contains(key): return -1
 *     node = map.get(key)
 *     moveToHead(node)
 *     return node.value
 *
 *   put(key, value):
 *     if map.contains(key):
 *       node = map.get(key)
 *       node.value = value
 *       moveToHead(node)
 *     else:
 *       node = new Node(key, value)
 *       map.put(key, node)
 *       addToHead(node)
 *       if map.size() > capacity:
 *         evicted = removeTail()
 *         map.remove(evicted.key)        // ← Node에 key가 저장돼있어야 가능!
 *
 *   helper:
 *     addToHead(node):    head ↔ node ↔ head.next
 *     removeNode(node):   prev ↔ next 로 우회
 *     moveToHead(node):   removeNode + addToHead
 *     removeTail():       node = tail.prev; removeNode(node); return node
 *
 * --- 불변식 ---
 *   - map.size() <= capacity
 *   - DLL의 노드 집합 == map의 value 집합
 *   - head 쪽이 가장 최근, tail 쪽이 가장 오래됨
 *
 * --- 핵심 트릭 ---
 *   eviction 시 map에서도 지워야 하므로 Node에 반드시 key를 저장!
 *
 * --- 왜 DLL인가? ---
 *   - 임의 노드 제거가 O(1) (prev/next 포인터로 즉시 우회)
 *   - 단방향 리스트는 prev를 모르므로 O(n) 검색 필요
 *
 * --- 대안 ---
 *   LinkedHashMap을 쓰면 한 줄로 가능하지만, 면접에선 직접 구현이 출제됨.
 */
class LRUCache {

    public LRUCache(int capacity) {
        // TODO: capacity 저장, HashMap, dummy head/tail 노드 초기화
    }

    public int get(int key) {
        // TODO
        return -1;
    }

    public void put(int key, int value) {
        // TODO
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
