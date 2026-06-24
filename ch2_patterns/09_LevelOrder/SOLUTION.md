# Binary Tree Level Order Traversal (LC 102) — 해설

> 막히기 전에 골격(`LevelOrder.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
이진 트리를 레벨 단위로 순회해 각 레벨의 값을 리스트로 반환한다.

## 핵심 직관 (1줄)
매 while 시작에서 큐 사이즈를 고정해 한 레벨치만 처리한다.

## 자료구조
- BFS 큐 (`ArrayDeque` 또는 `LinkedList`).

## 알고리즘
1. root가 null이면 빈 리스트.
2. 큐에 root를 넣고 시작.
3. `while 큐 not empty`: `levelSize = q.size()`. `for 0..levelSize-1`: poll, val 추가, 자식 offer.
   레벨 리스트를 결과에 추가.

## 불변식
while 진입 시점에 큐에는 "현재 레벨" 노드들만 들어 있다.

## 복잡도
- 시간: O(N)
- 공간: O(N) (큐)

## 함정 ⚠️
- `levelSize`를 고정하지 않고 큐에서 그대로 꺼내면 다음 레벨과 섞인다 → 고정 필수.
- 빈 트리는 빈 리스트.

## 대안 / 최적화
DFS로 깊이 인자를 들고 다니며 `res[depth]`에 추가하는 방식도 가능하다.

## 면접 답변 (한국어 1분)
> "BFS 큐를 사용하고, 매 while 진입 시점에 큐 크기를 고정해서 한 레벨치만 처리하는 게 핵심입니다.
> 큐에 root를 넣고 시작해서, while 안에서 levelSize를 q.size()로 잡고 그만큼만 꺼내 한 레벨 리스트를
> 만듭니다. 자식들을 다시 큐에 넣으면 다음 while에서 다음 레벨이 처리됩니다. levelSize를 고정 안 하면
> 자식까지 같이 꺼내져 레벨이 섞입니다. 시간 O(N), 공간 O(N)입니다."

## Follow-up
- **지그재그 순회 (LC 103)는?** levelOrder와 거의 같은데, 짝수/홀수 레벨에 따라 level 리스트를
  reverse하거나 Deque의 양쪽에 add하는 방식으로 처리한다.
