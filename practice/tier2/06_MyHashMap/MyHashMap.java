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
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 면접 포인트 ---
 *   (스스로 떠올려볼 것)
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
