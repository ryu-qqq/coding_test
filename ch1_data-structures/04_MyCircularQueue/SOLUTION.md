# MyCircularQueue (LC 622) — 해설

> 막히기 전에 골격(`MyCircularQueue.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
고정 용량의 원형 큐. 다 차면 enQueue 실패(false). `enQueue/deQueue/Front/Rear/isEmpty/isFull`.

## 핵심 직관 (1줄)
모듈러 산술로 인덱스를 회전시킨다 — `tail = (tail + 1) % capacity`.

## 자료구조
- `int[] arr`, `int capacity`, `int head`, `int tail`, `int size`.

## 알고리즘
1. 초기화: `head=0, tail=-1, size=0`.
2. `enQueue(v)`: isFull이면 false. 아니면 tail을 +1 회전, `arr[tail]=v`, size++.
3. `deQueue()`: isEmpty면 false. head를 +1 회전, size--.
4. `Front()`/`Rear()`: `arr[head]`/`arr[tail]`, 비었으면 -1.

## 불변식
0 ≤ size ≤ capacity. head, tail 모두 [0, capacity) 범위.

## 복잡도
- 모든 연산 O(1), 공간 O(capacity).

## 함정 ⚠️
- tail 초기값을 -1로 두면 첫 enQueue에서 `(-1+1)%cap = 0`으로 자연 처리된다. head=tail=0으로 시작하면 첫 enQueue에 if 분기가 필요해진다.
- `size` 변수 없이 head/tail만으로 isEmpty/isFull을 구분하려면 한 칸을 항상 비워둬야 한다(`(tail+1)%cap == head`이면 full). size를 두면 더 직관적.

## 대안 / 최적화
한 칸 sentinel 방식이면 size 변수 없이 가능 → 메모리 1개 절약(단 실제 저장은 capacity-1개). 멀티스레드라면 ring buffer + atomic head/tail.

## 면접 답변 (한국어 1분)
> "고정 크기 원형 큐 문제인데, 배열 하나와 head, tail, size 세 변수로 풀었습니다. 핵심은 인덱스를 모듈러로 회전시키는 거고, tail은 -1로 초기화해서 첫 enQueue가 (-1+1)%cap = 0으로 자연스럽게 0번째에 들어가도록 했습니다. size 변수를 따로 둬서 isEmpty와 isFull 판단을 단순하게 만들었습니다. size 없이 head, tail만으로 구분하려면 한 칸을 항상 비워둬야 하는데, 그 트릭보다 size를 두는 게 코드가 명확합니다. 모든 연산이 O(1)입니다."

## Follow-up
- **size 변수 없이 풀 수 있나?** 한 칸을 sentinel로 비워두는 방식이 있다. `(tail+1)%cap == head`면 full로 판단. 다만 capacity-1만큼만 실제 저장 가능하고 코드가 살짝 트릭해진다.
- **tail 초기값을 0으로 하면?** 가능하지만 첫 enQueue에서 "비어있을 때는 회전하지 말기" 분기가 필요해진다. -1로 시작하면 모든 enQueue가 동일하게 처리된다.
