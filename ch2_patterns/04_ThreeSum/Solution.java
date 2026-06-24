import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15 - 3Sum  [정답]
 *
 * 핵심: 정렬 후 한 원소를 고정(i)하고 나머지는 양 끝(l, r) 투포인터로 좁히기.
 * 불변식: 결과 리스트에 동일 삼중쌍은 한 번만 들어감.
 * 함정: 중복 제거를 i, l, r 모두에서 해야 함, nums[i] > 0이면 break.
 * 복잡도: 시간 O(N^2), 공간 O(1) (정렬 비용 별도, 결과 제외).
 * 자세한 해설 → SOLUTION.md
 */
class ThreeSum {

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);

            List<List<Integer>> results = new ArrayList<>();

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
