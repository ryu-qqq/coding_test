import java.util.Arrays;

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
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
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
