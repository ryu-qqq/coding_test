/**
 * LeetCode 121 - Best Time to Buy and Sell Stock  [정답]
 *
 * 핵심: 한 패스 돌며 지금까지 최저가를 갱신하고, 오늘 팔았을 때 수익이 best보다 크면 갱신.
 * 불변식: 루프 i 직후, minPrice는 prices[0..i]의 최솟값, maxProfit은 그때까지의 최대 수익.
 * 함정: 단조 감소면 0 (음수 수익이면 안 사면 그만), 빈 배열도 0.
 * 복잡도: 시간 O(N), 공간 O(1).
 * 자세한 해설 → SOLUTION.md
 */
class BestTimeBuySell {

    static class Solution {
        public int maxProfit(int[] prices) {
            int minPrice = Integer.MAX_VALUE;
            int maxProfit = 0;

            for(int price : prices){
                minPrice = Math.min(minPrice, price);
                maxProfit = Math.max(maxProfit, price - minPrice);
            }

            return maxProfit;
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
