import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1 - Two Sum
 *
 * 문제: nums 배열에서 합이 target이 되는 두 인덱스 쌍을 찾아 반환한다.
 *   정확히 한 쌍의 답이 존재한다고 가정.
 *
 * --- 인터페이스 ---
 *   int[] twoSum(int[] nums, int target)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(N)  (HashMap one-pass)
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
class TwoSum {

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> map = new HashMap<>();


            //  target 4
            // 1, 3

            // 4 - 1 -> 3 

            for(int i = 0; i < nums.length; i++){
                int abs = target - nums[i];
                Integer value = map.get(abs);
                if(map.containsKey(value)){
                    return new int[]{i, value};
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
