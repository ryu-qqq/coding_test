# KthLargest (LC 703) — 해설

> 막히기 전에 골격(`KthLargest.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
정수 스트림에서 매 `add` 호출마다 현재까지 들어온 값 중 K번째로 큰 값을 반환한다. (LC 215의 스트림 변형)

## 핵심 직관 (1줄)
크기 K인 **최소 힙**을 유지하면 그 top이 곧 K번째로 큰 값이다.

## 자료구조
크기 K짜리 `PriorityQueue<Integer>` (자바 기본은 min-heap).

## 알고리즘
- 생성자: `nums`의 모든 값을 `add`로 흘려보낸다.
- `add(v)`: `heap.offer(v)`. 힙 크기가 K를 넘으면 `poll`(가장 작은 값 버림). `heap.peek()` 반환.

## 불변식
힙에는 지금까지 본 값 중 **가장 큰 K개**가 들어 있고, top(최솟값)이 그 중 K번째로 큰 값이다.

## 복잡도
- 생성: O(N log K)
- add: O(log K)
- 공간: O(K)

## 함정 ⚠️
- max-heap을 쓰고 매번 K-1개를 빼는 방식은 O(K log N)으로 비효율.
- `nums`가 K보다 적을 수 있으니 `add`에서 크기 체크는 항상 필요(이 풀이는 자연 처리됨).
- 자바 `PriorityQueue`는 기본이 min-heap임을 기억.

## 대안 / 최적화
정적 배열에서 K번째 큰 값만 찾는 LC 215라면 Quickselect로 평균 O(N)도 가능.

## 면접 답변 (한국어 1분)
> "크기 K짜리 최소 힙으로 풀었습니다. 핵심은 가장 큰 K개의 값만 힙에 유지하는 거고, 그러면 힙의 top, 즉 그
> K개 중 가장 작은 값이 바로 K번째로 큰 값이 됩니다. add마다 새 값을 offer한 후 힙 크기가 K를 넘으면 poll로
> 가장 작은 걸 버립니다. add는 O(log K), 공간은 O(K)이고 생성자는 N개를 add하니 O(N log K)입니다. max-heap을
> 써서 매번 K-1개를 빼는 방식은 O(K log N)이라 비효율입니다."

## Follow-up
- **왜 min-heap이고 max-heap이 아닌가?** K개 중 가장 작은 게 K번째로 큰 값이라는 관찰 때문. min-heap이면 그 값이 top에 있어 O(1) 접근. max-heap이면 K번째에 닿으려고 K-1개를 빼야 한다.
- **정적 배열에서 K번째 큰 값(LC 215)이라면?** Quickselect로 평균 O(N), 최악 O(N²). 또는 동일한 min-heap O(N log K) 풀이.
