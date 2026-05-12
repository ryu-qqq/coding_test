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
 *
 * ====================================
 * === 채점 결과 (2026-05-11) ===
 * ====================================
 *
 * --- 실행 결과 ---
 *   ❌ ArrayIndexOutOfBoundsException: Index 6 out of bounds (line 43, sum 계산 중).
 *
 * --- 평가: 구조는 다 잡았는데 디테일 5 군데가 어긋남 ---
 *
 *   ✅ 정렬 ✓
 *   ✅ for + while 중첩 구조 ✓
 *   ✅ i 중복 스킵 ✓
 *   ✅ 정답 발견 후 중복 처리 시도 ✓
 *   ❌ 디테일 5 군데 (아래)
 *
 * 🔴 결정적 오류:
 *
 *   1. `return new ArrayList<>();` (line 65)
 *      → 위에서 results 에 정답 잘 넣고는, 마지막에 **빈 새 ArrayList 를 반환**.
 *      → results 가 통째로 무시됨. 무엇을 반환해야 할까?
 *
 *   2. `int left = 0;` (line 39)
 *      → left 가 0 부터 시작. 이러면 left 가 i 보다 앞에 있을 수도 있고, 같을 수도 있음.
 *      → 같은 원소를 두 번 쓰거나 (i == left), 이미 처리한 i 를 다시 만나는 문제.
 *      → 표준: left = i + 1 (i 다음 자리부터).
 *
 *   3. `right ++;` (line 53)
 *      → AIOOBE 의 직접 원인. right 가 증가하면 배열 밖으로 나감.
 *      → sum 이 너무 *작을* 때 → 합을 키워야 함 → 어느 쪽 포인터를 어떻게 옮겨야 하나?
 *      → (힌트: 정렬돼 있으니 작은 쪽 (left) 을 안쪽으로 옮기면 더 큰 값. right 는 그대로 두고 left++.)
 *
 *   4. sum 식의 부호 혼란. (line 38, 43)
 *        target = nums[i] * -1;                    // target = -nums[i]
 *        int sum = nums[left] + nums[right] + target;
 *        if (sum == 0) ...
 *
 *      → sum == 0 이려면 nums[left] + nums[right] + (-nums[i]) = 0
 *        즉 nums[left] + nums[right] = nums[i].
 *      → 그런데 우리가 원하는 식은 nums[i] + nums[left] + nums[right] = 0
 *        즉 nums[left] + nums[right] = **-nums[i]**.
 *      → 부호가 반대다. 정답이 있어도 sum == 0 안 되거나, 엉뚱한 트리플을 정답으로 인식.
 *
 *      두 가지 수정안 (둘 다 OK):
 *        (a) target 그대로 두고, 검사를 `nums[left] + nums[right] == target`
 *        (b) sum 식 그대로 두고, target = nums[i] (음수 안 곱함)
 *      또는 가장 간단한 (c): target 변수 안 쓰고 그냥
 *           int sum = nums[i] + nums[left] + nums[right];
 *           if (sum == 0) ...
 *
 *   5. 중복 스킵 안에서 left/right 이동을 안 하고, 가드도 없음. (line 47-48)
 *        while(nums[left] == nums[left+1]) left++;
 *        while(nums[right] == nums[right-1]) right--;
 *
 *      → 두 가지 문제:
 *        (a) **가드 없음**: left+1 또는 right-1 이 범위 밖이면 AIOOBE.
 *            예: left 가 마지막 자리 (n-1) 면 nums[left+1] = nums[n] → 폭발.
 *            가드: `while (left < right && nums[left] == nums[left+1])` ← left<right 먼저.
 *
 *        (b) **정답 추가 후 left/right 자체를 한 칸 더 옮겨야 한다**:
 *            중복 스킵 while 이 끝난 뒤에도 left, right 가 정답 자리 그대로면 같은 합 재발견 가능.
 *            → 마지막에 `left++; right--;` 추가 필요.
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   6. for 범위.
 *      → 현재 `i < nums.length`. 이건 OK 지만, `i < nums.length - 2` 로 잡으면 left=i+1, right=n-1
 *        이 항상 유효 (left < right 보장). 미세한 최적화.
 *
 *   7. 빈 배열 / 길이 < 3.
 *      → Arrays.sort 는 안전. for 안 들어가거나 한두 번만 들어감. while 도 안 들어가.
 *      → 단 #1 의 영향으로 어차피 빈 리스트 반환되니 *우연히* 통과.
 *
 * --- 핵심 분기 정리 (한 번에 외우기) ---
 *
 *   현재 트리플 합 sum 기준:
 *     sum < 0  → 합을 키워야 → **left++**  (작은 쪽을 더 큰 값으로)
 *     sum > 0  → 합을 줄여야 → **right--** (큰 쪽을 더 작은 값으로)
 *     sum == 0 → 정답 추가 → 중복 스킵 → left++; right--;
 *
 * --- 다음 단계 ---
 *   1) 🔴 #1 return results 로 수정.
 *   2) 🔴 #2 left = i+1 로 수정.
 *   3) 🔴 #3 right++ → right-- (또는 sum 분기 전체 재검토).
 *   4) 🔴 #4 sum 식 / target 부호 일관성. 가장 간단: target 빼고 `nums[i]+nums[left]+nums[right]`.
 *   5) 🔴 #5 중복 스킵 가드 추가 + 정답 후 left++/right-- 추가.
 *   6) java -ea ... 통과 확인.
 */
class ThreeSum {

    static class Solution {

        // a + b = -c
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> results = new ArrayList<>();
            Arrays.sort(nums);

            
            for(int i = 0; i < nums.length; i ++){
                if(i >0 && nums[i] == nums[i-1]) continue;

                int left = i + 1;
                int right = nums.length - 1;

                while(left < right){
                    int sum = nums[left] + nums[right] + nums[i];
                    if(sum ==0){
                        results.add(List.of(nums[i], nums[left], nums[right]));
                        
                        while(left < right && nums[left] == nums[left+1])  left++;
                        while(left < right && nums[right] == nums[right-1])  right--;
                        left ++;
                        right --;
                        
                    }else if(sum > 0){
                        right --;
                    }else{
                        left ++;
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
