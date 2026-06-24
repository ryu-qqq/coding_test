# 함수형 인터페이스 & 람다 — "이 파라미터에 뭘 넣지?" 완전 정복

> 출처: 김영한 자바 고급3 §2(람다), §3(함수형 인터페이스), §6(메서드 참조)
> 트리거: `merge`, `computeIfAbsent`, `Comparator`, `stream().map(...)` 등에서
>          "파라미터에 대체 뭘 어떻게 넣어야 하지?" 가 안 와닿을 때

---

## 0. 가장 큰 오해부터 깨기

**오해**: "이 파라미터 자리에 *값*(변수)을 넣어야 한다"
**진실**: "이 파라미터 자리에 ***동작(코드 조각)***을 넣는다"

```java
mailCntMap.merge(shooter, 1, Integer::sum);
//                          └──────────┘
//          여기는 "값" 이 아니라 "어떻게 합칠지에 대한 동작" 이 들어간다
```

자바에서 보통 메서드 인자는 `int`, `String` 같은 **데이터**를 넣지.
근데 함수형 인터페이스 자리에는 **"할 일(함수)" 자체를 값처럼** 넘긴다.
이게 "함수를 일급 시민으로 다룬다" 는 말의 뜻.

---

## 1. 함수형 인터페이스 = 추상 메서드 딱 1개짜리 인터페이스

```java
@FunctionalInterface
interface BiFunction<A, B, R> {
    R apply(A a, B b);   // 추상 메서드 단 1개
}
```

- 메서드가 1개뿐이라 "이름" 을 안 적어도 컴파일러가 "아, 그 메서드 구현이구나" 안다.
- 그래서 `new BiFunction() { public Integer apply(...) {...} }` 같은
  긴 익명 클래스 대신 **람다** 로 짧게 쓸 수 있다.

```java
// 익명 클래스 (옛날 방식, 장황)
BiFunction<Integer,Integer,Integer> sum = new BiFunction<>() {
    public Integer apply(Integer a, Integer b) { return a + b; }
};

// 람다 (같은 의미, 짧음)
BiFunction<Integer,Integer,Integer> sum = (a, b) -> a + b;

// 메서드 참조 (더 짧음 — 이미 있는 메서드를 가리킴)
BiFunction<Integer,Integer,Integer> sum = Integer::sum;
```

위 셋은 **완전히 같은 것**. 짧아지는 순서일 뿐.

---

## 2. 람다 문법 해부 — `(a, b) -> a + b`

```
(a, b)      ->       a + b
─────              ───────
파라미터            몸통(반환값)
"뭐가 들어오나"      "그걸로 뭘 하나"
```

- 화살표 `->` 왼쪽: **입력 파라미터** (타입 생략 가능, 컴파일러가 추론)
- 화살표 오른쪽: **결과** (한 줄이면 `return` 생략, 그 값이 반환됨)

```java
(a, b) -> a + b          // 두 개 받아 더해서 반환
x -> x * 2               // 하나 받아 2배 (괄호 생략 가능, 1개일 때)
() -> "hello"            // 안 받고 문자열 반환
(a, b) -> {              // 여러 줄이면 중괄호 + 명시적 return
    int c = a + b;
    return c * 2;
}
```

⭐ **핵심 질문에 대한 답**:
"파라미터에 뭘 넣어?" → **그 함수형 인터페이스의 추상 메서드 시그니처를 봐라.**
그게 `R apply(A a, B b)` 면 → 너는 `(a, b) -> 결과` 를 적으면 된다.
`a`, `b` 의 타입과 개수는 그 시그니처가 정해준다. 너는 이름만 붙이면 됨.

---

## 3. 자주 쓰는 함수형 인터페이스 5종 (이것만 알면 90%)

| 인터페이스 | 추상 메서드 | 의미 | 람다 예시 |
|---|---|---|---|
| `Supplier<T>` | `T get()` | 입력 0, 출력 1 (공급) | `() -> new ArrayList<>()` |
| `Consumer<T>` | `void accept(T t)` | 입력 1, 출력 0 (소비) | `x -> System.out.println(x)` |
| `Function<T,R>` | `R apply(T t)` | 입력 1, 출력 1 (변환) | `s -> s.length()` |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | 입력 2, 출력 1 | `(a, b) -> a + b` |
| `Predicate<T>` | `boolean test(T t)` | 입력 1, 출력 boolean (판정) | `x -> x > 10` |

