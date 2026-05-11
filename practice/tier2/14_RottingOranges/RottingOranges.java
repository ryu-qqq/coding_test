/**
 * LeetCode 994 - Rotting Oranges
 *
 * 문제: 그리드에서
 *   0 = 빈칸, 1 = 신선한 오렌지, 2 = 썩은 오렌지.
 *   매 분 썩은 오렌지가 4방향 인접한 신선 오렌지를 썩게 한다.
 *   모든 신선이 썩는 데 걸리는 분(최단 시간) 또는 불가능하면 -1.
 *
 * --- 인터페이스 ---
 *   int orangesRotting(int[][] grid)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(M*N), 공간 O(M*N)
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
class RottingOranges {

    static class Solution {
        public int orangesRotting(int[][] grid) {
            // TODO: 다중 출발 BFS
            return -1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] g1 = {
            {2,1,1},
            {1,1,0},
            {0,1,1}
        };
        assert sol.orangesRotting(g1) == 4 : "g1 should be 4 minutes";

        int[][] g2 = {
            {2,1,1},
            {0,1,1},
            {1,0,1}
        };
        assert sol.orangesRotting(g2) == -1 : "g2 unreachable -> -1";

        int[][] g3 = {{0, 2}};
        assert sol.orangesRotting(g3) == 0 : "no fresh -> 0";

        int[][] g4 = {{0}};
        assert sol.orangesRotting(g4) == 0 : "all empty -> 0";

        System.out.println("✅ RottingOranges: All tests passed");
    }
}
