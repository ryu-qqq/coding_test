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
