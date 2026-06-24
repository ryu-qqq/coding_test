/**
 * LeetCode 70 - Climbing Stairs
 *
 * 문제: n번째 계단까지 오르는 방법의 수. 한 번에 1 또는 2 계단을 오를 수 있다.
 *
 * --- 인터페이스 ---
 *   int climbStairs(int n)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N), 공간 O(1)  (rolling 두 변수)
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
class ClimbingStairs {

    static class Solution {
        public int climbStairs(int n) {
            // TODO: 구현
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        assert sol.climbStairs(1) == 1 : "n=1 → 1";
        assert sol.climbStairs(2) == 2 : "n=2 → 2";
        assert sol.climbStairs(3) == 3 : "n=3 → 3";
        assert sol.climbStairs(4) == 5 : "n=4 → 5";
        assert sol.climbStairs(5) == 8 : "n=5 → 8";
        assert sol.climbStairs(10) == 89 : "n=10 → 89";

        System.out.println("✅ ClimbingStairs: All tests passed");
    }
}
