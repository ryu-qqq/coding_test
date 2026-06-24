/**
 * LeetCode 70 - Climbing Stairs  [정답]
 *
 * 핵심: 본질이 피보나치 — f(n) = f(n-1) + f(n-2). 직전 두 값만 굴리는 rolling DP.
 * 불변식: 매 반복 직전 prev2 = f(i-2), prev1 = f(i-1).
 * 복잡도: 시간 O(N), 공간 O(1).
 * 자세한 해설 → SOLUTION.md
 */
class ClimbingStairs {

    static class Solution {
        public int climbStairs(int n) {
            if (n <= 2) return n;
            int prev2 = 1;
            int prev1 = 2;
            for(int i =3; i<=n; i ++){
                int cur = prev1 + prev2;
                prev2 = prev1;
                prev1 = cur;
            }

            return prev1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        assert sol.climbStairs(1) == 1 : "n=1 → 1";
        assert sol.climbStairs(2) == 2 : "n=2 → 2";
        assert sol.climbStairs(3) == 3 : "n=3 → 3";
        assert sol.climbStairs(4) == 5 : "n=4 → 5";
        assert sol.climbStairs(5) == 8 : "n=5 → 8";
        assert sol.climbStairs(10) == 89 : "n=10 → 89";

        System.out.println("✅ ClimbingStairs: All tests passed");
    }
}
