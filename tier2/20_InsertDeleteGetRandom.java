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
 * --- 핵심 아이디어 (수도코드) ---
 *   ArrayList(or Vector) + HashMap(value → index) 조합.
 *
 *   insert(val):
 *     if val in map: return false
 *     list.add(val); map[val] = list.size() - 1
 *     return true
 *
 *   remove(val):
 *     if val not in map: return false
 *     idx = map[val]; last = list.last
 *     list[idx] = last; map[last] = idx     // 마지막 원소를 idx 자리로 이동
 *     list.removeLast(); map.remove(val)
 *     return true
 *
 *   getRandom():
 *     return list[ random.nextInt(list.size()) ]
 *
 * --- 불변식 ---
 *   - list와 map은 동일한 원소 집합을 표현한다.
 *   - 모든 v에 대해 list[map[v]] == v.
 *
 * --- 함정 ---
 *   - remove에서 "삭제할 인덱스가 마지막 인덱스인 경우"도 일관 처리.
 *   - List에서 임의 인덱스 remove(O(N)) 대신 last로 swap 후 removeLast(O(1))를 써야 함.
 *   - getRandom 호출 시 비어있을 일은 LC에서 보장하지 않지만 호출 안 됨.
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
        // TODO
        return false;
    }

    public boolean remove(int val) {
        // TODO
        return false;
    }

    public int getRandom() {
        // TODO
        return -1;
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
