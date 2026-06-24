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
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class NumberOfIslands {

    // TODO: 필요한 필드를 선언하세요 (방향 배열, visited, 행/열 크기 등)

    static class Solution {
        public int numIslands(char[][] grid) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
        }
    }

    // TODO: 필요하면 dfs/bfs 헬퍼를 선언하세요

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
