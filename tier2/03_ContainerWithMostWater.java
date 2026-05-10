/**
 * LeetCode 11 - Container With Most Water
 *
 * 문제: 정수 배열 height[i] 가 i번째 막대 높이라 할 때,
 *   두 막대를 골라 만들 수 있는 가장 큰 면적(=물의 양)을 구한다.
 *   면적 = (j - i) * min(height[i], height[j])
 *
 * --- 인터페이스 ---
 *   int maxArea(int[] height)
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