⭐ **읽는 법**: 제네릭 `<...>` 의 **마지막 타입이 반환**, 앞은 입력.
- `Function<String, Integer>` = String 받아 Integer 반환
- `BiFunction<Integer, Integer, Integer>` = Integer 둘 받아 Integer 반환

---

## 4. "누가 이 함수를 호출하나?" — 콜백 멘탈모델

가장 헷갈리는 부분: 람다를 넘기기만 하면 끝. **호출은 내가 안 한다.**

```java
mailCntMap.merge(shooter, 1, (old, val) -> old + val);
```

- 너는 `(old, val) -> old + val` 라는 **레시피만 건넨다.**
- 실제로 `old` 와 `val` 에 값을 채워 호출하는 건 **`merge` 내부**다.
- merge 가 "키가 이미 있네? 그럼 받은 레시피에 (기존값, 새값) 을 넣어 실행하자"
  하고 **너 대신** 호출한다.

비유: 식당에서 "스테이크를 이렇게 구워주세요" 라고 **조리법을 적어 주방에 넘김.**
     실제 굽는 건 주방(merge)이 함. 너는 굽지 않는다.

그래서 `old`, `val` 이라는 이름은 **네가 짓는 거고**, 거기 들어올 값은
merge 가 알아서 채운다. "내가 무슨 값을 넣지?" 가 아니라
"merge 가 (기존값, 새값) 을 줄 테니 나는 그걸 어떻게 합칠지만 쓴다" 가 정답.

---

## 5. 실전: Map.merge 완전 해부

```java
V merge(K key, V value, BiFunction<V, V, V> remappingFunction)
```

읽는 법:
- `key`   : 어떤 키를?
- `value` : 키가 **없을 때** 넣을 초기값
- 세 번째: 키가 **이미 있을 때** "기존값 + 새값 → 최종값" 을 계산하는 동작

동작 분기 (merge 내부가 알아서 함):
```
키가 없으면        → map.put(key, value)            // 그냥 초기값
키가 이미 있으면   → 기존값과 value 를 BiFunction 에 넣어 호출
                     → 그 반환값으로 갱신
```

그래서 "카운트 +1" 패턴:
```java
map.merge(key, 1, (oldValue, one) -> oldValue + one);
// 키 없음 → 1 저장
// 키 있음 → (기존카운트, 1) → 기존카운트 + 1
```

`(oldValue, one) -> oldValue + one` 은 "두 Integer 받아 더해 반환" =
`Integer::sum` 과 똑같음. 그래서 더 짧게:
```java
map.merge(key, 1, Integer::sum);
```

⚠️ **흔한 버그**: 첫 번째 인자(key)를 잘못 넣기.
`merge` 의 첫 인자는 "카운트를 올릴 대상 키" 다. 누구의 카운트를 올리는지
정확히 넣어야 함. (← 지금 02번에서 난 실수가 바로 이거)

---

## 6. computeIfAbsent 도 같은 원리

```java
V computeIfAbsent(K key, Function<K, V> mappingFunction)
```

- `key` : 찾을 키
- 두 번째: 키가 **없을 때** "이 키로 무슨 값을 만들지" 계산하는 동작 (입력=key, 출력=새값)

```java
map.computeIfAbsent(candidate, key -> new HashSet<>());
//                             └─────────────────────┘
//        Function<String, Set>: key 를 받지만 안 쓰고 새 HashSet 반환
```

- `key ->` 의 `key` 는 candidate 가 들어옴. 근데 여기선 안 쓰니까 이름만 형식상.
- 반환값(`new HashSet<>()`)이 맵에 저장되고, 그게 반환됨.
- 그래서 뒤에 `.add(shooter)` 를 바로 이어붙일 수 있다.

---

## 7. 메서드 참조 `::` — "이미 있는 메서드를 람다 대신"

### 7-0. `::` vs `()` — 자판기 비유 (가장 헷갈리는 지점)

