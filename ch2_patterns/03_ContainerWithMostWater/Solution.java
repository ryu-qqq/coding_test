/**
 * LeetCode 11 - Container With Most Water  [정답]
 *
 * 핵심: 너비는 어차피 줄어드니, 높이의 최솟값을 키우려면 더 낮은 쪽을 안쪽으로 이동.
 * 불변식: 이미 버린 쌍 중에 best를 만들 수 있는 쌍은 없다(짧은 쪽을 안 옮기면 면적은 절대 안 커짐).
 * 함정: 같은 높이일 땐 어느 쪽을 옮겨도 무방, n < 2면 0.
 * 복잡도: 시간 O(N), 공간 O(1).
 * 자세한 해설 → SOLUTION.md
 */
class ContainerWithMostWater {

    static class Solution {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length -1;
            int maxArea = 0;

            while(left < right){
                int leftHeight = height[left];
                int rightHeight = height[right];
                int h = Math.min(leftHeight, rightHeight);
                int d = right - left;

                maxArea = Math.max(maxArea, h * d);

                if(leftHeight < rightHeight){
                    left++;
                }else{
                    right--;
                }
            }

            return maxArea;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        assert sol.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}) == 49 : "case1 = 49";
        assert sol.maxArea(new int[]{1, 1}) == 1 : "two ones";
        assert sol.maxArea(new int[]{4, 3, 2, 1, 4}) == 16 : "ends both 4 = 16";
        assert sol.maxArea(new int[]{1, 2, 1}) == 2 : "small";

        System.out.println("✅ ContainerWithMostWater: All tests passed");
    }
}
