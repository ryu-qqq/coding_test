/**
 * LeetCode 198 - House Robber
 *
 * 문제: nums[i] = i번째 집의 금액. 인접한 두 집은 동시에 털 수 없을 때 최대 금액.
 *
 * --- 인터페이스 ---
 *   int rob(int[] nums)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(1)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   매 반복 직후 prev1 = "0..i까지 봤을 때의 최대 수익", prev2 = "0..i-1까지의 최대 수익".
 *
 * --- 함정 ---
 *   - 빈 배열은 0.
 *   - 길이 1 → nums[0] 그대로 반환.
 *   - 음수 금액은 문제 가정상 없음 (LC는 0 이상).
 */
class HouseRobber {

    static class Solution {
        public int rob(int[] nums) {
            // TODO: rolling DP, max(prev1, prev2 + v)
            return 0;
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
