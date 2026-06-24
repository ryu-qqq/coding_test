# LIS (LC 300) — 해설

> 막히기 전에 골격(`LIS.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
배열에서 strictly 증가하는 부분 수열의 최대 길이.

## 핵심 직관 (1줄)
- **DP(이 풀이)**: `dp[i]` = `nums[i]`로 끝나는 LIS 길이.
- **Patience(고급)**: `tails[k]` = 길이 k+1 LIS의 가능한 가장 작은 마지막 값.

## 자료구조
- O(N²) DP: `dp[]` 배열
- O(N log N): `tails[]` 배열 + 이진 탐색(lower_bound)

## 알고리즘 (이 풀이: O(N²) DP)
1. `dp[i] = 1`로 초기화.
2. `for i: for j < i`: `nums[j] < nums[i]`이면 `dp[i] = max(dp[i], dp[j] + 1)`.
3. 모든 `dp[i]`의 최댓값이 정답.

## 알고리즘 (고급: patience sort O(N log N))
1. `tails = []`.
2. `for v`: `idx = lowerBound(tails, v)`.
3. `idx == tails.size()`면 append(LIS 길이 +1), 아니면 `tails[idx] = v`(덮어쓰기).
4. `tails.size()`가 정답.

## 불변식
- DP: `dp[i]` 확정 시 그 값은 `nums[i]`로 끝나는 가장 긴 증가 부분수열 길이.
- Patience: `tails`는 strictly 증가하며, 길이별로 최소 가능한 마지막 값을 유지한다.

## 복잡도
- DP: O(N²), 공간 O(N)
- Patience: O(N log N), 공간 O(N)

## 함정 ⚠️
- "strictly" 증가 — 같은 값은 함께 못 들어간다. DP에선 `<`(이 코드처럼), patience에선 lower_bound 사용(upper_bound는 같은 값 허용 = non-decreasing).
- 빈 배열은 0.
- patience의 `tails`는 실제 LIS 시퀀스가 아니다 — 길이만 정답.

## 대안 / 최적화
patience sort + 이진 탐색으로 O(N log N)까지 줄일 수 있다(위 알고리즘 참고).

## 면접 답변 (한국어 1분)
> "두 가지 풀이가 있습니다. 기본 O(N²) DP는 dp[i]를 nums[i]로 끝나는 LIS 길이로 정의하고, j<i 중
> nums[j]<nums[i]인 것들의 dp[j]+1의 최댓값으로 채웁니다. 더 빠른 O(N log N) 풀이는 patience sort인데,
> tails 배열을 유지하면서 각 num을 lower_bound로 적절한 위치에 덮어쓰거나 끝에 append합니다. tails[k]는
> 길이 k+1 LIS의 가능한 가장 작은 마지막 값이고, tails 길이가 곧 LIS 길이입니다. 단, tails는 실제 LIS
> 시퀀스가 아니라 길이만 알려준다는 점이 함정입니다. strictly 증가니까 lower_bound를 써야 같은 값이 안 낍니다."

## Follow-up
- **tails가 실제 LIS인가?** 아니다. 길이별 최소 마지막 값을 모은 보조 배열일 뿐. 실제 시퀀스 복원은 각 num의 직전 인덱스를 따로 기록해야 한다.
- **lower_bound vs upper_bound?** strictly 증가(LC 300)면 lower_bound(같은 값 배제), non-decreasing이면 upper_bound.
