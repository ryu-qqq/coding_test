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
 */
class MyHashMap {

    private static final int SIZE = 1000;
    private LinkedList<int[]>[] buckets;

    public MyHashMap() {
        buckets = new LinkedList[SIZE];
        for(int i =0; i <SIZE; i ++){
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key){
        return key % SIZE;
    }

    public void put(int key, int value) {
        LinkedList<int[]> bucket = buckets[hash(key)];
        for(int[] entry : bucket){
            if(entry[0] == key){
                entry[1] = value;
                return;
            }
        }
        bucket.add(new int[]{key, value});
    }

    public int get(int key) {
        LinkedList<int[]> bucket = buckets[hash(key)];
        for(int[] entry : bucket){
            if(entry[0] == key){
                return entry[1];
            }
        }

        return -1;
    }

    public void remove(int key) {
        LinkedList<int[]> bucket = buckets[hash(key)];
        Iterator<int[]> it =  bucket.iterator();
        while(it.hasNext()){
            if(it.next()[0] == key){
                it.remove();
                return;
            }
        }

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
