import java.util.Arrays;

/**
 * LeetCode 300 - Longest Increasing Subsequence  [정답]
 *
 * 핵심: dp[i] = nums[i]를 끝으로 하는 LIS 길이. j<i 중 nums[j]<nums[i]인 dp[j]+1의 최댓값.
 * 불변식: dp[i] 확정 시 그 값은 nums[i]로 끝나는 가장 긴 증가 부분수열의 길이.
 * 복잡도: 이 풀이 O(N^2) DP. (고급: patience sort + 이진 탐색 O(N log N))
 * 자세한 해설 → SOLUTION.md
 */
class LIS {

    static class Solution {
        public int lengthOfLIS(int[] nums) {
            int length = nums.length;
            if(length ==0) return 0;

            int[] dp = new int[length];
            Arrays.fill(dp, 1);

            int maxLen = 1;

            for(int i =1; i<length; i ++){
                for(int j =0; j<i; j ++){
                    if(nums[j] < nums[i]){
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxLen = Math.max(maxLen, dp[i]);
            }

            return maxLen;
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
