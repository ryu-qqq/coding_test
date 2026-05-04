import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 15 - 3Sum
 *
 * 문제: 정수 배열 nums에서 a + b + c == 0 인 모든 유니크한 삼중쌍 [a,b,c]를 반환.
 *
 * --- 인터페이스 ---
 *   List<List<Integer>> threeSum(int[] nums)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N^2), 공간 O(1) (정렬 비용 별도)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   정렬 + 투 포인터.
 *
 *   sort(nums)
 *   for i in 0..n-3:
 *     if nums[i] > 0: break              // 더 이상 0 합 불가능
 *     if i > 0 and nums[i] == nums[i-1]: continue  // 중복 i 건너뜀
 *     l = i+1; r = n-1
 *     while l < r:
 *       sum = nums[i] + nums[l] + nums[r]
 *       if sum < 0: l++
 *       elif sum > 0: r--
 *       else:
 *         add [nums[i], nums[l], nums[r]]
 *         while l < r and nums[l] == nums[l+1]: l++   // 중복 l 건너뜀
 *         while l < r and nums[r] == nums[r-1]: r--   // 중복 r 건너뜀
 *         l++; r--
 *
 * --- 불변식 ---
 *   결과 리스트에는 동일한 삼중쌍이 한 번만 들어간다(중복 스킵 덕분).
 *
 * --- 함정 ---
 *   - 중복 제거를 i, l, r 모두에서 해야 한다.
 *   - 정렬 안 하면 두 포인터 전략 자체가 안 통한다.
 *   - 결과는 [-1, -1, 2] 같이 정렬된 형태로 들어가야 함 (정렬 후 인덱싱이 자연스러움).
 */
class ThreeSum {

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            // TODO: 정렬 + 투 포인터
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // [-1, 0, 1, 2, -1, -4] → [[-1,-1,2],[-1,0,1]]
        List<List<Integer>> r1 = sol.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assert r1.size() == 2 : "should be 2 triples";

        // [0,0,0] → [[0,0,0]]
        List<List<Integer>> r2 = sol.threeSum(new int[]{0, 0, 0});
        assert r2.size() == 1 && r2.get(0).equals(List.of(0, 0, 0)) : "all zeros";

        // [0,1,1] → []
        List<List<Integer>> r3 = sol.threeSum(new int[]{0, 1, 1});
        assert r3.isEmpty() : "no triple";

        // 빈 배열
        assert sol.threeSum(new int[]{}).isEmpty() : "empty";

        System.out.println("✅ ThreeSum: All tests passed");
    }
}
