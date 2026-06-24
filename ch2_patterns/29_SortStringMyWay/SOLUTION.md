# 문자열 내 마음대로 정렬하기 (Programmers 12915) — 해설

> 막히기 전에 골격(`SortStringMyWay.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
문자열 배열을 각 문자열의 n번째 글자 기준 오름차순 정렬한다. n번째 글자가 같은 문자열끼리는 사전순으로 정렬한다.

## 핵심 직관 (1줄)
"1차 키 = n번째 글자, 동점이면 2차 키 = 문자열 전체 사전순" 이라는 2단계 비교 Comparator 하나면 끝.

## 자료구조
- 별도 자료구조 없음. `Arrays.sort(arr, comparator)` 인플레이스 정렬.

## 알고리즘
`Comparator.comparingInt((String s) -> s.charAt(n)).thenComparing(Comparator.naturalOrder())` 로 비교자를 만들어 `Arrays.sort` 에 넘긴다.

## 불변식
정렬 후 임의의 인접 쌍 a, b 에 대해 `a.charAt(n) < b.charAt(n)`, 또는 같으면 `a.compareTo(b) <= 0`.

## 복잡도
- 시간: O(M log M · 비교비용) (M = 배열 길이 ≤ 50, 비교는 charAt O(1) + 동점 시 사전순 O(문자열길이)).
- 공간: O(1)~O(M) (정렬 구현 의존).

## 함정 ⚠️ (Comparator / 함수형 인터페이스 집중)
- **"동작을 넘긴다"**: `comparingInt` 의 인자는 "값"이 아니라 **키 추출 함수**(`ToIntFunction`)다. `s -> s.charAt(n)` 처럼 "각 원소에서 비교 키를 어떻게 뽑을지"라는 **동작**을 넘긴다. 여기에 특정 문자열을 넣으려 하면 안 된다.
- **람다 타입 명시**: `comparingInt((String s) -> ...)` 처럼 파라미터 타입 `String` 을 명시해야 컴파일러가 추론한다. `s -> s.charAt(n)` 만 쓰면 `s` 타입을 몰라 `charAt` 호출이 막힐 수 있다.
- **2차 키 누락 = 불안정한 결과**: 1차 키만 비교하면 n번째 글자가 같은 문자열들의 상대 순서가 입력 순서대로 남는다(stable sort). 문제는 "사전순"을 요구하므로 `thenComparing(naturalOrder())` 가 반드시 필요. (입력이 이미 정렬돼 있지 않으면 1차만으로는 오답)
- **charAt 음수 차이 vs comparingInt**: 직접 `(a,b) -> a.charAt(n) - b.charAt(n)` 도 되지만(영소문자라 오버플로 안전), 2차 키 체이닝이 깔끔한 `comparing` 방식이 권장.

## 대안 / 최적화
- 비교자 직접 구현: `Arrays.sort(strings, (a, b) -> a.charAt(n) != b.charAt(n) ? a.charAt(n) - b.charAt(n) : a.compareTo(b));`. 동작은 같지만 `thenComparing` 체이닝이 의도가 더 명확.

## 면접 답변 (한국어 1분)
> "정렬 기준이 두 단계라 Comparator 체이닝으로 풀었습니다. 1차 키는 n번째 글자라 comparingInt 에 'n번째 글자를 뽑는 함수' s -> s.charAt(n) 를 넘겼습니다. 여기서 넘기는 건 값이 아니라 키 추출 동작이라는 게 포인트입니다. n번째 글자가 같을 때만 사전순으로 정렬해야 하니 thenComparing(naturalOrder()) 로 2차 키를 붙였습니다. 2차 키를 빠뜨리면 동점인 문자열들이 입력 순서로 남아 오답이 됩니다. Arrays.sort 에 이 비교자를 넘기면 O(M log M) 으로 끝납니다."

## Follow-up
- **`comparing` 과 `comparingInt` 차이?** `comparingInt` 은 키가 int 라 박싱을 피한다. char 는 int 로 승격되므로 `comparingInt` 가 적합.
- **`thenComparing` 없이 stable sort 만으로 되나?** 입력이 이미 사전순이라는 보장이 없으면 안 된다. 명시적으로 2차 키를 줘야 한다.
- **`naturalOrder()` 가 하는 일?** `String` 의 `compareTo`(사전순)를 그대로 쓰는 Comparator 를 반환한다.
