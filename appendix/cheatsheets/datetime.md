# 날짜/시간 치트시트 — 코테 라이브코딩 즉답용

> 출처: 김영한 자바 중급1 §7 (날짜와 시간)
> 트리거: 시간 차이 계산, 입출차/세션/예약 시뮬레이션, "HH:MM" 파싱
> 핵심: LocalTime / LocalDate / LocalDateTime + Duration / Period

---

## 0. 5개 핵심 타입 — 뭘 쓸지부터

| 타입 | 담는 것 | 예 |
|---|---|---|
| `LocalTime` | 시:분:초 (날짜 X) | 09:30, 23:59 |
| `LocalDate` | 년-월-일 (시간 X) | 2026-06-14 |
| `LocalDateTime` | 날짜+시간 | 2026-06-14T09:30 |
| `Duration` | **시간 기반 간격** (시·분·초) | 90분, 2시간 |
| `Period` | **날짜 기반 간격** (년·월·일) | 3일, 2개월 |

⭐ 고르는 법:
- "몇 시 몇 분"만 → `LocalTime`
- "며칠"만 → `LocalDate`
- 둘 다 → `LocalDateTime`
- **두 시각 차이를 분/초로** → `Duration`
- **두 날짜 차이를 일/월로** → `Period`

---

## 1. 생성

```java
// 직접 지정
LocalTime.of(9, 30)            // 09:30
LocalTime.of(9, 30, 15)        // 09:30:15
LocalDate.of(2026, 6, 14)
LocalDateTime.of(2026, 6, 14, 9, 30)

// 현재 (코테에선 거의 안 씀 — 결과 비결정적)
LocalTime.now()  LocalDate.now()  LocalDateTime.now()

// 상수
LocalTime.MIN    // 00:00
LocalTime.MAX    // 23:59:59.999999999
LocalTime.MIDNIGHT  // 00:00
LocalTime.NOON      // 12:00
```

## 2. 파싱 (문자열 → 객체) ⭐ 코테 단골

```java
LocalTime.parse("09:30")              // ISO 형식 "HH:mm" 바로 됨
LocalTime.parse("09:30:15")           // "HH:mm:ss"
LocalDate.parse("2026-06-14")         // "yyyy-MM-dd"
LocalDateTime.parse("2026-06-14T09:30")  // 가운데 T 필수

// 비표준 형식이면 DateTimeFormatter
DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
LocalDateTime.parse("2026/06/14 09:30", f);
```

⚠️ `"9:30"`(앞 0 없음)은 `LocalTime.parse` 가 거부할 수 있음 → formatter `"H:mm"` 사용.

## 3. 두 시점의 차이 ⭐⭐⭐ (제일 자주 막히는 곳)

```java
// 시간 차이 → Duration
Duration d = Duration.between(start, end);   // start, end 는 LocalTime/LocalDateTime
d.toMinutes()    // 총 분 (long!)
d.toHours()      // 총 시간
d.toSeconds()    // 총 초
d.toDays()       // 총 일
d.getSeconds()   // 초 단위 총합 (long)

// 예: 09:30 ~ 11:00
Duration.between(LocalTime.of(9,30), LocalTime.of(11,0)).toMinutes();  // 90

// 날짜 차이 → Period (년/월/일 단위) 또는 ChronoUnit (총 일수)
Period p = Period.between(date1, date2);
p.getDays(); p.getMonths(); p.getYears();   // "2년 3개월 5일" 식 분해값

// 총 며칠인지 (그냥 일수 차이) → ChronoUnit
import java.time.temporal.ChronoUnit;
ChronoUnit.DAYS.between(date1, date2);     // 총 일수 (long)
ChronoUnit.MINUTES.between(t1, t2);        // 총 분 (Duration.toMinutes 대안)
```

⚠️ **함정 1**: `toMinutes()`, `between()` 결과는 **`long`**. `int` 변수/반환에 넣으려면 `(int)` 캐스팅.
⚠️ **함정 2**: `Duration.between(A, B)` 에서 A가 B보다 **나중이면 음수**가 나온다. 순서 주의.
⚠️ **함정 3**: `Period.getDays()` 는 "월/년을 뺀 나머지 일"이지 총 일수가 아님. 총 일수는 `ChronoUnit.DAYS.between`.

