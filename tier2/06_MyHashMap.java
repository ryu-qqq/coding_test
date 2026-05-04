/**
 * LeetCode 706 - Design HashMap
 *
 * 문제: int → int 해시맵 (Java HashMap 사용 금지)
 *
 * --- 인터페이스 ---
 *   put(key, value)
 *   get(key): int   (없으면 -1)
 *   remove(key)
 *
 * --- 시간복잡도 목표 ---
 *   모든 연산 amortized O(1)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   고정 버킷 배열 + 체이닝 (LinkedList of Entry)
 *
 *   class Entry { int key, value; Entry next; }
 *
 *   bucket(key) = (key % capacity + capacity) % capacity   // 음수 방어
 *
 *   put(k, v):
 *     b = buckets[bucket(k)]
 *     for entry in b:
 *       if entry.key == k:
 *         entry.value = v
 *         return
 *     b.add(new Entry(k, v))     // 또는 head insertion
 *
 *   get(k):
 *     for entry in buckets[bucket(k)]:
 *       if entry.key == k: return entry.value
 *     return -1
 *
 *   remove(k):
 *     buckets[bucket(k)] 에서 key가 k인 엔트리 제거
 *
 * --- 도전 과제 ---
 *   load factor 기반 resize:
 *     size / capacity > 0.75 → 2배 확장 + 모든 원소 rehash
 *
 * --- 불변식 ---
 *   같은 key는 한 번만 존재한다 (덮어쓰기).
 *
 * --- 면접 포인트 ---
 *   - 충돌 해결: 체이닝 vs 오픈 어드레싱
 *   - 왜 capacity는 보통 소수(prime)나 2의 거듭제곱인가?
 *   - load factor는 왜 0.75가 일반적인가? (공간 vs 시간 trade-off)
 */
class MyHashMap {

    public MyHashMap() {
        // TODO: 버킷 배열 초기화 (예: capacity = 769 또는 1024)
    }

    public void put(int key, int value) {
        // TODO
    }

    public int get(int key) {
        // TODO
        return -1;
    }

    public void remove(int key) {
        // TODO
    }

    public static void main(String[] args) {
        MyHashMap m = new MyHashMap();
        m.put(1, 1);
        m.put(2, 2);
        assert m.get(1) == 1;
        assert m.get(3) == -1;          // 없음

        m.put(2, 1);                     // 덮어쓰기
        assert m.get(2) == 1;

        m.remove(2);
        assert m.get(2) == -1;

        // 충돌 유발 (큰 키)
        m.put(1000, 100);
        m.put(2000, 200);
        m.put(3000, 300);
        assert m.get(1000) == 100;
        assert m.get(2000) == 200;
        assert m.get(3000) == 300;

        // 0과 음수 키
        m.put(0, 999);
        assert m.get(0) == 999;

        System.out.println("✅ MyHashMap: All tests passed");
    }
}
