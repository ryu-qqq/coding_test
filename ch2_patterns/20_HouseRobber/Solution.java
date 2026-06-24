/**
 * LeetCode 198 - House Robber  [정답]
 *
 * 핵심: 각 집에서 "털기(prev2 + nums[i]) vs 안 털기(prev1)" 중 큰 값을 취하는 rolling DP.
 * 불변식: 매 반복 직후 prev1 = 0..i까지 최대 수익, prev2 = 0..i-1까지 최대 수익.
 * 복잡도: 시간 O(N), 공간 O(1).
 * 자세한 해설 → SOLUTION.md
 */
class HouseRobber {

    static class Solution {
        public int rob(int[] nums) {
            if(nums.length ==0) return 0;
            if(nums.length ==1) return nums[0];

            int prev2 = nums[0];
            int prev1 = Math.max(nums[0], nums[1]);

            for(int i = 2; i <nums.length; i ++){
                int cur = Math.max(prev1, prev2 + nums[i]);
                prev2 = prev1;
                prev1 = cur;
            }
            return prev1;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        assert sol.rob(new int[]{1, 2, 3, 1}) == 4 : "[1,2,3,1] → 4 (1+3)";
        assert sol.rob(new int[]{2, 7, 9, 3, 1}) == 12 : "[2,7,9,3,1] → 12 (2+9+1)";
        assert sol.rob(new int[]{}) == 0 : "empty → 0";
        assert sol.rob(new int[]{5}) == 5 : "single → 5";
        assert sol.rob(new int[]{2, 1, 1, 2}) == 4 : "[2,1,1,2] → 4";

        System.out.println("✅ HouseRobber: All tests passed");
    }
}
