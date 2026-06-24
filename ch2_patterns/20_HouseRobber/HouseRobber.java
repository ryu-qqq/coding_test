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
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class HouseRobber {

    static class Solution {
        public int rob(int[] nums) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
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
