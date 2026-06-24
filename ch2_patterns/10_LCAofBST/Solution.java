/**
 * LeetCode 235 - Lowest Common Ancestor of a Binary Search Tree  [정답]
 *
 * 핵심: BST 성질 — p, q가 root보다 둘 다 작으면 좌, 둘 다 크면 우, 그 외엔 root가 LCA.
 * 불변식: 루프 진입 시 cur은 "p, q를 모두 포함할 수 있는 가장 낮은 후보".
 * 함정: 일반 이진트리 LCA(LC 236)와 다름 — BST 성질을 써야 함. p == q도 자연 처리.
 * 복잡도: 시간 O(H), 공간 O(1) (반복).
 * 자세한 해설 → SOLUTION.md
 */
class LCAofBST {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
                TreeNode cur = root;

                while(cur != null){
                    if(p.val < cur.val && q.val < cur.val){
                        cur = cur.left;
                    }else if(p.val > cur.val && q.val > cur.val){
                        cur = cur.right;
                    }else{
                        return cur;
                    }
                }
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
