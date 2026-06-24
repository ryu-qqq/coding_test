# FindMedianFromStream (LC 295) — 해설

> 막히기 전에 골격(`FindMedianFromStream.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
정수가 스트림으로 들어오는 자료구조. 매 호출 시 현재까지의 중앙값을 반환한다.

## 핵심 직관 (1줄)
두 힙을 균형 있게 유지한다 — `lo`(최대 힙, 작은 절반) + `hi`(최소 힙, 큰 절반).

## 자료구조
- `lo`: 최대 힙 (`Comparator.reverseOrder()` 또는 `(a,b)->b-a`)
- `hi`: 최소 힙 (기본 PriorityQueue)

## 알고리즘 (이 코드의 방식: compare-then-place)
- `addNum(v)`: `lo`가 비었거나 `v <= lo.peek()`이면 `lo`에, 아니면 `hi`에 넣는다. 이후 크기 재균형: `lo.size() > hi.size()+1`이면 `hi.offer(lo.poll())`, `hi.size() > lo.size()`이면 `lo.offer(hi.poll())`.
- `findMedian()`: 두 힙 크기가 같으면 두 top의 평균, 아니면(`lo`가 1 더 큼) `lo.peek()`.

대안 트릭(더 짧음): `lo.offer(v); hi.offer(lo.poll());` 후 `hi.size() > lo.size()`이면 `lo.offer(hi.poll())`. 두 단계만으로 정렬이 자동 보장된다.

## 불변식
`lo`의 모든 원소 ≤ `hi`의 모든 원소. 그리고 `lo.size == hi.size` 또는 `lo.size == hi.size + 1`.

## 복잡도
- `addNum`: O(log N)
- `findMedian`: O(1)
- 공간: O(N)

## 함정 ⚠️
- `PriorityQueue`는 기본 min-heap → 최대 힙은 `Comparator.reverseOrder()`로 만들어야 한다.
- 두 top의 합을 2로 나눌 때 정수 나눗셈이 아니라 `/ 2.0`로 double 캐스팅(평균 정확도).
- compare-then-place 방식은 재균형 분기 두 개를 빠짐없이 둬야 한다. lo-first-then-poll 트릭은 코너 케이스를 자동으로 흡수해 더 안전하다.

## 대안 / 최적화
`TreeMap`(정렬 멀티셋)으로도 가능하지만 중앙값 접근에 별도 인덱스/size 추적이 필요해 코드가 더 복잡하다. 두 힙이 add O(log N), median O(1)로 가장 깔끔.

## 면접 답변 (한국어 1분)
> "두 힙을 사용합니다. lo는 작은 절반을 담는 최대 힙, hi는 큰 절반을 담는 최소 힙입니다. 불변식은 lo의 모든
> 원소가 hi의 모든 원소보다 작거나 같고, 두 힙 크기 차이가 최대 1이며 lo가 더 클 수 있다는 겁니다. addNum은
> 들어온 값을 lo의 top과 비교해 적절한 힙에 넣은 뒤 크기를 재균형합니다. findMedian은 두 힙 크기가 같으면 두
> top의 평균을, lo가 더 크면 lo.peek을 반환합니다. addNum은 O(log N), findMedian은 O(1), 공간은 O(N)입니다.
> 더 짧게 짜려면 lo에 먼저 넣고 lo의 top을 빼서 hi로 보내는 트릭으로 정렬을 자동 보장할 수 있습니다."

## Follow-up
- **lo-first-then-poll 트릭을 쓰는 이유는?** 단순 비교 배치는 hi가 비어있는 코너 케이스를 따로 처리해야 한다. lo에 먼저 넣고 lo.poll로 가장 큰 걸 hi로 보내면 정렬이 자동 보장된다.
- **왜 두 힙인가? TreeMap은?** TreeMap은 중앙값 접근에 인덱스/size 추적이 필요해 코드가 복잡하다. 두 힙은 add도 median도 깔끔하다.