```java
Comparator.naturalOrder()    // () 호출 = 버튼을 "눌러서" 결과(Comparator 객체)를 받음
Comparator::naturalOrder     // :: 참조 = "이 버튼 누르면 돼" 하고 버튼만 가리킴 (아직 실행 X)
```

- **`()` 호출** = 지금 실행해서 **결과물**을 손에 받는다.
- **`::` 참조** = 실행 안 하고 **"이거 쓰면 돼"** 하고 메서드를 가리켜 **남한테 넘긴다.**
  실제 호출은 그걸 받은 쪽(sort/merge/stream)이 나중에 함 (= 콜백).

판별 질문: **"내가 지금 결과를 받아야 하나, 남한테 일을 시키나?"**
- 결과 받음 → `메서드()`
- 남한테 시킴(콜백) → `클래스::메서드`

예:
```java
.thenComparing(Comparator.naturalOrder())  // 결과(비교기 객체)가 필요 → 호출 ()
list.sort(String::compareTo)               // sort 가 나중에 호출 → 참조 ::
```

⚠️ 안 와닿아도 정상. 명령형만 써온 사람은 다 여기서 막힌다.
패턴 몇 개 외워서 쓰다 보면 어느 순간 갑자기 와닿음. 지금은
"버튼 누르기() vs 버튼 가리키기::" 만 들고 가면 됨.

### 7-1. 메서드 참조 종류

람다가 "기존 메서드 하나를 그대로 호출" 하는 거면 `::` 로 줄임.

| 람다 | 메서드 참조 | 종류 |
|---|---|---|
| `(a, b) -> Integer.sum(a, b)` | `Integer::sum` | 정적 메서드 |
| `s -> s.length()` | `String::length` | 인스턴스 메서드 (첫 인자가 수신자) |
| `x -> System.out.println(x)` | `System.out::println` | 특정 객체의 메서드 |
| `() -> new ArrayList<>()` | `ArrayList::new` | 생성자 |

읽는 법: `클래스::메서드` 를 보면 "아, 저 메서드를 람다 자리에 끼우는구나" 로 해석.

---

## 8. 막힐 때 3단계 체크리스트

어떤 API의 함수형 파라미터 자리에서 막히면:

1. **그 자리의 타입이 뭐지?** (IDE 에서 메서드 시그니처 hover)
   예: `BiFunction<Integer,Integer,Integer>`
2. **그 인터페이스의 추상 메서드 시그니처는?**
   예: `Integer apply(Integer a, Integer b)` → 입력 2개, 출력 1개
3. **그 모양대로 람다를 쓴다**:
   `(a, b) -> 결과` — a, b 이름은 내가 짓고, 결과만 채운다.

"무슨 값을 넣지?" 가 아니라 **"무슨 동작을 넣지?"** 로 질문을 바꿔라.

---

## 9. 라이브코딩 자주 만나는 함수형 자리

```java
// 정렬 (Comparator 도 함수형 인터페이스! int compare(T,T))
list.sort((a, b) -> a - b);
list.sort(Comparator.comparingInt(Person::getAge));

// 스트림
list.stream()
    .filter(x -> x > 10)          // Predicate
    .map(s -> s.toUpperCase())    // Function
    .forEach(System.out::println);// Consumer

// Map
map.merge(key, 1, Integer::sum);                 // BiFunction
map.computeIfAbsent(key, k -> new ArrayList<>());// Function
map.forEach((k, v) -> ...);                      // BiConsumer
map.getOrDefault(key, 0);                        // (이건 함수형 아님, 그냥 값)

// Optional
opt.orElseGet(() -> defaultValue);  // Supplier
opt.map(x -> x.trim());             // Function
```

---

## 10. 핵심 한 줄 요약

> 함수형 파라미터 = **"값"이 아니라 "동작"을 넘기는 자리.**
> 뭘 넣을지 모르겠으면 → **그 자리의 인터페이스 추상 메서드 시그니처를 봐라.**
> 입력 개수/타입은 시그니처가 정해주고, 너는 **"그걸로 뭘 할지"** 만 쓴다.
> 호출은 내가 아니라 그 API(merge/stream/sort)가 대신 한다 (콜백).
