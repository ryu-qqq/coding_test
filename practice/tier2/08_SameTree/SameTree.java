/**
 * LeetCode 100 - Same Tree
 *
 * 문제: 두 이진 트리가 구조와 값 모두 동일한지 판별한다.
 *
 * --- 인터페이스 ---
 *   boolean isSameTree(TreeNode p, TreeNode q)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(H)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 */
class SameTree {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            // TODO
            return false;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 동일 트리: [1,2,3] vs [1,2,3]
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);
        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);
        assert sol.isSameTree(p1, q1) : "should be same";

        // 구조 다름: [1,2] vs [1,null,2]
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);
        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);
        assert !sol.isSameTree(p2, q2) : "structure differs";

        // 값 다름
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);
        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);
        assert !sol.isSameTree(p3, q3) : "values differ";

        // 둘 다 null
        assert sol.isSameTree(null, null) : "both null";

        System.out.println("✅ SameTree: All tests passed");
    }
}
