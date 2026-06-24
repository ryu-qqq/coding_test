# MyCircularDeque (LC 641) — 해설

> 막히기 전에 골격(`MyCircularDeque.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
고정 용량의 원형 덱. 양쪽(front/rear)에서 삽입/삭제. 다 차면 삽입 실패(false).

## 핵심 직관 (1줄)
원형 큐와 동일하되 head/tail을 양방향으로 회전 — 음수 모듈러 보정 `(head - 1 + capacity) % capacity`가 관건.

## 자료구조
- `int[] arr`, `int capacity`, `int size`, `int head`, `int tail`. (초기 `head=0, tail=-1`)

## 알고리즘
1. `insertFront(v)`: head를 -1 회전(보정 필수), `arr[head]=v`, size++.
2. `insertLast(v)`: tail을 +1 회전, `arr[tail]=v`, size++.
3. `deleteFront()`: head를 +1 회전, size--.
4. `deleteLast()`: tail을 -1 회전(보정), size--.
5. `getFront()`/`getRear()`: `arr[head]`/`arr[tail]`, 비었으면 -1.

## 불변식
0 ≤ size ≤ capacity. head, tail 모두 [0, capacity) 범위.

## 복잡도
- 모든 연산 O(1), 공간 O(capacity).

## 함정 ⚠️
- **자바의 `%`는 음수에 대해 음수를 반환** → `(-1) % 5 == -1`. 따라서 head를 뒤로 보낼 땐 `(head - 1 + capacity) % capacity`로 양수 보정을 반드시 한다(deleteLast의 tail도 동일).
- insertFront/insertLast가 head/tail 중 어느 쪽을 움직이는지 헷갈리기 쉽다. "Front 삽입은 head를 한 칸 앞당기고(-1), Last 삽입은 tail을 한 칸 뒤로 민다(+1)"로 외운다.

## 대안 / 최적화
`java.util.ArrayDeque`가 사실상 같은 구조. 첫 원소 삽입 시 head와 tail을 같은 칸으로 명시 동기화하면(예: `if (size==0) tail=head;`) 초기 상태 가정에 덜 의존하는 더 견고한 구현이 된다.

## 면접 답변 (한국어 1분)
> "원형 덱이라 양쪽에서 삽입과 삭제가 모두 가능해야 합니다. 큐와 비슷하게 배열, head, tail, size로 풀었는데, 차이점은 head를 거꾸로 움직일 때입니다. 자바의 `%` 연산자는 음수에 대해 음수를 그대로 돌려주기 때문에, `(head - 1) % capacity`로는 음수가 나옵니다. 그래서 `(head - 1 + capacity) % capacity`로 양수 보정을 해줘야 합니다. insertFront는 head를 앞당기고 insertLast는 tail을 뒤로 미는 식으로 대칭적으로 처리합니다. 모든 연산이 O(1)입니다."

## Follow-up
- **왜 `+ capacity` 보정이 필요한가?** 자바의 `%`가 부호를 그대로 유지하기 때문. head가 0일 때 `(0 - 1) % 5`는 -1이 나오는데, capacity를 더하면 -1+5=4가 되어 정상 인덱스가 된다. C++/파이썬과 다른 점.
- **ArrayDeque를 쓰면 안 되나?** 표준 라이브러리도 동일 동작. 다만 LC 622/641류는 직접 구현 의도라 배열 기반으로 푼다.
