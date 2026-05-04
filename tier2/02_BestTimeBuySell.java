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
 *   루프 i 직후, minPrice는 prices[0..i]의 최솟값,
 *   best는 0..i 까지의 최대 수익.
 *
 * --- 함정 ---
 *   - 파는 날이 사는 날 이후여야 하므로, 같은 인덱스에서 팔면 0이 나온다 (자연스레 처리됨).
 *   - 가격이 단조 감소면 best = 0 이 정답.
 *   - 빈 배열은 0.
 */
class BestTimeBuySell {

    static class Solution {
        public int maxProfit(int[] prices) {
            // TODO: 단일 패스, minPrice 추적
            return 0;
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
