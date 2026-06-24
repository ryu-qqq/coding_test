/**
 * LeetCode 547 - Number of Provinces  [정답]
 *
 * 핵심: Union-Find로 직접 연결된 도시들을 union, 남은 컴포넌트 수가 곧 도(province) 수.
 * 불변식: 같은 컴포넌트의 노드들은 항상 동일한 root를 가진다.
 * 복잡도: 시간 O(N^2 * α(N)) ≈ O(N^2), 공간 O(N).
 * 자세한 해설 → SOLUTION.md
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
                parent[i] = find(parent[i]);  // 경로 압축
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
