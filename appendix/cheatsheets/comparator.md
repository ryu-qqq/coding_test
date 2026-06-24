# Comparator 치트시트 — 정렬 라이브코딩 즉답용

> 출처: 김영한 자바 중급2 §11(정렬, Comparable/Comparator)
> 트리거: "정렬 어떻게 하지", "Comparator 사용법 까먹음", "n번째 기준 정렬"
> 라이브코딩 최빈출. 이거 즉답 안 되면 시간 다 까먹는다.

---

## 0. Comparator 의 본질 (함수형 인터페이스)

```java
interface Comparator<T> {
    int compare(T a, T b);   // 추상 메서드 1개
}
```

**compare(a, b) 반환값 규칙:**
- **음수** → a 가 앞으로 (a < b)
- **0**   → 동등 (순서 유지, stable)
- **양수** → b 가 앞으로 (a > b)

⭐ 외우는 법: **"a 를 앞에 두고 싶으면 음수를 뱉어라."**
오름차순이면 `a - b` (a 가 작을 때 음수 → a 앞으로).

---

## 1. 만드는 법 — 두 갈래

### 방법 A — 직접 람다 (compare 를 손으로)
```java
Arrays.sort(arr, (a, b) -> a - b);              // int 오름차순
Arrays.sort(arr, (a, b) -> b - a);              // int 내림차순
Arrays.sort(words, (a, b) -> a.compareTo(b));   // 문자열 사전순
Arrays.sort(words, (a, b) -> a.length() - b.length()); // 길이순
```

⚠️ `a - b` 함정: int 오버플로 위험 (a, b 가 매우 클 때).
안전하게는 `Integer.compare(a, b)` 사용.
```java
Arrays.sort(arr, (a, b) -> Integer.compare(a, b));  // 오버플로 안전
```

### 방법 B — comparing 헬퍼 (keyExtractor)
"비교를 직접 짜지 말고, 정렬 기준 키만 뽑아줘. 비교는 내가 할게."

```java
Arrays.sort(words, Comparator.comparingInt(w -> w.length()));
//                                          └────────────┘
//        keyExtractor (Function): 원소 받아서 "비교에 쓸 키" 반환
```

- `comparing(keyExtractor)`     : 키가 객체(Comparable)일 때
- `comparingInt(keyExtractor)`  : 키가 int (박싱 회피)
- `comparingLong / comparingDouble` : 각 타입용

```java
Comparator.comparing(Person::getName)       // 이름(String)으로
Comparator.comparingInt(Person::getAge)      // 나이(int)로
Comparator.comparing(w -> w.charAt(0))       // 첫 글자로
```

---

## 2. 적용 — 어디에 넣나

```java
// 배열
Arrays.sort(arr, comparator);            // in-place

// 리스트
list.sort(comparator);                   // in-place (List.sort)
Collections.sort(list, comparator);      // 같은 것

// 스트림
list.stream().sorted(comparator).toList();

// PriorityQueue (힙 정렬 기준)
new PriorityQueue<>(comparator);

// TreeMap / TreeSet (키 정렬 기준)
new TreeMap<>(comparator);
```

---

## 3. 다중 키 — "1순위, 같으면 2순위"

```java
Arrays.sort(people,
    Comparator.comparingInt(Person::getAge)       // 1순위: 나이 오름차순
              .thenComparing(Person::getName));     // 동점이면: 이름 사전순
```

- `.thenComparing(...)` : 앞 비교가 0(동점)일 때만 다음 키로
- `.thenComparingInt(...)` : 다음 키가 int 일 때

---

## 4. 방향 뒤집기 — reversed / reverseOrder

```java
Comparator.comparingInt(Person::getAge).reversed()   // 나이 내림차순
Comparator.<String>reverseOrder()                    // 사전 역순
Comparator.naturalOrder()                            // 사전/자연 순서

// 일부만 뒤집기: 나이 내림차순, 이름 오름차순
Comparator.comparingInt(Person::getAge).reversed()
          .thenComparing(Person::getName)
```

⚠️ `reversed()` 위치 주의: 바로 앞 키만 뒤집힌다. 전체 체인 뒤집는 거 아님.

---

## 5. naturalOrder / 자기 자신을 키로

원소 자체가 Comparable(String, Integer 등)이면:
```java
Comparator.naturalOrder()       // 원소 자체의 자연 순서 (사전순 등)
Comparator.reverseOrder()       // 그 역순
```

다중 키에서 "동점이면 전체 사전순" 자주 씀:
```java
Comparator.comparing(s -> s.charAt(n))   // 1순위: n번째 글자
          .thenComparing(Comparator.naturalOrder())  // 동점: 문자열 전체
// thenComparing(s -> s) 도 되지만 naturalOrder 가 의도 명확
```

