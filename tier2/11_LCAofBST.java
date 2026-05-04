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
 * --- 핵심 아이디어 (수도코드) ---
 *   BST 성질을 이용한다.
 *   - p.val, q.val 둘 다 root.val 보다 작으면 → 왼쪽 서브트리로 이동
 *   - 둘 다 root.val 보다 크면 → 오른쪽 서브트리로 이동
 *   - 그 외 (p와 q가 root를 사이에 두거나, 한 쪽이 root와 같음) → root가 LCA
 *
 *   반복:
 *     while root != null:
 *       if p.val < root.val and q.val < root.val: root = root.left
 *       elif p.val > root.val and q.val > root.val: root = root.right
 *       else: return root
 *
 * --- 불변식 ---
 *   루프 진입 시점의 root는 "p, q를 모두 포함하는 가장 낮은 가능 후보"이다.
 *
 * --- 함정 ---
 *   - 일반 이진트리 LCA(LC 236)와 다르다는 점 유의 (BST 성질 활용 필수).
 *   - p, q가 같은 노드인 경우 그 노드 자체가 LCA.
 */
class LCAofBST {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // TODO
            return null;
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
