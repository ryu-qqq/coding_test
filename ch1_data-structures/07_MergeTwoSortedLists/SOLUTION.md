# MergeTwoSortedLists (LC 21) — 해설

> 막히기 전에 골격(`MergeTwoSortedLists.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
오름차순 정렬된 두 연결 리스트를 하나의 정렬된 리스트로 병합한다.

## 핵심 직관 (1줄)
dummy head를 둬서 결과 head 분기 처리를 없애고, 두 리스트 머리만 비교해 작은 쪽을 tail에 붙인다.

## 자료구조
dummy head 노드 + tail 포인터.

## 알고리즘
1. dummy 노드 생성, `tail = dummy`.
2. while `l1 != null && l2 != null`: 작은 쪽을 `tail.next`에 붙이고 그 쪽 포인터 +1, `tail = tail.next`.
3. 남은 `l1` 또는 `l2`를 통째로 `tail.next`에 연결.
4. `dummy.next` 반환.

## 불변식
`dummy.next` ~ `tail`까지는 항상 정렬 상태.

## 복잡도
시간 O(N+M), 공간 O(1).

## 함정 ⚠️
- 마지막에 남은 리스트를 통째로 연결하는 걸 잊지 말 것(이미 정렬돼 있어 그대로 붙이면 됨).
- 안정성(같은 값일 때 l1 우선)을 원하면 비교를 `<=`로 한다.

## 대안 / 최적화
재귀로도 깔끔히 풀린다(`mergeTwoLists(l1.next, l2)` 식). K개 정렬 리스트 병합은 PriorityQueue 활용.

## 면접 답변 (한국어 1분)
> "두 정렬 리스트를 합치는 문제인데 dummy head와 tail 포인터를 사용했습니다. dummy를 두는 이유는 결과 리스트의 head가 어느 리스트에서 시작할지 모르니까 분기 처리를 없애기 위해서입니다. while 루프에서 두 리스트 head 값을 비교해서 작은 쪽을 tail.next에 붙이고 tail을 한 칸 전진시킵니다. 한쪽이 null이 되면 남은 다른 쪽을 통째로 tail.next에 연결하면 됩니다. 이미 정렬돼 있으니 그대로 붙여도 됩니다. 시간은 O(N+M), 공간은 O(1)입니다."

## Follow-up
- **K개 리스트를 병합하라면?** PriorityQueue에 각 리스트 head를 넣고 최소를 꺼내며 다음 노드를 다시 넣으면 O(N log K). 또는 두 개씩 divide-and-conquer로도 O(N log K).
- **재귀 버전과 비교?** 재귀는 코드가 짧지만 스택 깊이 N+M을 쓴다. 반복 버전이 메모리 측면에서 안전하다.
