import java.util.Random;

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
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 면접 포인트 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 주의 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
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

    // TODO: 필요한 필드를 선언하세요 (head sentinel, level, Random 등)

    public Skiplist() {
        // TODO: head sentinel, level, Random 초기화
    }

    // 동전 던지기로 새 노드의 레벨을 결정
    private int randomLevel() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean search(int target) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public void add(int num) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean erase(int num) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
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
