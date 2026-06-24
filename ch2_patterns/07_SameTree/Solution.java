/**
 * LeetCode 100 - Same Tree  [정답]
 *
 * 핵심: 두 트리를 같은 좌표로 동시에 따라가며 비교.
 * 불변식: 재귀 호출 시점 p, q는 두 트리의 같은 좌표에 위치.
 * 함정: null 체크 순서 — 둘 다 null 먼저, 그 다음 한쪽 null.
 * 복잡도: 시간 O(N), 공간 O(H).
 * 자세한 해설 → SOLUTION.md
 */
class SameTree {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if(p == null && q == null) return true;
            if(p == null || q == null) return false;

            return p.val == q.val &&
            isSameTree(p.left, q.left) &&
            isSameTree(p.right, q.right);
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
