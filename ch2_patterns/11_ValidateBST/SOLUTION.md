# Validate Binary Search Tree (LC 98) — 해설

> 막히기 전에 골격(`ValidateBST.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
주어진 이진 트리가 BST인지 검증한다. 왼쪽 서브트리의 모든 값은 root보다 strictly 작고, 오른쪽은
strictly 크며, 좌/우 서브트리 또한 BST다.

## 핵심 직관 (1줄)
노드마다 허용 범위 `(lower, upper)`를 좁혀가며 내려간다.

## 자료구조
- 재귀 with `(lower, upper)` 범위 (또는 in-order 비교).

## 알고리즘
- `validate(node, lower, upper)`: null → true. `node.val <= lower || node.val >= upper` → false.
  좌 `(lower, val)` && 우 `(val, upper)` 재귀.
- 초기 호출: `(root, -inf, +inf)`. 자바에선 `Long.MIN_VALUE` / `Long.MAX_VALUE`.

## 불변식
재귀 진입 시 node 값은 `(lower, upper)` 범위 안에 있어야 한다.

## 복잡도
- 시간: O(N)
- 공간: O(H)

## 함정 ⚠️
- 자식 직접 비교만 하면 부모-자손 관계를 놓친다 (예: 5의 오른쪽 서브트리에 4가 끼는 경우).
- `val`이 `Integer.MIN/MAX_VALUE`까지 올 수 있어 `long` 사용을 권장한다.
- "strictly" 증가 — 같은 값은 BST가 아니다.

## 대안 / 최적화
in-order 순회로 `prev`와 비교(strictly increasing 검사)하는 방식도 동일한 복잡도다. (정답 파일에
`inOrder` 보조 메서드로 남겨두었다.)

## 면접 답변 (한국어 1분)
> "범위 기반 재귀 풀이입니다. 각 노드를 방문할 때 그 노드가 가질 수 있는 값의 범위 (lower, upper)를
> 인자로 같이 내려보냅니다. 노드 값이 범위를 벗어나면 false, 아니면 좌측엔 (lower, val), 우측엔
> (val, upper)로 좁혀서 재귀합니다. 초기 호출은 (-inf, +inf)인데 자바에선 Long.MIN/MAX_VALUE로 두면
> int 경계값에서도 안전합니다. 단순히 자식과 부모만 비교하면 5의 오른쪽 서브트리에 4가 끼는 케이스를
> 못 잡습니다. 시간 O(N), 공간 O(H)이고, in-order로 prev와 strictly 증가를 검사하는 방식도 동일합니다."

## Follow-up
- **왜 단순 부모-자식 비교로는 안 되나?** BST는 서브트리 전체에 대한 조건이지 인접 노드만의 조건이
  아니기 때문이다. 5의 오른쪽 자식 7, 그 왼쪽 자식 4면 부모-자식 비교는 통과하지만 4 < 5인데 5의
  오른쪽 서브트리에 있어 BST가 아니다.
- **왜 long을 쓰나?** 노드 val이 `Integer.MIN/MAX_VALUE`일 수 있어 초기 범위를 같은 int로 잡으면
  경계값 비교가 틀린다. long으로 한 단계 위 범위를 잡아야 안전하다.
