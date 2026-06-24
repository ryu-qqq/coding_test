import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1 - Two Sum  [정답]
 *
 * 핵심: 각 인덱스에서 보수(target - nums[i])가 이미 map에 있는지 한 패스로 확인.
 * 불변식: map은 "현재 i 직전까지의 (값 → 인덱스)" 매핑을 정확히 반영.
 * 함정: lookup을 먼저 하고 put을 나중에 해야 자기 자신을 두 번 쓰지 않음.
 * 복잡도: 시간 O(N), 공간 O(N).
 * 자세한 해설 → SOLUTION.md
 */
class TwoSum {

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int comp = target - nums[i];
                if (map.containsKey(comp)) {
                    return new int[]{map.get(comp), i};
                }
                map.put(nums[i], i);
            }

            return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] r1 = sol.twoSum(new int[]{2, 7, 11, 15}, 9);
        // [0, 1] 순서이거나 [1, 0] 순서일 수 있음 → 둘 다 허용
        assert (r1[0] == 0 && r1[1] == 1) || (r1[0] == 1 && r1[1] == 0) : "case1";

        int[] r2 = sol.twoSum(new int[]{3, 2, 4}, 6);
        assert (r2[0] == 1 && r2[1] == 2) || (r2[0] == 2 && r2[1] == 1) : "case2";

        int[] r3 = sol.twoSum(new int[]{3, 3}, 6);
        assert (r3[0] == 0 && r3[1] == 1) || (r3[0] == 1 && r3[1] == 0) : "duplicate";

        int[] r4 = sol.twoSum(new int[]{-1, -2, -3, -4, -5}, -8);
        assert (r4[0] + r4[1] == 6) : "negatives, indices sum to 6 (2+4)";

        // 사용하지 않더라도 import 검증
        Map<Integer, Integer> _u = new HashMap<>();
        _u.put(0, 0);

        System.out.println("✅ TwoSum: All tests passed");
    }
}
