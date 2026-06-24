import java.util.*;
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
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class MyHashMap {

    // TODO: 필요한 필드를 선언하세요 (버킷 배열 등)

    public MyHashMap() {
        // TODO: 초기화
    }

    private int hash(int key){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public void put(int key, int value) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public int get(int key) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public void remove(int key) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
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
