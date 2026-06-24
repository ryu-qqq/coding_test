# 구간 겹침 (Interval Overlap) 치트시트 — 예약/일정 시스템 단골

> 트리거: 회의실/룸 예약, 일정 충돌, 시간대 겹침, 캘린더, 구간 병합
> 2026-06-16 빌드블록 라이브코딩 출제 (룸 예약 시스템)

---

## 0. 핵심 공식 ⭐ (이것만 외우면 90% 해결)

두 구간 `[s, e]` 와 `[rs, re]` 가 **겹치는 조건**:

```java
s < re && rs < e
```

말: "내 시작이 상대 끝보다 이르고, 상대 시작이 내 끝보다 이르면 겹친다."

유도: **안 겹치려면** `e <= rs`(내가 먼저 끝남) 또는 `re <= s`(내가 나중 시작).
이걸 부정하면 → `s < re && rs < e` = 겹침.

⚠️ 경계: `[9,10]` vs `[10,11]` 은 `<` 쓰면 **안 겹침**(끝=시작 허용).
`<=` 쓰면 겹침으로 침. → **명확화 질문**: "끝시각=시작시각이면 겹침인가요?"

---

## 1. 저장 구조

```java
// 룸(자원)별로 예약 구간 리스트
Map<String, List<int[]>> reservations = new HashMap<>();
//        룸ID         [start, end] 들
```
구간 표현: `int[]{s,e}` / `LocalTime` 2개 / `Reservation` 클래스 — 자유.

---

## 2. 예약 추가 (겹침 검사 후)

```java
boolean canReserve(String room, int s, int e) {
    for (int[] r : reservations.getOrDefault(room, List.of())) {
        if (s < r[1] && r[0] < e) return false;   // 겹침 → 불가
    }
    return true;
}

void reserve(String room, int s, int e) {
    if (s >= e) throw new IllegalArgumentException("시작이 종료보다 늦거나 같음");
    if (!canReserve(room, s, e)) throw new IllegalStateException("시간이 겹칩니다");
    reservations.computeIfAbsent(room, k -> new ArrayList<>()).add(new int[]{s, e});
}
```

- 단순 순회 O(n) — 룸당 예약 수가 적으면 충분
- `computeIfAbsent` 로 "룸 처음이면 리스트 생성 + 추가"

---

## 3. 시간을 어떻게 int로? (LocalTime / "HH:MM")

```java
// "09:30" → 분으로 (비교/겹침은 int가 편함)
int toMinutes(String t) {
    String[] p = t.split(":");
    return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
}
// 또는 LocalTime 그대로: s.isBefore(re) && rs.isBefore(e)
```
LocalTime 버전 겹침: `s.isBefore(re) && rs.isBefore(e)`

---

## 4. 면접 확장 답변 (가산점)

- "예약 수만 건이면?" → "시작시간 **정렬 + 이진탐색**, 또는 `TreeMap<시작, 끝>` 으로
   바로 앞/뒤 예약만 확인 → O(log n)."
- "구간들을 병합하려면?" (예: 빈 시간 찾기) → "시작시간 정렬 후 순회하며
   `prev.end >= cur.start` 면 병합" (구간 병합 = LeetCode Merge Intervals 패턴)

---

## 5. 관련 클래식 (구간 3대 패턴)

| 문제 | 핵심 |
|---|---|
| 겹침 판정 (예약 가능?) | `s < re && rs < e` |
| 구간 병합 (Merge Intervals) | 시작 정렬 → `prev.end >= cur.start` 면 합침 |
| 최대 동시 개수 (회의실 개수) | 시작/끝 분리 정렬 or 우선순위큐, +1/-1 스윕 |

---

## 6. 핵심 한 줄

> 겹침 = **`s < re && rs < e`**. 저장 = **`Map<자원, List<구간>>`**.
> 추가 전 기존 구간들과 겹침 검사. 경계(끝=시작)는 명확화 질문.
> 많아지면 정렬+이진탐색 or TreeMap.
