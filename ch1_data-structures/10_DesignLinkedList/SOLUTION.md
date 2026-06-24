# DesignLinkedList (LC 707) — 해설

> 막히기 전에 골격(`DesignLinkedList.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
연결 리스트의 `get / addAtHead / addAtTail / addAtIndex / deleteAtIndex`를 직접 구현한다.

## 핵심 직관 (1줄)
head/tail 두 sentinel(더미)을 둔 **이중 연결 리스트**로, 양끝 더미 덕에 모든 삽입/삭제를 "prev/next 재배선" 한 가지 패턴으로 통일한다(경계 분기 제거).

## 자료구조
- `ListNode{ val, prev, next }`
- `head`, `tail` 두 sentinel 노드 + `int size`. 초기에 `head.next = tail`, `tail.prev = head`.

## 알고리즘
- `get(i)`: `0 ≤ i < size` 검사 후 `head.next`부터 i번 전진해 값 반환, 범위 밖이면 -1.
- `addAtHead(v)`: `head`와 `head.next` 사이에 새 노드 삽입, size++.
- `addAtTail(v)`: `tail.prev`와 `tail` 사이에 새 노드 삽입, size++.
- `addAtIndex(i, v)`: 범위 검사 후 i번째 노드 앞에 삽입(cur를 head부터 i번 전진해 직전 위치 확보), 양방향 4개 링크 재배선, size++.
- `deleteAtIndex(i)`: 범위 검사 후 i번째 실제 노드를 찾아 `prevNode.next = nextNode; nextNode.prev = prevNode`, size--.

## 불변식
`head.next` ~ `tail.prev` 사이에 정확히 size개의 실제 노드. 모든 실제 노드는 prev/next가 양방향으로 일관.

## 복잡도
- get / addAtIndex / deleteAtIndex: O(N) (인덱스까지 순회)
- addAtHead / addAtTail: O(1) (양끝 sentinel 덕에 끝 노드 즉시 접근)

## 함정 ⚠️
- 이중 연결이므로 삽입 시 **네 개의 링크**(new.prev, new.next, prev.next, next.prev)를 모두 갱신해야 한다. 하나라도 빠지면 역방향 순회나 이후 삭제가 깨진다.
- size 갱신을 빼먹으면 이후 모든 범위 검사가 어긋난다.
- `get`은 인덱스가 음수거나 size 이상이면 -1을 반환.

## 대안 / 최적화
단일 연결 리스트 + dummy head로도 풀 수 있다(addAtTail은 tail 포인터가 없으면 O(N)). 이중 연결 + 양끝 sentinel은 양끝 연산을 O(1)로 만들고 경계 분기를 없애 코드가 가장 깔끔하다.

## 면접 답변 (한국어 1분)
> "연결 리스트를 직접 구현하는 문제인데, head와 tail 두 개의 sentinel 더미를 둔 이중 연결 리스트로 풀었습니다. 양끝에 더미를 두면 인덱스 0에 삽입하거나 head/tail을 다룰 때도 경계 분기 없이 'prev와 next를 재배선하는' 한 가지 패턴으로 통일됩니다. size 변수를 따로 둬서 범위 검사를 빠르게 합니다. addAtHead와 addAtTail은 양끝 더미 옆에 바로 끼우면 되니 O(1)이고, get이나 임의 인덱스 삽입/삭제는 그 위치까지 순회해야 해서 O(N)입니다. 이중 연결이라 삽입할 때 네 개의 링크를 모두 갱신하는 것에 주의했습니다."

## Follow-up
- **단일 연결로 하면 뭐가 달라지나?** dummy head 하나만 두면 prev 추적을 위해 직전 노드를 따로 들고 다녀야 하고, addAtTail은 tail 포인터가 없으면 O(N)이 된다. 이중 연결은 양끝 O(1)과 양방향 순회가 장점이다.
- **왜 양끝에 sentinel 두 개?** head 더미만으로도 앞쪽 삽입은 통일되지만, tail 더미까지 두면 addAtTail/deleteLast도 분기 없이 같은 패턴으로 처리되어 코드 중복과 경계 버그가 함께 사라진다.
