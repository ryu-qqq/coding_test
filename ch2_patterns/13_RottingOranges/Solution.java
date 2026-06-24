import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 994 - Rotting Oranges  [정답]
 *
 * 핵심: 모든 썩은 오렌지(2)를 동시에 큐에 넣고 분 단위(레벨) BFS로 신선(1)을 전염시킨다.
 * 불변식: 매 레벨 시작 시 큐에는 "직전 분에 막 썩은 오렌지들"만 들어 있다.
 * 복잡도: 시간 O(M*N), 공간 O(M*N).
 * 자세한 해설 → SOLUTION.md
 */
class RottingOranges {

    static int rows;
    static int cols;
    static int freshCnt;

    static class Solution {
        public int orangesRotting(int[][] grid) {
            rows = grid.length;
            cols = grid[0].length;
            freshCnt = 0;  // static 필드 재초기화 (여러 호출 간 상태 누수 방지)
            Queue<int[]> q = new ArrayDeque<>();

            for(int i =0; i<rows; i ++){
                for(int j =0; j <cols; j ++){
                    if(grid[i][j] == 1) freshCnt++;
                    if(grid[i][j] == 2) q.offer(new int[]{i, j});
                }
            }

            if(freshCnt ==0) return 0;
            int min =0;
            int[][] dir = {{-1,0}, {1,0}, {0, -1}, {0, 1}};

            while(!q.isEmpty() && freshCnt > 0){
                int size = q.size();

                for(int i =0; i< size; i ++){
                    int[] cur = q.poll();
                    for(int[] d : dir){
                        int curX = cur[0] + d[0];
                        int curY = cur[1] + d[1];
                        if(curX >=0 && curY >=0 && curX < rows && curY < cols && grid[curX][curY] == 1){
                            grid[curX][curY] = 2;
                            freshCnt --;
                            q.offer(new int[]{curX, curY});
                        }
                    }
                }

                min++;
            }

            return freshCnt == 0 ? min : -1;
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
