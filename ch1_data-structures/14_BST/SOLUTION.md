# BST (Binary Search Tree, 직접 구현) — 해설

> 막히기 전에 골격(`BST.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
서로 다른 int 값을 저장하는 이진 탐색 트리를 직접 구현한다. `TreeMap`/`TreeSet` 금지.
`insert(val)`(중복 무시), `search(val)`, `delete(val)`(없으면 무시), `inorder()`(오름차순 리스트).

## 핵심 직관 (1줄)
**"Node 반환 패턴"** — `helper(node, val)`이 새(또는 같은) 노드를 반환하고, 부모가 그 반환값을 자기 left/right에 재대입한다. 그러면 `root = helper(root, val)` 한 줄로 root 변경도 자동 처리된다.

## 자료구조
- `Node` : `int val`, `Node left, right`.
- `Node root`.

## 알고리즘
1. `insert` : `node == null`이면 새 노드 반환. `val < node.val`이면 왼쪽, `>`이면 오른쪽으로 재귀. 같으면 무시(중복).
2. `search` : null이면 false, 같으면 true, 작으면 왼쪽 / 크면 오른쪽으로 재귀.
3. `delete` : 값을 찾아 내려간 뒤 세 케이스 처리(아래).
4. `inorder` : 왼쪽 → 자기 → 오른쪽 순 재귀하면 정렬된 순서가 나온다.

## delete의 세 케이스 (핵심 함정)
- **자식 0개(리프)** : `node.left == null`이면 `node.right`(=null) 반환 → 자연스럽게 제거.
- **자식 1쪽** : `node.left == null`이면 `node.right` 반환 / `node.right == null`이면 `node.left` 반환. (위 두 줄로 케이스 0·1 통합)
- **자식 2쪽** : 오른쪽 서브트리의 최솟값(in-order successor)을 `findMin`으로 찾아 **그 값을 현재 노드에 복사**하고, 그 successor를 오른쪽 서브트리에서 재귀 삭제.

## 불변식 (BST property)
모든 노드 N에 대해 왼쪽 서브트리의 모든 값 `< N.val`, 오른쪽 서브트리의 모든 값 `> N.val`. → inorder가 항상 오름차순.

## 복잡도
- insert/search/delete : 평균 O(log n), 최악 O(n)(한쪽으로 치우친 불균형 트리).
- inorder : O(n).

## 함정 ⚠️
- delete 자식 2쪽에서 successor의 **값만 복사**하고 그 successor 노드를 오른쪽에서 재귀 삭제해야 한다. successor는 왼쪽 자식이 없으므로(=최솟값) 재귀 삭제가 다시 케이스 0/1로 끝나 무한 루프가 안 생긴다.
- 모든 helper가 노드를 반환하고 부모가 재대입하지 않으면(`node.left = helper(...)`를 빼면) 트리 변경이 반영되지 않는다.
- 중복 insert는 `==` 분기에서 아무것도 안 해야(무시) 한다.

## 대안 / 최적화
- 최악 O(n)을 막으려면 자가 균형 트리(AVL, Red-Black)를 쓴다. 회전(rotation)으로 높이를 O(log n)으로 유지.
- delete의 successor 대신 predecessor(왼쪽 서브트리 최댓값)를 써도 대칭으로 동작.

## 면접 답변 (한국어 1분)
> "재귀 helper가 노드를 반환하고 부모가 그 반환값을 left/right에 재대입하는 패턴으로 짰습니다.
> 이러면 root 변경도 root = helper(root, val) 한 줄로 처리됩니다. insert는 빈 자리면 새 노드,
> 작으면 왼쪽 크면 오른쪽으로 내려가고 같으면 무시합니다. delete가 핵심인데 세 케이스입니다.
> 자식이 없거나 한쪽뿐이면 그 자식(또는 null)을 반환하고, 양쪽이면 오른쪽 서브트리의 최솟값인
> in-order successor의 값을 복사한 뒤 그 successor를 오른쪽에서 재귀 삭제합니다. inorder는 왼-자기-오른
> 순서라 정렬된 결과가 나옵니다. 평균 O(log n), 불균형 최악은 O(n)입니다."

## Follow-up
- **왜 successor를 쓰나?** 자식 2개 노드를 지우면서 BST 불변식을 유지하려면, 그 자리에 올 값이 "왼쪽 전부보다 크고 오른쪽 전부보다 작은" 값이어야 한다. 오른쪽 서브트리의 최솟값(successor)이 정확히 그 조건을 만족한다(predecessor=왼쪽 최댓값도 가능).
- **최악 O(n)을 어떻게 막나?** AVL/Red-Black 같은 균형 트리로 회전을 통해 높이를 O(log n) 유지.
- **inorder가 왜 정렬인가?** BST 불변식상 왼쪽<자기<오른쪽이므로 왼-자기-오른 순회가 오름차순.
