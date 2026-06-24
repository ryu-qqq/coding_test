/**
 * LeetCode 104 - Maximum Depth of Binary Tree  [정답]
 *
 * 핵심: 깊이 = 1 + max(좌 깊이, 우 깊이). null이면 0.
 * 불변식: 재귀 반환값은 그 서브트리의 최대 깊이.
 * 함정: null → 0 base case를 빠뜨리면 NPE. 한쪽만 자식 있어도 양쪽 max 비교 필요.
 * 복잡도: 시간 O(N), 공간 O(H) (재귀 스택).
 * 자세한 해설 → SOLUTION.md
 */
class MaxDepth {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // [3, 9, 20, null, null, 15, 7] → 3
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        assert sol.maxDepth(root) == 3 : "depth should be 3";

        // 빈 트리
        assert sol.maxDepth(null) == 0 : "null should be 0";

        // 단일 노드
        assert sol.maxDepth(new TreeNode(1)) == 1 : "single node";

        // 한쪽으로 치우친 트리: 1 -> 2 -> 3
        TreeNode skew = new TreeNode(1);
        skew.right = new TreeNode(2);
        skew.right.right = new TreeNode(3);
        assert sol.maxDepth(skew) == 3 : "skewed tree";

        System.out.println("✅ MaxDepth: All tests passed");
    }
}
