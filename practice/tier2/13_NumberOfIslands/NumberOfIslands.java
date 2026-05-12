import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 200 - Number of Islands
 *
 * 문제: '1'(land)과 '0'(water)로 이루어진 그리드에서 섬의 개수를 구한다.
 *   섬은 상하좌우로 연결된 '1'들의 그룹.
 *
 * --- 인터페이스 ---
 *   int numIslands(char[][] grid)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(M*N), 공간 O(M*N) (재귀 스택 또는 큐)
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
class NumberOfIslands {

    static class Solution {

        static final int[][] xy = {{-1,0}, {1,0}, {0, 1}, {0,-1}};
        static int cols, rows;

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) return 0;
            int cnt = 0;
            cols = grid.length;
            rows = grid[0].length;

            for(int i =0; i <cols; i ++){
                for(int j =0; j<rows; j ++){
                    if(grid[i][j] == '1'){
                        cnt++;
                        bfs(grid, i, j);
                    }
                }
            }
            return cnt;
        }

        void bfs(char[][] grid, int i, int j) {

            Deque<int[]>  q = new ArrayDeque<>();
            q.offer(new int[]{i, j});
            grid[i][j] = '0';

            while (!q.isEmpty()) {
                int[] cur = q.poll();

                for(int[] ar : xy){
                    int curX = ar[0] + cur[0];
                    int curY = ar[1] + cur [1];

                    if(curX >=0 && curX < cols & curY >=0 && curY < rows && grid[curX][curY] == '1'){
                        q.offer(new int[]{curX, curY});
                        grid[curX][curY] = '0';
                    }
                }
            }

        }


    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        char[][] g1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        assert sol.numIslands(g1) == 1 : "g1 should be 1 island";

        char[][] g2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        assert sol.numIslands(g2) == 3 : "g2 should be 3 islands";

        // 빈 그리드
        char[][] g3 = {};
        assert sol.numIslands(g3) == 0 : "empty grid";

        // 모두 물
        char[][] g4 = {{'0','0'},{'0','0'}};
        assert sol.numIslands(g4) == 0 : "all water";

        System.out.println("✅ NumberOfIslands: All tests passed");
    }
}
