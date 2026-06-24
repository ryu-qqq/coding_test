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
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class NumberOfProvinces {

    // ---- Union-Find 헬퍼 (직접 채워볼 것) ----
    static class UnionFind{

        // TODO: 필요한 필드를 선언하세요 (parent 배열, count 등)

        public UnionFind(int n){
            // TODO: 초기화
        }

        public int find(int i){
            // TODO: 구현 (경로 압축)
            throw new UnsupportedOperationException("TODO");
        }

        public void union(int x, int y){
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
        }
    }


    static class Solution {
        public int findCircleNum(int[][] isConnected) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
        }
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
