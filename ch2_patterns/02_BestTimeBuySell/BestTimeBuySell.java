/**
 * LeetCode 121 - Best Time to Buy and Sell Stock
 *
 * 문제: prices[i]가 i일의 가격일 때, 한 번 사고 한 번 팔아 얻을 수 있는 최대 수익.
 *   파는 날은 사는 날 이후여야 한다. 수익이 음수라면 0을 반환.
 *
 * --- 인터페이스 ---
 *   int maxProfit(int[] prices)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(1)
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
class BestTimeBuySell {

    static class Solution {
        public int maxProfit(int[] prices) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        assert sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4}) == 5 : "buy 1 sell 6";
        assert sol.maxProfit(new int[]{7, 6, 4, 3, 1}) == 0 : "monotonic decrease";
        assert sol.maxProfit(new int[]{1}) == 0 : "single day";
        assert sol.maxProfit(new int[]{}) == 0 : "empty";
        assert sol.maxProfit(new int[]{2, 4, 1}) == 2 : "early peak";

        System.out.println("✅ BestTimeBuySell: All tests passed");
    }
}
