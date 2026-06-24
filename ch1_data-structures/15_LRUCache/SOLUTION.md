# LRUCache (LC 146) — 해설

> 막히기 전에 골격(`LRUCache.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
용량 `capacity`인 LRU(Least Recently Used) 캐시. `get(key)`(없으면 -1)와 `put(key, value)` 모두 O(1).
용량 초과 시 가장 오래 사용되지 않은 항목을 evict.

## 핵심 직관 (1줄)
HashMap으로 O(1) 검색, Doubly Linked List로 O(1) 임의 노드 제거 + head 이동 → eviction은 tail 노드 삭제.

## 자료구조
- `Map<Integer, Node> map` : key → Node, O(1) 검색.
- DLL(dummy head/tail sentinel) : head 쪽이 가장 최근, tail 쪽이 가장 오래됨.
- `Node` : `key, value, prev, next` — **key를 같이 저장**(evict 시 map.remove에 필요).

## 알고리즘
1. `get(key)` : map에 없으면 -1. 있으면 그 노드를 `moveToHead`(=removeNode + addToHead) 후 value 반환.
2. `put(key, value)` :
   - 있으면 value 갱신 + moveToHead.
   - 없으면 새 노드 생성 → map 등록 → addToHead. 그 후 `map.size() > capacity`면 `removeTail`로 evict하고 `map.remove(lru.key)`.

## 불변식
- `map.size() <= capacity`.
- DLL의 노드 집합 == map의 value 집합.
- head 쪽이 가장 최근, tail 쪽이 가장 오래됨.

## 복잡도
- get/put 모두 O(1).
- 공간 O(capacity).

## 함정 ⚠️
- **Node에 key를 저장해야 한다.** evict 시 tail 노드를 지우면서 `map.remove(node.key)`를 호출해야 하는데, key가 없으면 어떤 항목을 지울지 모른다.
- 단방향 LinkedList는 임의 노드의 prev를 모르므로 제거가 O(N) → **DLL 필수**.
- dummy head/tail sentinel을 두면 head/tail 경계의 null 체크가 사라져 코드가 간결.
- `moveToHead = removeNode + addToHead`.

## 대안 / 최적화
- Java `LinkedHashMap`을 `accessOrder=true`로 두고 `removeEldestEntry`를 override하면 몇 줄로 가능. 다만 면접에선 자료구조 이해를 보려 직접 구현을 요구.

## 면접 답변 (한국어 1분)
> "HashMap과 Doubly Linked List 조합입니다. HashMap은 key → Node로 O(1) 검색, DLL은 임의 노드
> 제거와 head 이동을 O(1)에 합니다. dummy head/tail sentinel을 두면 null 체크가 사라집니다.
> get은 노드를 찾아 head로 옮기고 value 반환, put은 있으면 갱신+head 이동, 없으면 head에 추가하고
> 사이즈 초과 시 tail 노드를 evict합니다. 가장 중요한 트릭은 Node에 key도 저장하는 건데, evict 시
> 그 노드의 key를 알아야 map에서도 같이 지울 수 있기 때문입니다. 단방향 LinkedList면 prev를 몰라
> 임의 제거가 O(N)이라 DLL이 필수입니다."

## Follow-up
- **왜 Node에 key를 저장?** tail을 evict할 때 `map.remove(key)`를 호출해야 하므로. value만 있으면 어떤 key를 지울지 모른다.
- **LinkedHashMap으로 풀면?** `accessOrder=true` + `removeEldestEntry` override로 한두 줄. 면접에선 직접 구현을 시킨다.
- **왜 단방향 LinkedList는 안 되나?** 임의 노드를 head로 옮길 때 prev를 알 수 없어 prev를 찾으려면 head부터 O(N) 순회 → O(1) 보장이 깨진다.
