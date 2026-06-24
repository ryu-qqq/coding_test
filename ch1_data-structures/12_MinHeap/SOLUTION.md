# MinHeap (직접 구현) — 해설

> 막히기 전에 골격(`MinHeap.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
배열 기반 이진 최소 힙을 직접 구현한다. Java `PriorityQueue` 금지.
`insert(val)`, `extractMin()`, `peek()`, `size()`.

## 핵심 직관 (1줄)
배열로 완전 이진 트리를 표현한다 — 인덱스 i의 부모 `(i-1)/2`, 좌/우 자식 `2i+1`, `2i+2`.

## 자료구조
- `int[] arr` + `int size` (+ 동적 확장용 `capacity`).
- 트리를 따로 만들 필요 없이 인덱스 산식만으로 부모/자식을 찾는다.

## 알고리즘
1. `insert(v)` : `arr[size++] = v` 로 끝에 추가 후 `siftUp(size-1)`.
2. `extractMin()` : `min = arr[0]` 저장 → 마지막 원소를 root로 옮기고 `size--` → `siftDown(0)` → `min` 반환.
3. `siftUp(i)` : 부모보다 작으면 swap하며 위로.
4. `siftDown(i)` : 두 자식 중 **더 작은 쪽**과 비교, 자기보다 작으면 swap하며 아래로.
5. 가득 차면 `Arrays.copyOf`로 배열을 2배 확장.

## 불변식 (Heap Property)
모든 노드 i에 대해 `arr[parent(i)] <= arr[i]`. 따라서 root(`arr[0]`)가 항상 최솟값.

## 복잡도
- insert O(log n), extractMin O(log n), peek O(1), size O(1).
- buildHeap(임의 배열 heapify)은 O(n)으로 가능(도전 과제).

## 함정 ⚠️
- extractMin에서 **마지막 원소를 root로 옮긴다**. 중간을 비우면 완전 이진 트리 형태가 깨져 인덱스 산식이 무너진다.
- siftDown은 반드시 **두 자식을 모두 비교**해 더 작은 쪽과 swap. 아무 자식이나 swap하면 힙 속성이 깨진다.
- siftUp 종료 조건은 `arr[i] >= arr[parent]`(즉 부모 이하로 못 내려갈 때). 빈 힙 extractMin은 호출 안 된다는 가정(또는 예외 처리).

## 대안 / 최적화
- 실무에선 `PriorityQueue` 사용.
- buildHeap: `i = n/2 - 1`부터 0까지 거꾸로 siftDown 호출하면 O(n)에 힙 구성(트리 절반은 leaf라 작업 불필요, 깊이별 비용 합이 O(n)으로 수렴).

## 면접 답변 (한국어 1분)
> "배열 기반 이진 힙입니다. 인덱스 i의 부모는 (i-1)/2, 좌우 자식은 2i+1, 2i+2 공식을 씁니다. insert는
> 배열 끝에 추가하고 siftUp으로 부모와 비교하며 올립니다. extractMin은 root를 반환하고, 마지막
> 원소를 root로 옮긴 뒤 siftDown으로 더 작은 자식과 비교하며 내려갑니다. 마지막 원소를 올리는 이유는
> 완전 이진 트리 형태를 유지하기 위함이고, siftDown에서 두 자식 중 더 작은 쪽과 swap하는 게 핵심입니다.
> insert와 extractMin은 트리 깊이에 비례해 O(log n), peek은 O(1)입니다."

## Follow-up
- **extractMin에서 왜 마지막을 root로?** 완전 이진 트리 형태를 유지하기 위해. 중간을 비우면 형태와 인덱스 산식이 깨진다.
- **buildHeap을 O(n)에?** `n/2-1`부터 0까지 거꾸로 siftDown. leaf는 건너뛰고 깊이별 비용 합이 O(n).
- **max-heap이 필요하면?** 비교를 뒤집거나 값에 -1을 곱해 저장.
