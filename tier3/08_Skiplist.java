/**
 * LeetCode 1206 - Design Skiplist
 *
 * 문제: 정렬된 자료구조에서 평균 O(log n)으로 search/add/erase 지원.
 *       균형 트리(Red-Black, AVL)의 확률적 대안.
 *       (Redis의 sorted set이 실제로 skiplist 기반)
 *
 * --- 인터페이스 ---
 *   search(target): boolean
 *   add(num)
 *   erase(num): boolean   (없으면 false)
 *
 * --- 시간복잡도 목표 ---
 *   평균 O(log n) (모든 연산)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   다층(level) 연결 리스트:
 *     - 레벨 0: 모든 원소를 정렬 순서로 연결 (가장 빽빽)
 *     - 레벨 1, 2, ... : 각 노드가 동전 던져 확률적으로 위 레벨로 승격 (점점 듬성)
 *
 *   class Node { int val; Node[] next; }   // next[i] = i레벨에서의 다음 노드
 *
 *   각 노드의 레벨은 randomLevel()로 결정:
 *     level = 1
 *     while random() < P and level < MAX_LEVEL: level++
 *     return level
 *     (P = 0.5 이 일반적, MAX_LEVEL = 16~32 정도)
 *
 *   search(target):
 *     curr = head
 *     for i from MAX_LEVEL-1 down to 0:
 *       while curr.next[i] != null and curr.next[i].val < target:
 *         curr = curr.next[i]
 *     curr = curr.next[0]
 *     return curr != null && curr.val == target
 *
 *   add(num):
 *     update[MAX_LEVEL] = 각 레벨에서 num보다 작은 마지막 노드
 *     curr = head
 *     for i from MAX_LEVEL-1 down to 0:
 *       while curr.next[i] != null and curr.next[i].val < num:
 *         curr = curr.next[i]
 *       update[i] = curr
 *     level = randomLevel()
 *     newNode = new Node(num, level)
 *     for i in 0..level-1:
 *       newNode.next[i] = update[i].next[i]
 *       update[i].next[i] = newNode
 *
 *   erase(num):
 *     update 배열을 search와 동일하게 채움
 *     curr = update[0].next[0]
 *     if curr == null or curr.val != num: return false
 *     for i in 0..MAX_LEVEL-1:
 *       if update[i].next[i] != curr: break
 *       update[i].next[i] = curr.next[i]
 *     return true
 *
 * --- 불변식 ---
 *   - 각 레벨의 리스트는 정렬 상태 유지
 *   - 레벨 i에 있는 노드는 레벨 0..i 모든 레벨에 존재
 *   - head는 모든 레벨에 존재 (sentinel)
 *
 * --- 면접 포인트 ---
 *   - 왜 확률적이어도 평균 O(log n)인가?
 *     각 레벨에서 평균 O(1)노드만 거치고, 레벨은 평균 O(log n)
 *   - 균형 트리 대비 장점:
 *     구현이 단순, 락 분할이 쉬워 동시성 자료구조에 유리
 *   - Redis ZSET, LevelDB MemTable이 skiplist 사용
 *
 * --- 주의 ---
 *   같은 값이 여러 번 add 될 수 있음 → erase는 1개만 지움
 */
class Skiplist {

    public Skiplist() {
        // TODO: head sentinel, MAX_LEVEL, Random 초기화
    }

    public boolean search(int target) {
        // TODO
        return false;
    }

    public void add(int num) {
        // TODO
    }

    public boolean erase(int num) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        Skiplist s = new Skiplist();
        s.add(1);
        s.add(2);
        s.add(3);
        assert !s.search(0);
        s.add(4);
        assert s.search(1);
        assert !s.erase(0);
        assert s.erase(1);
        assert !s.search(1);

        // 중복 값
        Skiplist s2 = new Skiplist();
        s2.add(1);
        s2.add(1);
        s2.add(1);
        assert s2.search(1);
        assert s2.erase(1);     // 1개만 지움
        assert s2.search(1);    // 아직 2개 남음
        assert s2.erase(1);
        assert s2.erase(1);
        assert !s2.search(1);
        assert !s2.erase(1);    // 다 지웠으므로 false

        // 큰 데이터셋
        Skiplist s3 = new Skiplist();
        for (int i = 0; i < 100; i++) s3.add(i);
        for (int i = 0; i < 100; i++) assert s3.search(i);
        for (int i = 0; i < 100; i += 2) assert s3.erase(i);
        for (int i = 0; i < 100; i++) {
            boolean expected = (i % 2 == 1);
            assert s3.search(i) == expected : "i=" + i;
        }

        System.out.println("✅ Skiplist: All tests passed");
    }
}
