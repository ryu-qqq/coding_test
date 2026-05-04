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
 *   범위 방식: 재귀 진입 시 node의 값은 (lower, upper) 범위 안에 들어야 한다.
 *
 * --- 함정 ---
 *   - 단순히 node.left.val < node.val, node.right.val > node.val 만 체크하면
 *     서브트리 전체 조건을 놓친다(예: 5의 왼쪽 서브트리에 6이 끼는 경우).
 *   - 값 범위가 Integer.MIN_VALUE / MAX_VALUE 까지 올 수 있어 long을 쓰거나
 *     Long/null 박싱을 권장.
 */
class ValidateBST {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public boolean isValidBST(TreeNode root) {
            // TODO: 범위 기반 재귀 또는 in-order 비교
            return false;
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
