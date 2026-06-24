# ReverseLinkedList (LC 206) — 해설

> 막히기 전에 골격(`ReverseLinkedList.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
단일 연결 리스트를 뒤집어 새 head를 반환한다.

## 핵심 직관 (1줄)
prev, curr, next 세 포인터로 한 노드씩 next 방향을 뒤집는다.

## 자료구조
단일 연결 리스트. 추가 자료구조 없음(포인터 3개).

## 알고리즘
1. `prev = null`, `curr = head`.
2. while `curr != null`: next 저장 → `curr.next = prev` → `prev = curr` → `curr = next`.
3. 종료 시 prev가 새 head.

## 불변식
매 반복 직후 prev는 지금까지 뒤집힌 부분의 head.

## 복잡도
- 반복: 시간 O(N), 공간 O(1)
- 재귀: 시간 O(N), 공간 O(N) (스택 깊이)

## 함정 ⚠️
- `curr.next`를 먼저 저장하지 않고 덮어쓰면 다음 노드로 갈 수 없다.
- while 조건은 `curr != null`. `curr.next != null`로 하면 마지막 노드의 뒤집기를 빠뜨린다.
- 재귀 버전은 마지막에 `head.next = null`을 안 하면 사이클이 생긴다.

## 대안 / 최적화
재귀 버전이 코드는 짧지만 깊이 N의 스택을 쓴다. 면접에선 반복 권장.

## 면접 답변 (한국어 1분)
> "연결 리스트를 뒤집는 가장 기본 문제인데, 반복 방식으로 풀었습니다. prev, curr, next 세 포인터를 사용하고, curr를 따라 가면서 매 노드의 next 포인터를 prev 쪽으로 뒤집어 줍니다. 핵심은 방향을 바꾸기 전에 반드시 next를 임시 저장해야 다음 노드로 갈 수 있다는 점입니다. 종료 시 prev가 새 head가 됩니다. 시간 O(N), 공간 O(1)이고 빈 리스트도 prev가 null로 시작하니 자연스럽게 처리됩니다. 재귀로도 풀 수 있지만 스택 깊이 N을 쓰니까 반복이 더 안전합니다."

## Follow-up
- **재귀로 풀면?** `reverse(head.next)`로 끝까지 들어간 뒤, 돌아오면서 `head.next.next = head; head.next = null`로 뒤집는다. 마지막 `head.next = null`을 빼먹으면 사이클이 생긴다.
- **while 조건을 `curr.next != null`로 하면?** 마지막 노드에서 멈춰 그 노드의 뒤집기를 빠뜨린다. `curr != null`이어야 모든 노드를 처리한다.
