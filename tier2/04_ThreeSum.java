import java.util.ArrayList;
import java.util.Arrays;
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
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 */
class ThreeSum {

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);

            List<List<Integer>> results = new ArrayList<>();


            // b + c =  - a
            // -2  -1  -1  0  2  1  3

            for(int i =0; i <nums.length; i ++){
                if(nums[i] >0) break;
                if(i > 0 && nums[i] == nums[i-1]) continue;


                int target =  nums[i] * -1;

                int left = i + 1;
                int right = nums.length - 1;
                
                while(left < right){

                    int a = nums[left];
                    int b = nums[right];
                    int sum = a+b;

                    if(sum == target){
                        results.add(List.of(a, b, nums[i]));

                        while(left < right && nums[left] == nums[left+1]) left ++;
                        while(left < right && nums[right] == nums[right-1]) right --;

                        left ++;
                        right --;

                    }else if(sum < target ){
                        left ++;
                    }else{
                        right --;
                    }
                }

            }

        
            return results;
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
