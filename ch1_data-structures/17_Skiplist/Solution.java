import java.util.Random;

/**
 * LeetCode 1206 - Design Skiplist  [정답]
 *
 * 핵심: 여러 레벨의 정렬 연결 리스트를 겹쳐 "건너뛰기" 검색. 상위 레벨일수록 노드가 듬성듬성해
 *       이진 탐색처럼 평균 O(log n)에 위치를 좁힌다. 레벨은 동전 던지기(50%)로 확률 결정.
 * 불변식: 각 레벨 리스트는 정렬 상태. 레벨 k에 있는 노드는 레벨 0..k 모두에 존재.
 * 복잡도: search/add/erase 평균 O(log n).
 * 자세한 해설 → SOLUTION.md
 */
class Skiplist {

    private static final int MAX_LEVEL = 16;
    private static final double P = 0.5;

    private static class Node {
        int val;
        Node[] forward;          // forward[i] = 레벨 i에서 다음 노드
        Node(int val, int level) {
            this.val = val;
            this.forward = new Node[level + 1];
        }
    }

    private final Node head;     // sentinel (val 의미 없음), 항상 MAX_LEVEL
    private int level;           // 현재 사용 중인 최고 레벨
    private final Random rng;

    public Skiplist() {
        head = new Node(-1, MAX_LEVEL);
        level = 0;
        rng = new Random();
    }

    // 동전 던지기: 0.5 확률로 레벨 +1
    private int randomLevel() {
        int lvl = 0;
        while (lvl < MAX_LEVEL && rng.nextDouble() < P) lvl++;
        return lvl;
    }

    public boolean search(int target) {
        Node cur = head;
        // 위 레벨부터 내려가며 target 직전까지 전진
        for (int i = level; i >= 0; i--) {
            while (cur.forward[i] != null && cur.forward[i].val < target) {
                cur = cur.forward[i];
            }
        }
        cur = cur.forward[0];    // 레벨 0에서 target 후보
        return cur != null && cur.val == target;
    }

    public void add(int num) {
        Node[] update = new Node[MAX_LEVEL + 1];  // 각 레벨에서 새 노드의 직전 노드
        Node cur = head;
        for (int i = level; i >= 0; i--) {
            while (cur.forward[i] != null && cur.forward[i].val < num) {
                cur = cur.forward[i];
            }
            update[i] = cur;
        }

        int newLevel = randomLevel();
        if (newLevel > level) {
            for (int i = level + 1; i <= newLevel; i++) update[i] = head;
            level = newLevel;
        }

        Node node = new Node(num, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            node.forward[i] = update[i].forward[i];
            update[i].forward[i] = node;
        }
    }

    public boolean erase(int num) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node cur = head;
        for (int i = level; i >= 0; i--) {
            while (cur.forward[i] != null && cur.forward[i].val < num) {
                cur = cur.forward[i];
            }
            update[i] = cur;
        }

        Node target = cur.forward[0];
        if (target == null || target.val != num) return false;   // 없음

        // target이 존재하는 모든 레벨에서 링크 우회 (한 개만 제거)
        for (int i = 0; i <= level; i++) {
            if (update[i].forward[i] != target) break;
            update[i].forward[i] = target.forward[i];
        }

        // 최상위 레벨이 비면 level 낮추기
        while (level > 0 && head.forward[level] == null) level--;
        return true;
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
