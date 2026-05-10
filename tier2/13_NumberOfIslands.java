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

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static int row;
    static int cols;

    static class Solution {
        public int numIslands(char[][] grid) {
            row = grid.length;
            if (row == 0) return 0;
            cols = grid[0].length;
            int cnt = 0;
            visited = new boolean[row][cols];
            
            for(int i =0; i <row; i ++){
                for(int j=0; j <cols; j ++){
                    if(!visited[i][j] && grid[i][j] =='1'){
                        dfs(grid, i, j);
                        cnt++;
                    }
                }
            }


            return cnt;
        }
    }

    static void dfs(char[][] grid, int i, int j){
        visited[i][j] = true;
        for(int k =0; k<4; k ++){
            int curX = i + dx[k];
            int curY = j + dy[k];

            if(curX < row && curX >= 0 && curY >= 0 && curY < cols 
                && !visited[curX][curY]
                && grid[curX][curY] == '1'
            ){
                visited[curX][curY] = true;
                dfs(grid, curX, curY);
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
