import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102 - Binary Tree Level Order Traversal  [정답]
 *
 * 핵심: 매 while 시작에서 큐 사이즈를 고정해 한 레벨치만 처리.
 * 불변식: while 진입 시점에 큐에는 "현재 레벨" 노드들만 들어있음.
 * 함정: levelSize를 고정 안 하면 자식까지 같이 꺼내져 레벨이 섞임. 빈 트리는 빈 리스트.
 * 복잡도: 시간 O(N), 공간 O(N).
 * 자세한 해설 → SOLUTION.md
 */
class LevelOrder {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            List<List<Integer>> results = new ArrayList<>();
            if (root == null) return results;
            q.add(root);

            while(!q.isEmpty()){
                int size = q.size();
                List<Integer> level = new ArrayList<>();
                for(int i =0; i <size; i ++){
                    TreeNode cur = q.poll();
                    level.add(cur.val);
                    if(cur.left != null) q.add(cur.left);
                    if(cur.right != null) q.add(cur.right);
                }

                results.add(level);
            }

            return results;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // [3, 9, 20, null, null, 15, 7] → [[3],[9,20],[15,7]]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> r1 = sol.levelOrder(root);
        assert r1.size() == 3 : "3 levels";
        assert r1.get(0).equals(List.of(3)) : "level 0";
        assert r1.get(1).equals(List.of(9, 20)) : "level 1";
        assert r1.get(2).equals(List.of(15, 7)) : "level 2";

        // null
        assert sol.levelOrder(null).isEmpty() : "null tree";

        // 단일 노드
        List<List<Integer>> r2 = sol.levelOrder(new TreeNode(1));
        assert r2.size() == 1 && r2.get(0).equals(List.of(1)) : "single";

        // 사용하지 않더라도 import 검증용
        Deque<Integer> _unused = new ArrayDeque<>();
        _unused.offer(0);

        System.out.println("✅ LevelOrder: All tests passed");
    }
}
