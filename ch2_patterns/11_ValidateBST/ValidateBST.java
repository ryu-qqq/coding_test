/**
 * LeetCode 98 - Validate Binary Search Tree
 *
 * 문제: 주어진 이진 트리가 BST인지 검증한다.
 *   - 왼쪽 서브트리의 모든 값은 root.val 보다 strictly 작다.
 *   - 오른쪽 서브트리의 모든 값은 root.val 보다 strictly 크다.
 *   - 좌/우 서브트리 또한 BST 이다.
 *
 * --- 인터페이스 ---
 *   boolean isValidBST(TreeNode root)
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
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class ValidateBST {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public boolean isValidBST(TreeNode root) {
            // TODO: 구현 (힌트: 범위 (lower, upper)를 좁혀가는 재귀 보조 메서드를 만들어 보세요)
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // [2, 1, 3] → true
        TreeNode r1 = new TreeNode(2);
        r1.left = new TreeNode(1);
        r1.right = new TreeNode(3);
        assert sol.isValidBST(r1) : "[2,1,3] is BST";

        // [5,1,4,null,null,3,6] → false (4 < 5인데 5의 오른쪽에 위치)
        TreeNode r2 = new TreeNode(5);
        r2.left = new TreeNode(1);
        r2.right = new TreeNode(4);
        r2.right.left = new TreeNode(3);
        r2.right.right = new TreeNode(6);
        assert !sol.isValidBST(r2) : "[5,1,4,..] not BST";

        // 동일 값은 BST가 아님 (strictly)
        TreeNode r3 = new TreeNode(1);
        r3.left = new TreeNode(1);
        assert !sol.isValidBST(r3) : "duplicates not BST";

        // 단일 노드
        assert sol.isValidBST(new TreeNode(0)) : "single node BST";

        System.out.println("✅ ValidateBST: All tests passed");
    }
}
