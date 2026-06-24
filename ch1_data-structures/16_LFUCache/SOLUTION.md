# LFUCache (LC 460) — 해설

> 막히기 전에 골격(`LFUCache.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
용량 `capacity`인 LFU(Least Frequently Used) 캐시. 빈도가 가장 낮은 항목을 evict하되, **동률이면 그 중 LRU**. get/put 모두 O(1).

## 핵심 직관 (1줄)
빈도별로 별도 DLL을 둬서 같은 빈도 안에서 LRU를 적용한다. evict는 `minFreq` DLL의 tail.

## 자료구조
- `keyMap` : `Map<key, Node>` (Node = {key, value, freq, prev, next}).
- `freqMap` : `Map<freq, DLL>` — 각 빈도별 LRU 리스트.
- `minFreq` : 현재 살아있는 최소 빈도.

## 알고리즘
- `get(key)` : 없으면 -1. 있으면 `increaseFreq(node)` 후 value 반환.
- `put(key, value)` :
  - `capacity == 0`이면 무시.
  - 있으면 value 갱신 + `increaseFreq`.
  - 없는데 사이즈가 capacity면 `freqMap[minFreq].removeTail()`로 victim 제거 + `keyMap`에서도 제거.
  - 새 노드(freq=1) 추가 → `freqMap[1]`의 head에 add → `minFreq = 1` 강제 리셋.
- `increaseFreq(node)` :
  - old freq DLL에서 제거. **그 DLL이 비었고 `minFreq == oldFreq`면 `minFreq++`**.
  - `node.freq++` → `freqMap[node.freq]`의 head에 add.

## 불변식
- 모든 `freqMap[f]`의 노드들은 `freq == f`.
- `keyMap.size() == sum(freqMap[f].size())`.
- `minFreq <=` 모든 살아있는 노드의 freq.

## 복잡도
- get/put 모두 O(1). 공간 O(capacity).

## 함정 ⚠️
- **새 원소 put 시 `minFreq = 1` 강제 리셋** (새 노드는 freq=1이라 1보다 낮은 빈도가 존재할 수 없음).
- **`increaseFreq` 후 minFreq 증가는 old DLL이 비었고 `minFreq == oldFreq`일 때만**. 아니면 minFreq가 잘못 올라가 evict 대상을 놓친다.
- DLL의 `size`를 add/remove마다 정확히 관리해야 한다(evict 시 빈 DLL 판별에 쓰임).

## 대안 / 최적화
- 빈도별 DLL 대신 `TreeMap<freq, LinkedHashSet<key>>`도 가능하지만 `firstKey`가 O(log)라 엄밀히 O(1)이 아니다. 빈도별 HashMap + DLL이 진정한 O(1).

## 면접 답변 (한국어 1분)
> "LRU보다 어렵습니다. 자료구조 세 개를 씁니다. keyMap은 key→Node, freqMap은 빈도별 DLL,
> minFreq는 현재 최소 빈도입니다. 같은 빈도 안에서 LRU를 적용해야 하므로 빈도별 DLL을 두고,
> evict는 minFreq DLL의 tail에서 합니다. get/put 시 freq가 오르는데 increaseFreq에서 old freq DLL
> 에서 빼고 new freq DLL의 head에 넣습니다. 핵심 트릭 두 개는, 새 원소 put 시 minFreq를 1로 강제
> 리셋하는 것과, increaseFreq 후 minFreq 증가는 old DLL이 비고 minFreq==oldFreq일 때만 한다는
> 겁니다. 그렇지 않으면 minFreq가 잘못 올라가 evict 대상을 놓칩니다. get/put 모두 O(1)입니다."

## Follow-up
- **왜 빈도별로 DLL을 따로?** evict 시 "최소 빈도 그룹에서 LRU"를 O(1)에 찾으려면. 한 DLL에 다 넣으면 minFreq 그룹의 LRU를 찾는 데 O(N).
- **minFreq는 언제 갱신?** 두 시점. 새 원소 put 시 무조건 1로 리셋. increaseFreq에서 old DLL이 비고 minFreq==oldFreq일 때만 +1.
- **TreeMap으로도 가능?** 가능하지만 firstKey가 O(log)라 엄밀히 O(1)이 아니다.