## 4. 더하기 / 빼기 (시각 ± 기간 = 시각)

```java
LocalTime.of(9, 30).plusMinutes(90)    // 11:00
time.plusHours(2)  time.minusMinutes(15)
date.plusDays(7)   date.minusMonths(1)
dateTime.plusWeeks(2)

// minus 는 "시각에서 기간을 뺌" — LocalTime 끼리 빼는 게 아님!
// 두 시각 차이는 Duration.between 을 써라 (3번 참고)
```

⭐ 불변(immutable): 결과를 **반드시 다시 받아야** 함. `time.plusMinutes(90)` 만 하면 원본 안 바뀜.

## 5. 비교

```java
t1.isBefore(t2)    // t1 < t2
t1.isAfter(t2)     // t1 > t2
t1.isEqual(t2)     // (LocalDateTime) 동일 시점
t1.equals(t2)      // 값 동일
t1.compareTo(t2)   // 음수/0/양수 → 정렬용

// 정렬
list.sort(Comparator.naturalOrder());          // LocalDateTime 은 Comparable
list.sort(Comparator.comparing(Event::getTime));
```

## 6. 조회 (필드 꺼내기)

```java
time.getHour()  time.getMinute()  time.getSecond()
date.getYear()  date.getMonthValue()  date.getDayOfMonth()
date.getDayOfWeek()      // DayOfWeek.MONDAY ... (enum)
date.getDayOfWeek().getValue()   // 월=1 ... 일=7
date.getDayOfYear()
dateTime.toLocalDate()   dateTime.toLocalTime()   // 분해
```

코테 활용: 요일 판정
```java
DayOfWeek dow = date.getDayOfWeek();
boolean weekend = (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY);
```

## 7. 포맷팅 (객체 → 문자열)

```java
DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
dateTime.format(f);                  // "2026-06-14 09:30"
time.format(DateTimeFormatter.ofPattern("HH:mm"));   // "09:30"
```

패턴 기호: `yyyy`년 `MM`월 `dd`일 `HH`시(24) `mm`분 `ss`초 `E`요일

## 8. 코테에서 자주 나오는 패턴

```java
// (1) "HH:MM" 입차/출차 → 분 차이
int minutes = (int) Duration.between(
    LocalTime.parse(inTime), LocalTime.parse(outTime)).toMinutes();

// (2) 자정 넘김 처리 (출차가 다음날) — LocalTime만으론 부족
//     23:00 입차, 01:00 출차 → Duration이 음수(-22h). 이럴 땐 LocalDateTime 쓰거나
//     음수면 +24시간(1440분) 보정
long m = Duration.between(in, out).toMinutes();
if (m < 0) m += 24 * 60;

// (3) N분 단위 올림 (주차요금 흔함): 10분 단위 올림
long units = (minutes + 9) / 10;   // 정수 올림 나눗셈

// (4) 시간순 정렬
events.sort(Comparator.comparing(e -> e.time));

// (5) 영업시간 내 판정
boolean open = !t.isBefore(LocalTime.of(9,0)) && !t.isAfter(LocalTime.of(18,0));
```

## 9. Duration vs Period vs ChronoUnit (헷갈림 정리)

| 쓸 곳 | 도구 |
|---|---|
| 두 시각 차이를 분/초/시간으로 | `Duration.between(...).toMinutes()` |
| 두 날짜 차이를 "총 일수"로 | `ChronoUnit.DAYS.between(...)` |
| 두 날짜 차이를 "Y년 M월 D일"로 분해 | `Period.between(...)` |
| 시각에 기간 더하기/빼기 | `plusMinutes` / `minusDays` 등 |

## 10. 핵심 한 줄

> 두 시각 차이 = **`Duration.between(시작, 끝).toMinutes()`** (결과 long → int 캐스팅).
> "HH:MM" 파싱 = **`LocalTime.parse`**. 시각±기간은 `plus/minus`, 시각끼리 빼는 건 Duration.
> 날짜 총 일수는 `ChronoUnit.DAYS.between`, 분해는 `Period`.
