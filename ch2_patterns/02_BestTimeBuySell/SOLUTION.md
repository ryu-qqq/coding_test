# Best Time to Buy and Sell Stock (LC 121) — 해설

> 막히기 전에 골격(`BestTimeBuySell.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
`prices[i]`가 i일의 가격일 때, 한 번 사고 한 번 팔아 얻을 수 있는 최대 수익. 파는 날은 사는 날
이후여야 하며, 수익이 음수면 0을 반환한다.

## 핵심 직관 (1줄)
한 패스 돌며 지금까지의 최저가를 갱신하고, 오늘 팔았을 때 수익이 best보다 크면 갱신한다.

## 자료구조
- 변수 두 개: `minPrice`, `maxProfit`.

## 알고리즘
1. `minPrice = Integer.MAX_VALUE`, `maxProfit = 0`.
2. `for p in prices`: `minPrice = min(minPrice, p)`; `maxProfit = max(maxProfit, p - minPrice)`.

## 불변식
루프 i 직후, `minPrice`는 `prices[0..i]`의 최솟값, `maxProfit`은 그때까지의 최대 수익.

## 복잡도
- 시간: O(N)
- 공간: O(1)

## 함정 ⚠️
- 단조 감소면 `maxProfit = 0` (음수 수익이면 안 사면 그만).
- 빈 배열은 0 (루프가 한 번도 안 돌고 초기값 그대로).

## 대안 / 최적화
Kadane 알고리즘 관점으로 보면 "인접 차이값 배열의 최대 부분합" 문제와 등가다.

## 면접 답변 (한국어 1분)
> "한 번의 패스로 푸는 그리디 풀이입니다. minPrice에 지금까지 본 최저가를, maxProfit에 최대 수익을
> 추적합니다. 매 가격마다 최저가를 갱신하고, 현재 가격에서 minPrice를 뺀 값, 즉 오늘 팔았을 때 수익이
> 더 크면 갱신합니다. 단조 감소면 maxProfit이 0으로 남아 자연스럽게 처리됩니다. 시간 O(N), 공간
> O(1)입니다. Kadane 관점으로도 볼 수 있는데, 인접 차이값의 최대 부분합과 같은 문제입니다."

## Follow-up
- **여러 번 사고 팔 수 있다면? (LC 122)** 인접한 가격이 오를 때마다 차이를 더한다.
  `if (prices[i] > prices[i-1]) profit += prices[i] - prices[i-1]`.
- **K번 사고 팔 수 있다면? (LC 188)** 2D DP. `dp[k][i]` = i일까지 최대 k번 거래 시 최대 수익.
