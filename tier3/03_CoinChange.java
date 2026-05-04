/**
 * LeetCode 322 - Coin Change
 *
 * 문제: 동전 종류 coins와 목표 금액 amount가 주어질 때,
 *   amount를 만드는 데 필요한 최소 동전 개수. 만들 수 없으면 -1.
 *
 * --- 인터페이스 ---
 *   int coinChange(int[] coins, int amount)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(amount * len(coins)), 공간 O(amount)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   bottom-up DP.
 *   dp[a] = a 만들기 위한 최소 동전 수, 초기값 amount+1 (도달 불가 표식).
 *   dp[0] = 0.
 *   점화식: dp[a] = min over c in coins (a-c >= 0) { dp[a-c] + 1 }
 *
 *   for a in 1..amount:
 *     for c in coins:
 *       if c <= a:
 *         dp[a] = min(dp[a], dp[a-c] + 1)
 *   return dp[amount] > amount ? -1 : dp[amount]
 *
 *   대안: BFS로 amount → 0 으로 가는 최단 거리.
 *
 * --- 불변식 ---
 *   dp[a]가 채워진 시점에서, 그 값은 "지금까지 고려한 동전들로 a를 만드는 최소 개수".
 *
 * --- 함정 ---
 *   - 초기 dp 값을 Integer.MAX_VALUE로 두면 +1 시 오버플로우. amount+1로 두는 것이 안전.
 *   - amount == 0이면 0 반환.
 *   - 동전이 비어있어도 amount==0이면 0이어야 함.
 */
class CoinChange {

    static class Solution {
        public int coinChange(int[] coins, int amount) {
            // TODO: bottom-up DP, dp[a] = min 동전 수
            return -1;
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