---

## 6. 자주 만나는 정렬 레시피

```java
// int 배열 내림차순 (primitive 는 Comparator 못 씀 → Integer[] 로 박싱하거나 직접)
Integer[] boxed = ...;
Arrays.sort(boxed, Collections.reverseOrder());

// 2차원 배열 [a, b] 를 a 오름차순, a 같으면 b 내림차순
Arrays.sort(arr, (x, y) -> x[0] != y[0] ? x[0] - y[0] : y[1] - x[1]);

// Map.Entry 를 값(value) 내림차순
list.sort(Map.Entry.<String,Integer>comparingByValue().reversed());

// 문자열 길이 오름차순, 길이 같으면 사전순 (카카오 단골)
Arrays.sort(words,
    Comparator.comparingInt(String::length)
              .thenComparing(Comparator.naturalOrder()));

// 객체를 여러 필드로
people.sort(
    Comparator.comparing(Person::getCity)
              .thenComparingInt(Person::getAge)
              .thenComparing(Person::getName));
```

---

## 7. Comparable vs Comparator (헷갈림 정리)

| | Comparable | Comparator |
|---|---|---|
| 위치 | 클래스 **자신**이 구현 | **외부**에서 별도로 만듦 |
| 메서드 | `int compareTo(T o)` | `int compare(T a, T b)` |
| 의미 | "내 기본 정렬 순서" | "이번엔 이 기준으로" |
| 예 | String, Integer 가 이미 구현 | 그때그때 람다로 |

```java
// Comparable: 클래스에 박아둔 기본 순서
class Person implements Comparable<Person> {
    public int compareTo(Person o) { return this.age - o.age; }
}
Arrays.sort(people);   // Comparable 기본 순서로

// Comparator: 외부에서 다른 기준
Arrays.sort(people, Comparator.comparing(Person::getName));
```

---

## 7.5 ⚠️ 타입 추론 함정 (라이브코딩 실전 자주 터짐)

### 함정 A — 체이닝하면 람다 파라미터가 Object 로 추론
```java
// ❌ 컴파일 에러: "variable s of type Object", charAt 못 찾음
Arrays.sort(strings,
    Comparator.comparingInt(s -> s.charAt(n))
              .thenComparing(...));
```
원인: `comparingInt(...).thenComparing(...)` 체이닝 시 자바가
`s` 의 타입을 String 으로 못 흘려보내 Object 로 깔아버림.

**에러 메시지 읽는 법**: `location: variable s of type Object` 이 줄이 핵심.
"s 가 String 이 아니라 Object 로 추론됐다" 는 뜻. charAt 이 문제가 아님.

**해결**: 람다에 타입을 직접 박는다.
```java
// ✅ 방법 A: 람다 파라미터 타입 명시
Comparator.comparingInt((String s) -> s.charAt(n))

// ✅ 방법 B: 타입 증인(type witness)
Comparator.<String>comparingInt(s -> s.charAt(n))
```

### 함정 B — naturalOrder 는 호출해야 함 (`::` 아님)
```java
// ❌ Comparator::naturalOrder 는 "메서드 참조" (아직 호출 안 됨)
.thenComparing(Comparator::naturalOrder)

// ✅ Comparator.naturalOrder() 는 "호출해서 나온 Comparator 객체"
.thenComparing(Comparator.naturalOrder())
```
naturalOrder / reverseOrder 는 **인자 0개로 Comparator 를 만들어 반환하는 팩토리**.
thenComparing 은 Comparator 객체를 원하므로 **괄호로 호출**해서 넘긴다.

구분:
- `Class::method`  = 메서드 자체를 가리킴 (람다 대용, 나중에 호출됨)
- `Class.method()` = 지금 호출해서 결과값을 얻음

## 8. 막힐 때 3단계

1. **무엇을 정렬? 어떤 순서?** 말로 먼저: "나이 오름차순, 같으면 이름순"
2. **키가 하나면 comparing, 직접 비교가 편하면 람다.**
   - "필드 하나로 정렬" → `comparing(X::getField)`
   - "두 원소를 복잡하게 비교" → `(a, b) -> ...`
3. **다중 키면 thenComparing 으로 잇기. 방향은 reversed.**

---

## 9. 핵심 한 줄

> compare(a,b) 가 **음수면 a 가 앞**. 오름차순은 `a - b`.
> 필드 하나로 정렬이면 `comparing(키뽑는함수)`, 동점 처리는 `thenComparing`,
> 뒤집기는 `reversed`. 적용은 `Arrays.sort(arr, 비교기)`.
