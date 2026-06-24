import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102 - Binary Tree Level Order Traversal
 *
 * 문제: 이진 트리를 레벨 단위로 순회해 각 레벨의 값을 리스트로 반환한다.
 *
 * --- 인터페이스 ---
 *   List<List<Integer>> levelOrder(TreeNode root)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(N)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class LevelOrder {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // [3, 9, 20, null, null, 15, 7] → [[3],[9,20],[15,7]]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> r1 = sol.levelOrder(root);
        assert r1.size() == 3 : "3 levels";
        assert r1.get(0).equals(List.of(3)) : "level 0";
        assert r1.get(1).equals(List.of(9, 20)) : "level 1";
        assert r1.get(2).equals(List.of(15, 7)) : "level 2";

        // null
        assert sol.levelOrder(null).isEmpty() : "null tree";

        // 단일 노드
        List<List<Integer>> r2 = sol.levelOrder(new TreeNode(1));
        assert r2.size() == 1 && r2.get(0).equals(List.of(1)) : "single";

        // 사용하지 않더라도 import 검증용
        Deque<Integer> _unused = new ArrayDeque<>();
        _unused.offer(0);

        System.out.println("✅ LevelOrder: All tests passed");
    }
}
