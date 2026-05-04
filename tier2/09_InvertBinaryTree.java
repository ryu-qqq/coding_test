/**
 * LeetCode 226 - Invert Binary Tree
 *
 * 문제: 이진 트리의 모든 노드의 좌/우 자식을 swap 한다.
 *
 * --- 인터페이스 ---
 *   TreeNode invertTree(TreeNode root)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(H)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   재귀 종료 시 해당 서브트리는 좌우 거울 상태가 되어 있다.
 *
 * --- 함정 ---
 *   - swap 전에 left/right 둘 중 하나를 임시 변수로 잡지 않으면 덮어써져 잘못 swap 된다.
 *   - 빈 트리 입력 처리.
 */
class InvertBinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public TreeNode invertTree(TreeNode root) {
            // TODO
            return null;
        }
    }

    // 헬퍼: 트리를 in-order로 직렬화 (테스트 비교용)
    private static String inorder(TreeNode root) {
        if (root == null) return "#";
        return "(" + inorder(root.left) + " " + root.val + " " + inorder(root.right) + ")";
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        //     4              4
        //    / \            / \
        //   2   7    →     7   2
        //  / \ / \        / \ / \
        // 1  3 6  9      9  6 3  1
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeNode r = sol.invertTree(root);
        // 반전 후 in-order는 [9, 7, 6, 4, 3, 2, 1] 순으로 방문되어야 함
        String got = inorder(r);
        assert got.contains("9") && got.contains("1") : "inverted contains all";
        assert r != null && r.left != null && r.left.val == 7 : "root.left should be 7 after invert";
        assert r.right != null && r.right.val == 2 : "root.right should be 2 after invert";

        // null
        assert sol.invertTree(null) == null : "null tree";

        // 단일 노드
        TreeNode single = sol.invertTree(new TreeNode(1));
        assert single != null && single.val == 1;

        System.out.println("✅ InvertBinaryTree: All tests passed");
    }
}
