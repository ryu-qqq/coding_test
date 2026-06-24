# 신고 결과 받기 (Programmers 92334) — 해설

> 막히기 전에 골격(`ReportResult.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
유저들이 서로 신고한다. 같은 사람을 여러 번 신고해도 1회로 카운트. k회 이상 신고당하면 정지되고, 정지된 유저를 신고한 사람들은 메일을 1통씩 받는다. 유저별 받은 메일 수를 `id_list` 순서로 반환.

## 핵심 직관 (1줄)
"피신고자 → 신고자 집합(Set)" 을 모으면 중복 신고가 자동으로 사라지고, 집합 크기가 곧 신고당한 횟수다.

## 자료구조
- `Map<String, Set<String>> countMap` : 피신고자 → 신고한 사람들의 집합 (중복 제거).
- `Map<String, Integer> mailCntMap` : 유저 → 받을 메일 수.

## 알고리즘 (2-pass)
1. **집계**: 각 report 를 `split(" ")` 으로 신고자/피신고자 분리, `countMap[피신고자].add(신고자)`.
2. **판정+카운트**: 각 피신고자에 대해 `set.size() >= k` 이면 정지. 그 신고자들 각각에게 `mailCntMap` +1.
3. **복원**: `id_list` 순서대로 `mailCntMap.getOrDefault(id, 0)` 으로 결과 배열 채움.

## 불변식
1-pass 종료 시 `countMap[u]` 는 u 를 신고한 **서로 다른** 유저들의 집합(중복 없음). 2-pass 종료 시 `mailCntMap[u]` 는 u 가 받을 메일 수.

## 복잡도
- 시간: O(N) (report N건 1회 순회 + Set 연산 평균 O(1)). report 20만 건도 선형이라 무난.
- 공간: O(유저수 + report수).

## 함정 ⚠️
- **중복 신고**: `Set` 을 쓰는 이유. List 로 모으면 같은 신고자 중복이 size 에 반영되어 k 판정이 틀어진다.
- **computeIfAbsent 패턴** (Map API 핵심): `countMap.computeIfAbsent(c, key -> new HashSet<>()).add(shooter)` 한 줄.
  - `getOrDefault(c, new HashSet<>())` 는 "없으면 새 Set 을 **반환만**" 한다. 그 Set 을 `put` 으로 다시 넣지 않으면 map 과 연결이 안 된다. `computeIfAbsent` 는 없으면 만들어서 **map 에 넣고** 그 Set 을 돌려주므로 `put` 불필요.
  - `Set.add` 는 이미 있으면 `false` 반환하고 안 넣음 → `contains` 체크 불필요.
- **merge 패턴**: "없으면 1, 있으면 +1" 은 `mailCntMap.merge(shooter, 1, Integer::sum)` 한 줄. `put(k, getOrDefault(k,0)+1)` 의 교과서적 축약.
- **결과 순서**: 반드시 `id_list` 인덱스 순서로 복원해야 한다(map 순회 순서가 아님).

## 대안 / 최적화
- 2-pass 대신, 정지자 명단을 먼저 만든 뒤 다시 report 를 훑어 "내가 신고한 사람이 정지자인가" 로 세도 된다. 다만 중복 신고를 따로 걸러야 해 Set-집계 방식이 더 깔끔하다.

## 면접 답변 (한국어 1분)
> "같은 사람을 여러 번 신고해도 1회라는 조건 때문에 '피신고자 → 신고자 Set' 맵으로 집계했습니다. 그러면 중복이 자동으로 제거되고, 집합 크기가 곧 신고당한 횟수가 됩니다. 이걸 computeIfAbsent 한 줄로 처리했습니다. getOrDefault 와 달리 computeIfAbsent 는 없을 때 새 Set 을 맵에 넣고 반환해줘서 put 을 또 부를 필요가 없거든요. 2차로 집합 크기가 k 이상인 피신고자의 신고자들에게 merge 로 메일을 1씩 더했고, 마지막에 id_list 순서대로 결과를 복원했습니다. report 가 N건이면 Set 연산이 평균 O(1)이라 전체 O(N), 20만 건도 선형이라 괜찮습니다."

## Follow-up
- **`computeIfAbsent` vs `getOrDefault`?** 전자는 부재 시 map 에 값을 삽입(side-effect)하고 반환, 후자는 삽입 없이 기본값만 반환. "없으면 컨테이너를 만들어 누적" 패턴엔 전자.
- **report 20만 건이면?** 선형이라 무난. 병목은 split. 필요하면 indexOf 로 공백 위치만 찾아 substring 해 정규식/split 오버헤드를 줄일 수 있다.
