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
 */
class LFUCache {

    public LFUCache(int capacity) {
        // TODO: capacity, keyToNode, freqToList, minFreq, size 초기화
    }

    public int get(int key) {
        // TODO
        return -1;
    }

    public void put(int key, int value) {
        // TODO
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
