import java.util.Arrays;

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
class CoinChange {

    static class Solution {
        public int coinChange(int[] coins, int amount) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
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
