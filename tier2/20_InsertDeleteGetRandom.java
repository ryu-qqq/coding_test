import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * LeetCode 380 - Insert Delete GetRandom O(1)
 *
 * 문제: insert / remove / getRandom 모두 평균 O(1)인 자료구조.
 *
 * --- 인터페이스 ---
 *   boolean insert(int val)   // 없으면 추가, true / 이미 있으면 false
 *   boolean remove(int val)   // 있으면 제거, true / 없으면 false
 *   int     getRandom()       // 들어 있는 값 중 균등 랜덤 추출
 *
 * --- 시간복잡도 목표 ---
 *   모두 평균 O(1)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 */
class InsertDeleteGetRandom {

    private final List<Integer> list;
    private final Map<Integer, Integer> idxMap;
    private final Random rng;

    public InsertDeleteGetRandom() {
        this.list = new ArrayList<>();
        this.idxMap = new HashMap<>();
        this.rng = new Random();
        // TODO: 필요시 추가 초기화
    }

    public boolean insert(int val) {
        if(idxMap.containsKey(val)) return false;
        idxMap.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(!idxMap.containsKey(val)) return false;
        int idx = idxMap.get(val);
        int lastVal = list.get(list.size() - 1);

        list.set(idx, lastVal);
        idxMap.put(lastVal, idx);

        list.remove(list.size() - 1);
        idxMap.remove(val);

        return true;
    }

    public int getRandom() {
        return list.get(rng.nextInt(list.size()));
    }

    public static void main(String[] args) {
        InsertDeleteGetRandom s = new InsertDeleteGetRandom();
        assert s.insert(1) : "insert 1 → true";
        assert !s.remove(2) : "remove 2 (없음) → false";
        assert s.insert(2) : "insert 2 → true";
        // getRandom은 1 또는 2
        int g = s.getRandom();
        assert g == 1 || g == 2 : "random in {1,2}";
        assert s.remove(1) : "remove 1 → true";
        assert !s.insert(2) : "2 already in → false";
        assert s.getRandom() == 2 : "only 2 left";

        // 중복 insert 거절
        InsertDeleteGetRandom s2 = new InsertDeleteGetRandom();
        s2.insert(0);
        assert !s2.insert(0) : "duplicate insert false";
        assert s2.remove(0) : "remove 0 true";
        assert !s2.remove(0) : "remove 0 again false";

        System.out.println("✅ InsertDeleteGetRandom: All tests passed");
    }
}
