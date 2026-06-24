import java.util.Arrays;

/**
 * LeetCode 322 - Coin Change  [정답]
 *
 * 핵심: dp[a] = a를 만드는 최소 동전 수. dp[a] = min over c { dp[a-c] + 1 } (a-c >= 0).
 * 불변식: dp[a]가 채워진 시점, 그 값은 "지금까지 고려한 동전들로 a를 만드는 최소 개수".
 * 복잡도: 시간 O(amount * coins.length), 공간 O(amount).
 * 자세한 해설 → SOLUTION.md
 */
class CoinChange {

    static class Solution {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;

            for(int i =1; i <=amount; i ++){
                for(int c : coins){
                    if(i-c>=0){
                        dp[i] = Math.min(dp[i], dp[i-c] + 1);
                    }
                }
            }

            return dp[amount] > amount ? - 1 : dp[amount];
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        assert sol.coinChange(new int[]{1, 2, 5}, 11) == 3 : "11 = 5+5+1";
        assert sol.coinChange(new int[]{2}, 3) == -1 : "impossible";
        assert sol.coinChange(new int[]{1}, 0) == 0 : "amount 0";
        assert sol.coinChange(new int[]{1}, 2) == 2 : "1+1";
        assert sol.coinChange(new int[]{186, 419, 83, 408}, 6249) == 20 : "tricky case";

        System.out.println("✅ CoinChange: All tests passed");
    }
}
