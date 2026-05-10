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
 */
class NumberOfProvinces {

    static class UnionFind{
        int[] parent;
        int count;

        public UnionFind(int n){
            parent = new int[n];
            count =n;
            for(int i =0; i < n; i ++){
                parent[i] = i;
            }
        }

        public int find(int i){
            if(parent[i] !=i){
                parent[i] = find(parent[i]);
            }

            return parent[i];
        }

        public void union(int x, int y){
            int rx = find(x);
            int ry = find(y);
            if(rx == ry) return;
            parent[rx] = ry;
            count--;
        }

        public int getCoutn(){
            return count;
        }
    }


    static class Solution {
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            UnionFind uf = new UnionFind(n);
            for(int i =0; i <n; i ++){
                for(int j = i + 1; j<n; j ++){
                    if (isConnected[i][j] == 1) {        
                        uf.union(i, j);                                                                                                                                                                                         
                    } 
                }
            }


            return uf.count;
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
