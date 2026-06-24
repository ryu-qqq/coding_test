/**
 * LeetCode 235 - Lowest Common Ancestor of a Binary Search Tree
 *
 * 문제: BST에서 두 노드 p, q의 최소 공통 조상(LCA)을 찾는다.
 *
 * --- 인터페이스 ---
 *   TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(H) (BST 높이), 공간 O(1) (반복) 또는 O(H) (재귀)
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
class LCAofBST {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static void main(String[] args) {
        //         6
        //        / \
        //       2   8
        //      / \ / \
        //     0  4 7  9
        //       / \
        //      3   5
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        Solution sol = new Solution();

        TreeNode p = root.left;       // 2
        TreeNode q = root.right;      // 8
        assert sol.lowestCommonAncestor(root, p, q).val == 6 : "LCA(2,8) = 6";

        TreeNode p2 = root.left;            // 2
        TreeNode q2 = root.left.right;      // 4
        assert sol.lowestCommonAncestor(root, p2, q2).val == 2 : "LCA(2,4) = 2";

        TreeNode p3 = root.left.right.left;   // 3
        TreeNode q3 = root.left.right.right;  // 5
        assert sol.lowestCommonAncestor(root, p3, q3).val == 4 : "LCA(3,5) = 4";

        System.out.println("✅ LCAofBST: All tests passed");
    }
}
