# CoinChange (LC 322) — 해설

> 막히기 전에 골격(`CoinChange.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
동전 종류 `coins`와 목표 금액 `amount`가 주어질 때 `amount`를 만드는 최소 동전 개수. 만들 수 없으면 -1.

## 핵심 직관 (1줄)
`dp[a]` = a를 만드는 최소 동전 수. `dp[a] = min over c in coins (a-c >= 0) { dp[a-c] + 1 }`.

## 자료구조
1차원 배열 `dp[amount+1]`.

## 알고리즘
1. `dp`를 `amount+1`로 채워 도달 불가 표식으로 둔다. `dp[0] = 0`.
2. `for a in 1..amount`: `for c in coins`: `c <= a`이면 `dp[a] = min(dp[a], dp[a-c] + 1)`.
3. `dp[amount] > amount`이면 -1, 아니면 `dp[amount]`.

## 불변식
`dp[a]`가 채워진 시점, 그 값은 "지금까지 고려한 동전들로 a를 만드는 최소 개수".

## 복잡도
- 시간: O(amount × coins.length)
- 공간: O(amount)

## 함정 ⚠️
- 초기값을 `Integer.MAX_VALUE`로 두면 `+1`에서 오버플로우. `amount+1`로 두면 안전(어떤 정답도 amount 이하).
- `amount == 0`이면 0 반환(`dp[0]=0`으로 자연 처리).
- 동전이 빈 배열이라도 `amount == 0`이면 0.

## 대안 / 최적화
BFS — `amount → 0`으로 가는 최단 거리(각 노드에서 coin만큼 빼는 방식). 동일 복잡도, 큐 기반.

## 면접 답변 (한국어 1분)
> "bottom-up DP로 풀었습니다. dp[a]를 a를 만드는 최소 동전 수로 정의하고, 점화식은 모든 동전 c에 대해
> dp[a-c] + 1의 최소값입니다. 초기값을 amount+1로 둬서 도달 불가 표식으로 쓰는데, Integer.MAX_VALUE를 쓰면
> +1에서 오버플로우가 나서 amount+1이 안전합니다. dp[0]은 base로 0이고, 마지막에 dp[amount]가 amount+1을
> 그대로 갖고 있으면 도달 불가라 -1을 반환합니다. 시간은 amount 곱하기 coins 길이, 공간은 amount입니다.
> BFS로도 같은 복잡도로 풀 수 있습니다."

## Follow-up
- **왜 dp 초기값이 amount+1인가?** 도달 불가 표식인데, 어떤 정상 답도 amount 이하다(1원 동전이면 amount개). amount+1이면 비교에서 무조건 져 "도달 불가"를 뜻한다. MAX_VALUE는 +1 오버플로우 위험.
- **Greedy로 풀면 안 되나?** [1, 3, 4], amount=6 같은 반례가 있다(그리디 4+1+1=3개 > 최적 3+3=2개). 일반 동전 집합에선 DP가 맞다.
