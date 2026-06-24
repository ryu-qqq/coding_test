import java.util.*;
/**
 * LeetCode 706 - Design HashMap  [정답]
 *
 * 핵심: 고정 크기 버킷 배열 + 버킷마다 LinkedList<int[]>로 분리 연쇄(separate chaining).
 *       hash = key % SIZE 로 버킷 선택, 같은 버킷은 선형 탐색.
 * 복잡도: 충돌 적으면 모든 연산 amortized O(1), 최악(전부 같은 버킷) O(N).
 * 자세한 해설 → SOLUTION.md
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
                entry[1] = value;   // 이미 있으면 덮어쓰기
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

        return -1;   // 없으면 -1
    }

    public void remove(int key) {
        LinkedList<int[]> bucket = buckets[hash(key)];
        Iterator<int[]> it =  bucket.iterator();
        while(it.hasNext()){
            if(it.next()[0] == key){
                it.remove();        // 순회 중 안전 삭제는 Iterator.remove()
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
