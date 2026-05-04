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
 *   바깥 루프가 (r,c)를 지나는 시점에서, (r,c)가 '1'이면 그 셀이 속한 섬은 아직 카운트 안 됨.
 *
 * --- 함정 ---
 *   - 입력 그리드를 변경해도 되는지 문제 조건 확인 (LC는 허용).
 *     변경 금지라면 별도 visited[][] 배열을 써야 함.
 *   - 대각선은 연결로 치지 않음 (4방향만).
 *   - 매우 큰 그리드에서 재귀 깊이로 StackOverflow 가능 → BFS 권장하기도 함.
 */
class NumberOfIslands {

    static class Solution {
        public int numIslands(char[][] grid) {
            // TODO: 모든 셀을 순회하며 '1' 만나면 DFS/BFS로 가라앉히고 count++
            return 0;
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
