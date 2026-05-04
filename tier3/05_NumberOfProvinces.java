/**
 * LeetCode 547 - Number of Provinces
 *
 * 문제: isConnected[i][j] = 1 이면 도시 i, j가 직접 연결.
 *   직접/간접으로 연결된 도시 그룹의 개수(=연결 컴포넌트 수)를 구한다.
 *
 * --- 인터페이스 ---
 *   int findCircleNum(int[][] isConnected)
 *
 * --- 시간복잡도 목표 ---
 *   DFS/BFS: O(N^2)
 *   Union-Find: O(N^2 * α(N)) ≈ O(N^2)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   Union-Find: 같은 컴포넌트에 속하는 모든 노드는 동일한 root를 갖는다.
 *
 * --- 함정 ---
 *   - 행렬은 대칭 (isConnected[i][j] == isConnected[j][i]).
 *   - 대각선은 항상 1 (자기 자신과 연결) — count 로직에 영향 없음.
 *   - DFS 깊이가 N까지 갈 수 있음 → 큰 N에서는 BFS나 Union-Find가 안전.
 */
class NumberOfProvinces {

    static class Solution {
        public int findCircleNum(int[][] isConnected) {
            // TODO: DFS/BFS 또는 Union-Find
            return 0;
        }

        // ---- Union-Find 헬퍼 (선택적으로 채울 것) ----
        // private int[] parent;
        // private int[] rank;
        // private int find(int x) { /* 경로 압축 */ return -1; }
        // private void union(int a, int b) { /* rank 기반 합치기 */ }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] g1 = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        assert sol.findCircleNum(g1) == 2 : "g1 → 2 provinces";

        int[][] g2 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        assert sol.findCircleNum(g2) == 3 : "g2 → 3 provinces";

        int[][] g3 = {{1}};
        assert sol.findCircleNum(g3) == 1 : "single city";

        int[][] g4 = {
            {1, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 1, 1}
        };
        assert sol.findCircleNum(g4) == 2 : "two pairs";

        System.out.println("✅ NumberOfProvinces: All tests passed");
    }
}
