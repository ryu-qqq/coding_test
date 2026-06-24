# NumberOfProvinces (LC 547) — 해설

> 막히기 전에 골격(`NumberOfProvinces.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
`isConnected[i][j] == 1`이면 도시 i, j가 직접 연결. 직접/간접으로 연결된 도시 그룹(연결 컴포넌트)의 개수를 구한다.

## 핵심 직관 (1줄)
연결된 도시들을 하나의 집합으로 합치고(Union-Find), 남은 집합(컴포넌트)의 수가 곧 도(province) 수.

## 자료구조
- Union-Find: `parent[]` + `count`(현재 컴포넌트 수)
- 대안: DFS/BFS + `visited[]`

## 알고리즘 (Union-Find)
1. `parent[i] = i`로 초기화, `count = n`.
2. `i < j` 상삼각만 순회: `isConnected[i][j] == 1`이면 `union(i, j)`. union이 실제로 두 집합을 합칠 때만 `count--`.
3. 최종 `count` 반환.

`find`는 경로 압축(`parent[i] = find(parent[i])`)으로 트리를 평탄화한다.

## 알고리즘 (DFS 대안)
1. `visited = new boolean[n]`, `count = 0`.
2. `for i: !visited[i]`이면 `dfs(i); count++`.
3. `dfs(u)`: `visited[u]=true`; `isConnected[u][v]==1 && !visited[v]`인 v로 재귀.

## 불변식
같은 컴포넌트의 노드들은 항상 동일한 root를 가진다. `count`는 항상 현재까지의 컴포넌트 수.

## 복잡도
- 시간: O(N^2 * α(N)) ≈ O(N^2) — 행렬 전체를 한 번 훑는다
- 공간: O(N) — parent 배열

## 함정 ⚠️
- 행렬은 대칭이고 대각선은 1(자기 자신). 그래서 `j = i+1`부터 상삼각만 봐도 충분하다.
- union 시 이미 같은 root면 `count`를 줄이면 안 된다(중복 감소 방지).
- `find`에서 경로 압축을 빼면 트리가 깊어져 느려진다.

## 대안 / 최적화
- DFS/BFS로도 O(N^2)에 풀린다(코드는 더 짧을 수 있음).
- Union-Find에 rank/size 기반 합치기를 더하면 트리 높이를 더 낮출 수 있다.

## 면접 답변 (한국어 1분)
> "Union-Find로 풀었습니다. 모든 도시를 자기 자신을 부모로 초기화하고, 인접 행렬의 상삼각만 훑으면서 연결된
> 쌍을 union합니다. union이 실제로 서로 다른 두 집합을 합칠 때만 컴포넌트 수를 1 줄입니다. 행렬이 대칭이고
> 대각선이 1이라 j는 i+1부터 보면 충분합니다. find에는 경로 압축을 적용해 트리를 평탄화합니다. 최종 컴포넌트
> 수가 곧 도의 개수입니다. 시간은 행렬 전체를 보니 O(N^2), 공간은 parent 배열로 O(N)입니다. DFS/BFS로도
> 같은 복잡도로 풀 수 있습니다."

## Follow-up
- **DFS와 Union-Find 중 무엇을 쓰나?** 정적 그래프의 컴포넌트 수만 구하면 DFS가 간결하다. 간선이 점진적으로 추가되며 연결 여부를 계속 질의하는 동적 상황이면 Union-Find가 유리하다.
- **rank/size 합치기를 더하면?** 작은 트리를 큰 트리 밑에 붙여 트리 높이를 낮춰, 경로 압축과 함께 거의 상수 시간(α) 연산을 보장한다.
