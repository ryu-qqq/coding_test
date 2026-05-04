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
 *   탐색 종료 시점까지 "이미 버린 쌍" 중 best를 만들 수 있는 쌍은 없다(증명 가능).
 *
 * --- 함정 ---
 *   - 같은 높이일 때 어느 쪽을 옮겨도 무방.
 *   - n < 2 인 경우 0 반환 (LC는 n >= 2 보장하기도 함).
 */
class ContainerWithMostWater {

    static class Solution {
        public int maxArea(int[] height) {
            // TODO: 투 포인터
            return 0;
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
