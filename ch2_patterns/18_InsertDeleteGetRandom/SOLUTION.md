# InsertDeleteGetRandom (LC 380) — 해설

> 막히기 전에 골격(`InsertDeleteGetRandom.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
`insert` / `remove` / `getRandom`이 모두 평균 O(1)인 자료구조.

## 핵심 직관 (1줄)
getRandom O(1)을 위해 ArrayList(인덱스 접근), remove O(1)을 위해 value→index 맵 + 마지막 원소와 swap.

## 자료구조
- `List<Integer> list`: 실제 값 저장(인덱스 랜덤 접근용)
- `Map<Integer,Integer> idxMap`: 값 → list 내 인덱스
- `Random rng`

## 알고리즘
- `insert(v)`: 맵에 있으면 false. 없으면 `list.add(v)`, `idxMap.put(v, list.size()-1)`, true.
- `remove(v)`: 맵에 없으면 false. `idx = idxMap.get(v)`, `last = list`의 마지막 값. `list.set(idx, last)`, `idxMap.put(last, idx)`, 마지막 원소 제거, `idxMap.remove(v)`, true.
- `getRandom()`: `list.get(rng.nextInt(list.size()))`.

## 불변식
`list`와 `idxMap`은 동일한 원소 집합을 표현한다. 모든 v에 대해 `list[idxMap[v]] == v`.

## 복잡도
- 모든 연산 평균 O(1)
- 공간 O(N)

## 함정 ⚠️
- ArrayList의 임의 인덱스 remove는 뒤 원소를 당겨야 해서 O(N) → 반드시 마지막 원소와 swap 후 removeLast.
- "삭제할 인덱스가 곧 마지막 인덱스"인 케이스도 일관되게 동작해야 한다(set은 같은 자리, removeLast).
- remove 시 `idxMap`의 last 인덱스 갱신을 빠뜨리지 말 것.

## 대안 / 최적화
중복 값을 허용하는 LC 381(RandomizedCollection)이라면 맵의 값을 인덱스 집합(`Set<Integer>`)으로 확장한다.

## 면접 답변 (한국어 1분)
> "ArrayList와 HashMap을 조합했습니다. getRandom을 O(1)에 하려면 인덱스 접근이 되는 ArrayList가 필요하고,
> remove를 O(1)에 하려면 value→index 맵을 두고 마지막 원소와 swap하는 트릭을 씁니다. insert는 ArrayList 끝에
> 추가하고 맵에 인덱스를 저장합니다. remove는 삭제할 인덱스 idx와 마지막 원소 last를 찾아, list[idx]에 last를
> 덮어쓰고 map[last]를 idx로 갱신한 뒤, 마지막을 제거하고 map에서 val을 지웁니다. 이러면 ArrayList의 임의
> 인덱스 remove O(N)을 피해 모두 평균 O(1)이 됩니다."

## Follow-up
- **왜 마지막과 swap하나?** ArrayList 임의 인덱스 remove는 뒤 원소를 모두 한 칸씩 당겨 O(N). 마지막을 삭제 자리에 덮고 마지막을 제거하면 O(1).
- **중복 값을 허용해야 한다면(LC 381)?** 맵 값을 `Set<Integer>` 인덱스 집합으로 바꾼다. remove/insert가 살짝 복잡해지지만 여전히 평균 O(1).
