# MyHashMap (LC 706) — 해설

> 막히기 전에 골격(`MyHashMap.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
`int → int` 해시맵을 직접 구현한다. Java의 `HashMap`을 쓰면 안 된다.
`put(key, value)`, `get(key)`(없으면 -1), `remove(key)`.

## 핵심 직관 (1줄)
고정 크기 버킷 배열 + 버킷마다 연결 리스트로 충돌을 처리하는 **분리 연쇄(separate chaining)**.

## 자료구조
- `LinkedList<int[]>[] buckets` : 크기 `SIZE`(=1000)의 버킷 배열. 각 버킷은 `{key, value}` 쌍의 리스트.
- `hash(key) = key % SIZE` : 키를 버킷 인덱스로 매핑.

## 알고리즘
1. `put(k,v)` : `buckets[hash(k)]` 버킷을 선형 탐색해 같은 key가 있으면 value를 덮어쓰고, 없으면 `{k,v}`를 append.
2. `get(k)` : 해당 버킷을 선형 탐색해 key가 있으면 value, 없으면 -1.
3. `remove(k)` : 해당 버킷을 `Iterator`로 순회하다 key를 찾으면 `it.remove()`.

## 불변식
- 같은 key는 전체에서 단 하나만 존재한다(put이 중복을 덮어쓰므로).
- key는 항상 `key % SIZE` 버킷에만 들어 있다.

## 복잡도
- 시간: 충돌이 적으면 모든 연산 amortized O(1). 최악(모든 키가 같은 버킷)에는 O(N) 선형 탐색.
- 공간: O(SIZE + 저장된 원소 수).

## 함정 ⚠️
- 순회 도중 삭제는 반드시 `Iterator.remove()`. for-each 안에서 `bucket.remove()`를 호출하면 `ConcurrentModificationException`.
- 생성자에서 각 버킷을 `new LinkedList<>()`로 **미리 초기화**해야 한다. 안 하면 첫 접근에서 NPE.
- `key % SIZE`는 음수 키에서 음수 인덱스가 나올 수 있다(자바 `%`는 부호 유지). 이 테스트는 음수 키를 안 쓰지만, 안전하게 하려면 `((key % SIZE) + SIZE) % SIZE`.

## 대안 / 최적화
- 버킷을 리스트 대신 개방 주소법(open addressing, 선형 탐사)으로 처리할 수도 있다.
- load factor를 보고 동적으로 resize + rehash 하면 충돌을 낮춰 평균 O(1)을 더 잘 보장(자바 표준 HashMap 방식).

## 면접 답변 (한국어 1분)
> "고정 크기 버킷 배열에 분리 연쇄로 충돌을 처리했습니다. `key % SIZE`로 버킷을 고르고, 같은 버킷
> 안에서는 `{key,value}` 쌍의 연결 리스트를 선형 탐색합니다. put은 같은 key가 있으면 덮어쓰고
> 없으면 추가, get은 찾아서 반환하거나 -1, remove는 Iterator로 순회하며 삭제합니다. 충돌이
> 적으면 평균 O(1)이고, 최악은 한 버킷에 다 몰릴 때 O(N)입니다. 자바 표준 HashMap은 load factor
> 기반 resize와 트리화로 이 최악을 완화합니다."

## Follow-up
- **load factor가 높아지면?** 충돌이 늘어 평균 탐색 길이가 길어진다. 임계치를 넘으면 배열을 키우고 rehash.
- **음수 키 처리?** `key % SIZE`가 음수가 되므로 `((key % SIZE) + SIZE) % SIZE`로 보정.
- **왜 평균 O(1)인가?** 키가 버킷에 고르게 분산된다는 가정 하에 각 버킷 길이가 상수에 가깝기 때문.
