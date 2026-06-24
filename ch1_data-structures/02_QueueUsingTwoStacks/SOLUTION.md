# QueueUsingTwoStacks (LC 232) — 해설

> 막히기 전에 골격(`QueueUsingTwoStacks.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
스택 두 개만으로 FIFO 큐를 구현한다. `push(x)`, `pop()`, `peek()`, `empty()`.

## 핵심 직관 (1줄)
out 스택(`subQueue`)이 비었을 때만 in 스택(`mainQueue`)을 통째로 옮긴다 → 옮기는 과정에서 자연스레 역순이 되어 FIFO가 성립.

## 자료구조
- `mainQueue` (in 스택): push 전용. 새 원소가 쌓이는 곳.
- `subQueue` (out 스택): pop/peek 전용. in을 뒤집어 옮겨 담은 곳.
- 둘 다 `ArrayDeque`를 스택으로 사용.

## 알고리즘
1. `push(x)`: 무조건 `mainQueue.push(x)`.
2. `pop()`/`peek()`: `subQueue`가 비어있으면 `mainQueue`를 전부 옮긴 뒤(역순됨), `subQueue`에서 pop/peek.
3. `empty()`: 두 스택 모두 비어야 true.

## 불변식
`subQueue`의 top은 항상 "현재 큐에서 가장 오래된 원소".

## 복잡도
- push: O(1)
- pop/peek: **amortized O(1)** (단일 호출은 worst O(N)이지만 각 원소는 평생 최대 3번 연산만 받음)
- 공간: O(N)

## 함정 ⚠️
- 매 pop마다 in→out으로 옮기면 순서가 깨진다. 반드시 **out이 완전히 비었을 때만** 옮긴다 (중간에 옮기면 더 새로운 원소가 오래된 원소 위에 쌓여 FIFO가 깨짐).
- `ArrayDeque`에서 스택 의미를 원하면 `push`/`pop`/`peek`로 통일. `offer`/`add`를 섞으면 큐 동작이 되어버린다.

## 대안 / 최적화
한 개의 Deque로 양방향 접근하면 그냥 큐가 되니 의미 없음. 비용을 push 쪽에 몰면(LC 225 StackUsingQueue) trade-off가 반대가 된다.

## 면접 답변 (한국어 1분)
> "스택 두 개로 큐를 만드는 문제인데, 핵심은 in 스택과 out 스택 두 개를 두는 겁니다. push는 무조건 in에 넣고, pop이나 peek은 out에서 가져옵니다. out이 비어있을 때만 in을 전부 옮기는데, 옮기는 과정에서 자연스럽게 순서가 뒤집혀서 가장 오래된 원소가 out의 top이 됩니다. 시간복잡도는 push는 O(1)이고, pop은 amortized O(1)입니다. 각 원소가 in에 push되고, out으로 옮겨지고, out에서 pop되는 세 번의 연산만 받으니까 평균적으로 O(1)이 됩니다. 주의할 점은 매 pop마다 옮기면 안 되고 out이 완전히 비었을 때만 옮겨야 순서가 보장된다는 겁니다."

## Follow-up
- **Amortized O(1)이 무슨 뜻?** 단일 호출은 worst O(N)이지만, N번 연산 총합을 N으로 나누면 O(1)이라는 뜻. 각 원소가 평생 최대 3번 연산(in push, in→out 이동, out pop)만 받으므로 N개 원소에 대해 총 연산 수가 3N이라 평균 O(1).
- **왜 out이 비었을 때만 옮기나?** 중간에 옮기면 in에 쌓인 더 새로운 원소가 out의 오래된 원소 위에 올라가 FIFO가 깨진다.
