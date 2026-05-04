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
 * --- 핵심 아이디어 (수도코드) ---
 *   1) O(N^2) DP:
 *        dp[i] = nums[i]를 마지막으로 끝나는 LIS의 길이
 *        dp[i] = 1 + max{ dp[j] | j < i, nums[j] < nums[i] }   (없으면 1)
 *        return max(dp)
 *
 *      for i in 0..n-1:
 *        dp[i] = 1
 *        for j in 0..i-1:
 *          if nums[j] < nums[i]:
 *            dp[i] = max(dp[i], dp[j] + 1)
 *
 *   2) O(N log N) "patience sort":
 *        tails 배열을 유지 (tails[k] = 길이 k+1 LIS의 가능한 가장 작은 마지막 값).
 *        각 num을 lower_bound로 tails에 위치시킴.
 *        - 발견된 위치가 tails 끝이면 append (LIS 길이 +1).
 *        - 그렇지 않으면 그 위치를 num으로 덮어씀.
 *        결과: tails.size() = LIS 길이.
 *        주의: tails는 실제 LIS의 시퀀스가 아님(길이만 정답).
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
