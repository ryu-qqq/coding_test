/**
 * LeetCode 104 - Maximum Depth of Binary Tree
 *
 * 문제: 이진 트리의 최대 깊이(루트 → 가장 먼 leaf 까지 노드 수)를 구한다.
 *
 * --- 인터페이스 ---
 *   int maxDepth(TreeNode root)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(H) (H는 트리 높이, 재귀 스택)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   재귀 정의:
 *     maxDepth(node) = 0                          if node == null
 *                    = 1 + max(maxDepth(left),
 *                              maxDepth(right))   otherwise
 *
 *   반복 BFS 버전: 큐를 이용해 레벨 단위로 카운트.
 *
 * --- 불변식 ---
 *   재귀 호출 직후 반환값은 "그 서브트리의 최대 깊이".
 *
 * --- 함정 ---
 *   - 빈 트리 (root == null) → 0 반환.
 *   - 한쪽만 자식이 있는 경우, 양쪽 max를 비교해야 함 (depth는 leaf까지 거리).
 */
class MaxDepth {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public int maxDepth(TreeNode root) {
            // TODO
            return 0;
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
