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
 *
 * ====================================
 * === 채점 결과 (2026-05-11) ===
 * ====================================
 *
 * --- 실행 결과 ---
 *   ❌ AssertionError "case1 = 49". 두 포인터 골격은 맞는데 계산식이 어긋남.
 *
 * --- 두 가지 큰 문제 ---
 *
 *   [문제 1] 너비를 "높이의 차이" 로 계산하고 있다. (line 43)
 *       int width = rh - lh;
 *
 *     너비는 두 막대 사이의 **거리** 즉 **인덱스 차이** 다.
 *     공식: `area = (right - left) * min(height[left], height[right])`
 *     → 너비 = `right - left`, 높이 = `min(...)`.
 *     너 코드의 `rh - lh` 는 두 막대 *높이의* 차이라 의미 자체가 다름. 게다가 음수도 될 수 있음.
 *
 *   [문제 2] 포인터 이동을 면적 계산 *전에* 한다. (line 36-46)
 *     순서가 이렇게 되어있다:
 *       1) lh, rh 읽기 (이동 *전* 높이)
 *       2) 짧은 쪽 이동 (left++ 또는 right--)
 *       3) width 계산 (이동 *전* 높이 차이로)
 *       4) h 계산 (이동 *후* 인덱스의 높이로)
 *       5) square 갱신
 *
 *     이동 전/후 값이 뒤섞여서 의미가 깨진다.
 *     또한 처음 위치 (left=0, right=N-1) 의 면적이 *계산되지 않는다*.
 *
 *   표준 순서:
 *       1) 현재 left, right 로 면적 계산
 *       2) 짧은 쪽 이동
 *     이걸 while 안에서 반복.
 *
 * --- 핵심 통찰: 왜 짧은 쪽을 움직이나? ---
 *
 *   면적 = (너비) × (짧은 막대 높이).
 *   짧은 쪽이 면적의 **병목**. 면적을 키우려면 둘 중 하나가 필요:
 *     (a) 너비 늘리기 — 불가능 (양 끝에서 시작했으니 줄어들기만 함)
 *     (b) 짧은 쪽 높이 늘리기 — 짧은 쪽을 안쪽으로 옮겨 더 높은 막대를 만날 가능성
 *
 *   긴 쪽을 옮기면? 너비는 줄고, 높이는 어차피 짧은 쪽에 막혀서 더 늘어날 수 없음 → 면적 무조건 감소.
 *   짧은 쪽을 옮기면? 너비는 줄지만, 높이가 늘어날 *기회* 가 생김.
 *
 *   ※ 면접 답변 핵심: **"짧은 쪽을 움직이면 더 큰 면적의 가능성이 남는다. 긴 쪽을 움직이면 더 큰 면적이 절대 불가능."**
 *
 * --- 표준 패턴 ---
 *
 *   int left = 0, right = height.length - 1;
 *   int max = 0;
 *   while (left < right) {
 *       int w = right - left;
 *       int h = Math.min(height[left], height[right]);
 *       max = Math.max(max, w * h);
 *
 *       if (height[left] < height[right]) left++;
 *       else right--;
 *   }
 *   return max;
 *
 *   주의 — 비교 조건 `height[left] < height[right]`:
 *     - 같을 때 (`==`) 어느 쪽을 옮겨도 동일 (양쪽 다 짧은 쪽). else 분기로 right-- 해도 OK.
 *     - `<=` 으로 left++ 해도 OK. 결과 동일.
 *
 * --- 손 시뮬레이션 (height=[1,8,6,2,5,4,8,3,7]) ---
 *   left=0, right=8 → w=8, h=min(1,7)=1, area=8. 짧은 쪽 left. left++.
 *   left=1, right=8 → w=7, h=min(8,7)=7, area=49. 짧은 쪽 right. right--.
 *   left=1, right=7 → w=6, h=min(8,3)=3, area=18. 짧은 쪽 right. right--.
 *   left=1, right=6 → w=5, h=min(8,8)=8, area=40. 같음. else 분기로 right--.
 *   left=1, right=5 → w=4, h=min(8,4)=4, area=16. right--.
 *   left=1, right=4 → w=3, h=min(8,5)=5, area=15. right--.
 *   left=1, right=3 → w=2, h=min(8,2)=2, area=4. right--.
 *   left=1, right=2 → w=1, h=min(8,6)=6, area=6. right--.
 *   left=1, right=1 → 종료.
 *   max = 49 ✓
 *
 * 🔴 결정적 오류:
 *
 *   1. width 계산이 잘못. (line 43)
 *      → `rh - lh` (높이 차이) 가 아니라 `right - left` (인덱스 차이).
 *
 *   2. 이동을 면적 계산 *전에* 한다. (line 36-46)
 *      → 표준은 "계산 → 이동" 순서. 처음 위치의 면적이 누락됨.
 *
 *   3. 이동 전/후 변수가 섞임.
 *      → lh, rh 는 이동 *전*, h 의 min 은 이동 *후* 인덱스. 의미 일관성 무너짐.
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   4. 두 막대 높이가 같을 때 (`lh == rh`).
 *      → 어느 쪽을 옮겨도 결과 동일. `>=` 로 묶거나 `<=` 로 묶거나 OK.
 *      → 너 코드의 `lh >= rh` 분기 자체는 일반적이지만, 위 #1, #2 와 결합되어 깨짐.
 *
 *   5. 빈 배열 / 단일 막대.
 *      → height.length-1 = -1 또는 0. while 안 들어감. return 0. ✓
 *      → 단 height.length == 0 일 때 `right = -1`. while(0 < -1) false → 안전.
 *
 * 🟢 개선 가능:
 *
 *   6. 변수명 `square` 보다 `maxArea` 가 더 직관적 (square 는 정사각형 느낌).
 *
 * --- 다음 단계 ---
 *   1) 너비 = "인덱스 차이" 라는 사실 종이에 적기.
 *   2) "계산 → 이동" 순서로 while 본문 재구성.
 *   3) 짧은 쪽을 움직이는 이유 본인 표현으로 한 줄 정리 (면접 답변용).
 *   4) java -ea ... 통과 확인.
 */
class ContainerWithMostWater {

    static class Solution {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int square = 0;

            while(left < right){

                int w = right - left;
                int lh = height[left];
                int rh = height[right];
                int h = Math.min(lh, rh);
                square = Math.max(square, w * h);

                if(lh >= rh ){
                    right--;
                }else{
                    left ++;
                }
            }


            return square;
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
