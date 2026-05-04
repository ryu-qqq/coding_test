/**
 * LeetCode 300 - Longest Increasing Subsequence
 *
 * 문제: 배열에서 strictly 증가하는 부분 수열의 최대 길이.
 *
 * --- 인터페이스 ---
 *   int lengthOfLIS(int[] nums)
 *
 * --- 시간복잡도 목표 ---
 *   기본: O(N^2) DP
 *   고급: O(N log N) patience sort + binary search
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   DP: dp[i]가 채워진 직후, "i를 끝으로 하는 LIS"는 dp[i] 길이.
 *   patience: tails는 strictly 증가하며, 길이별 최소 마지막 값을 유지.
 *
 * --- 함정 ---
 *   - "strictly" 증가 — 같은 값은 LIS에 함께 못 들어감 (lower_bound 사용).
 *   - 빈 배열은 0.
 */
class LIS {

    static class Solution {
        public int lengthOfLIS(int[] nums) {
            // TODO: O(N^2) DP 또는 O(N log N) patience sort
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        assert sol.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) == 4 : "case1 → 4";
        assert sol.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) == 4 : "case2 → 4";
        assert sol.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}) == 1 : "all same → 1";
        assert sol.lengthOfLIS(new int[]{}) == 0 : "empty → 0";
        assert sol.lengthOfLIS(new int[]{1}) == 1 : "single → 1";

        System.out.println("✅ LIS: All tests passed");
    }
}
