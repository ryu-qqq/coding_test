# Java String 치트시트 — 라이브코딩 즉답용

> 출처: 김영한 자바 중급1 §4 (11강 2시간) 압축판
> 목적: AB180 트라우마 해소 + 카카오 String 기출 정복
> 사용법: 10~15분 훑고 → 카카오 72410 풀기 → 막힌 부분만 재방문

---

## 1. 핵심 메서드 — 라이브코딩 자주 쓰는 순서

### 길이/접근
```java
str.length()           // 길이
str.charAt(i)          // i번째 문자 (char)
str.codePointAt(i)     // i번째 문자의 유니코드 정수
str.isEmpty()          // 길이 0
str.isBlank()          // 길이 0 OR 공백만 (Java 11+)
```

### 검색
```java
str.indexOf("x")          // 첫 위치, 없으면 -1
str.indexOf("x", 3)       // 3부터 검색
str.lastIndexOf("x")      // 마지막 위치
str.contains("x")         // 포함 여부 (boolean)
str.startsWith("pre")     // 접두사
str.endsWith("post")      // 접미사
```

### 자르기/추출 (라이브코딩 핵심)
```java
str.substring(3)          // 3부터 끝까지
str.substring(3, 7)       // [3, 7) — end는 exclusive!
str.split(",")            // 구분자로 자르기 → String[]
str.split(",", -1)        // 빈 토큰 보존 ("a,,b" → ["a","","b"])
str.split(",", 2)         // 최대 2개로 자르기
```

⚠️ **함정**: `split`은 정규식 인자라 `.`, `|` 같은 메타문자는 이스케이프 필요.
```java
"a.b.c".split(".")        // ❌ 빈 배열
"a.b.c".split("\\.")      // ✅ ["a","b","c"]
```

### 공백/대소문자
```java
str.trim()                // 앞뒤 공백 제거 (ASCII)
str.strip()               // 앞뒤 공백 제거 (유니코드) — Java 11+
str.stripLeading()        // 앞 공백만
str.stripTrailing()       // 뒤 공백만
str.toLowerCase()
str.toUpperCase()
```

### 치환 (AB180 출제 핵심)
```java
str.replace("a", "b")            // 모든 "a" → "b" — 정규식 X, 단순 치환
str.replace('a', 'b')            // char 단위
str.replaceAll(regex, repl)      // 정규식 — 모든 매치 치환
str.replaceFirst(regex, repl)    // 정규식 — 첫 매치만 치환
str.matches(regex)               // 전체가 정규식과 매치하는지 (boolean)
```

⚠️ **결정적 함정**: `replace` vs `replaceAll`
```java
"a.b.c".replace(".", "_")        // "a_b_c" — 단순 치환
"a.b.c".replaceAll(".", "_")     // "_____" — 정규식 . = 모든 문자!
"a.b.c".replaceAll("\\.", "_")   // "a_b_c" — 이스케이프 필요
```

### 결합
```java
String.join(",", list)                 // List<String> → "a,b,c"
String.join(",", "a", "b", "c")        // 가변인자도 됨
String.format("%.2f원", 1234.5)        // 포맷팅
String.format("%-10s|%5d", name, n)    // 좌측정렬 10칸, 우측정렬 5칸
```

### 비교
```java
str.equals(other)                 // 내용 비교 ✅
str == other                      // 참조 비교 ❌ 절대 쓰지 마
str.equalsIgnoreCase(other)       // 대소문자 무시
str.compareTo(other)              // 사전순 비교 (음수/0/양수)
str.compareToIgnoreCase(other)
```

---

## 2. 정규식 — 라이브코딩 필수 패턴

### Java에서 정규식 쓸 때 주의
- 백슬래시는 **무조건 두 번** `\\d`, `\\s`, `\\w`, `\\.`
- `[]` 안에서는 대부분의 메타문자 의미 없어짐

### 자주 쓰는 패턴
```java
"\\d+"               // 숫자 1개 이상
"\\D+"               // 숫자 아닌 것 1개 이상
"\\s+"               // 공백 1개 이상 (스페이스/탭/개행)
"\\w+"               // [a-zA-Z0-9_] 1개 이상
"[a-z]+"             // 영소문자 1개 이상
"[a-z0-9_-]+"        // 영소문자+숫자+_- 1개 이상
"[^a-z0-9]"          // 영소문자+숫자가 아닌 것
"^pre"               // 시작
"end$"               // 끝
".{2,5}"             // 임의 문자 2~5개
"a|b|c"              // OR
"(\\d+)-(\\d+)"      // 그룹 캡처
```

### 자주 쓰는 활용
```java
// 영소문자, 숫자, _, - 만 남기고 다 제거
str.replaceAll("[^a-z0-9_-]", "");

// 연속된 공백을 한 칸으로
str.replaceAll("\\s+", " ");

// 숫자만 추출
str.replaceAll("[^0-9]", "");

// 전화번호 형식 검사
str.matches("\\d{3}-\\d{4}-\\d{4}");

// 마침표 두 번 이상 → 한 번
str.replaceAll("\\.{2,}", ".");
```

### Pattern + Matcher (반복 사용 시 성능↑)
```java
Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher(str);
while (m.find()) {
    System.out.println(m.group());
}
```

---

## 3. Character 클래스 — char 한 글자 다룰 때

```java
Character.isLetter(c)            // 알파벳 (한글 포함)
Character.isDigit(c)             // 0-9
Character.isLetterOrDigit(c)     // 영숫자
Character.isWhitespace(c)        // 공백
Character.isUpperCase(c)
Character.isLowerCase(c)
Character.toLowerCase(c)
Character.toUpperCase(c)
Character.getNumericValue('5')   // '5' → 5 (int)
```

⚠️ **함정**: `'5' - '0'` 으로 빠르게 정수 변환 가능
```java
int n = '5' - '0';   // 5
```

---

## 4. StringBuilder — 가변 String

### 언제 쓰나
- 반복문으로 String 누적 만들 때
- `+=` 100번 이상이면 무조건 StringBuilder
- 한두 번 더하기는 `+` 그대로 OK (컴파일러 최적화)

```java
StringBuilder sb = new StringBuilder();
sb.append("hello");
sb.append(' ');
sb.append(42);
sb.insert(0, ">>> ");
sb.delete(0, 3);
sb.deleteCharAt(5);
sb.reverse();
sb.toString();             // 마지막에 String 변환
```

### 흔한 패턴
```java
StringBuilder sb = new StringBuilder();
for (String word : words) {
    sb.append(word).append(",");
}
// 마지막 "," 제거
if (sb.length() > 0) sb.setLength(sb.length() - 1);

// 또는 처음부터 String.join 사용
String.join(",", words);   // 훨씬 깔끔
```

---

## 5. 문자열 ↔ 숫자 변환

```java
Integer.parseInt("42")              // String → int
Integer.parseInt("FF", 16)          // 16진수
Long.parseLong("123456789")
Double.parseDouble("3.14")

String.valueOf(42)                  // int → String (null safe)
Integer.toString(42)                // int → String
Integer.toString(255, 16)           // "ff"
"" + 42                             // ❌ 비추 (가독성↓)
```

⚠️ **함정**: 잘못된 형식이면 `NumberFormatException` (RuntimeException) 던짐.
```java
try {
    int n = Integer.parseInt(input);
} catch (NumberFormatException e) {
    // 사용자 입력 검증 시 필수
}
```

---

## 6. String의 본질 — 불변(immutable)

```java
String s = "hello";
s.toUpperCase();          // 결과 안 받으면 의미 없음
s = s.toUpperCase();      // 이렇게 받아야 함
```

→ String 메서드는 **새 String을 반환**한다. 원본은 안 바뀜.

```java
// String Pool (리터럴 캐싱)
String a = "abc";
String b = "abc";
a == b;                   // true (같은 풀 객체)

String c = new String("abc");
a == c;                   // false (new는 새 객체)
a.equals(c);              // true (내용 같음)
```

**그래서**: 비교는 **항상 `equals`**. `==`는 절대 X.

---

## 7. 라이브코딩 흔한 패턴 모음

### 7-1. 카카오 신규 아이디 추천 패턴 (AB180 트라우마)
```java
String solution(String new_id) {
    String s = new_id.toLowerCase();
    s = s.replaceAll("[^a-z0-9_.\\-]", "");
    s = s.replaceAll("\\.{2,}", ".");
    s = s.replaceAll("^\\.|\\.$", "");
    if (s.isEmpty()) s = "a";
    if (s.length() >= 16) {
        s = s.substring(0, 15);
        s = s.replaceAll("\\.$", "");
    }
    while (s.length() <= 2) s += s.charAt(s.length() - 1);
    return s;
}
```
**여기서 배울 것**:
- `replaceAll` 정규식 활용
- 빈 문자열 체크
- 길이 제한 + 후처리
- 짧으면 반복 채우기

### 7-2. 단어별 첫 글자 대문자
```java
String s = "hello world java";
String[] words = s.split(" ");
StringBuilder sb = new StringBuilder();
for (String w : words) {
    if (!w.isEmpty()) {
        sb.append(Character.toUpperCase(w.charAt(0)));
        sb.append(w.substring(1).toLowerCase());
    }
    sb.append(' ');
}
String result = sb.toString().trim();
```

### 7-3. 회문(팰린드롬) 체크
```java
boolean isPalindrome(String s) {
    String clean = s.toLowerCase().replaceAll("[^a-z0-9]", "");
    int l = 0, r = clean.length() - 1;
    while (l < r) {
        if (clean.charAt(l++) != clean.charAt(r--)) return false;
    }
    return true;
}
```

### 7-4. 문자열 압축 ("aaabbc" → "a3b2c1")
```java
String compress(String s) {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (i < s.length()) {
        char c = s.charAt(i);
        int count = 0;
        while (i < s.length() && s.charAt(i) == c) {
            count++;
            i++;
        }
        sb.append(c).append(count);
    }
    return sb.toString();
}
```

### 7-5. 토큰별 처리 (CSV 파싱)
```java
String line = "name,age,city";
String[] tokens = line.split(",", -1);  // -1로 빈 토큰 보존
for (int i = 0; i < tokens.length; i++) {
    tokens[i] = tokens[i].strip();       // 공백 제거
}
```

---

## 8. 우테코 컨벤션으로 본 String 안티패턴

❌ **나쁜 코드**
```java
public String process(String s) {
    String result = "";
    if (s != null) {
        if (s.length() > 0) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isLetter(c)) {
                    if (Character.isLowerCase(c)) {
                        result += Character.toUpperCase(c);
                    } else {
                        result += c;
                    }
                }
            }
        }
    }
    return result;
}
```
- 길이 길고 (15줄), depth 4단, else 사용, `+=` 누적, null 체크 안에 또 분기

✅ **우테코 스타일**
```java
public String toUpperLettersOnly(String s) {
    if (s == null || s.isEmpty()) return "";
    return s.chars()
            .filter(Character::isLetter)
            .map(Character::toUpperCase)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
}
```
- 한 가지 일만, depth 1, stream 활용

또는 더 단순하게:
```java
public String toUpperLettersOnly(String s) {
    if (s == null || s.isEmpty()) return "";
    return s.replaceAll("[^a-zA-Z]", "").toUpperCase();
}
```
**핵심 교훈**: char 루프 돌리기 전에 **정규식으로 해결 가능한지** 먼저 본다.

---

## 9. 즉답 체크리스트 (면접 직전 점검)

- [ ] `split` vs `replace` vs `replaceAll` 차이 즉답
- [ ] `split(",", -1)` 의미 즉답 (빈 토큰 보존)
- [ ] String이 불변이라는 것 즉답
- [ ] `==` vs `equals` 즉답
- [ ] 자주 쓰는 정규식 5개 외워두기 (`\\d+`, `\\s+`, `[a-z]+`, `[^...]`, `.{n,m}`)
- [ ] StringBuilder 언제 쓸지 즉답
- [ ] `parseInt` 실패 시 어떤 예외? (`NumberFormatException`)
- [ ] `String.format("%.2f", n)` 같은 포맷 즉답

---

## 10. 다음 단계

1. 이 치트시트 한 번 훑기 (10~15분)
2. **카카오 신규 아이디 추천 (72410)** 풀기 (목표: 25분 안에)
   - URL: https://school.programmers.co.kr/learn/courses/30/lessons/72410
   - 풀이는 `practice2/` 또는 새 폴더에
3. 풀고 나면 `/code-grade`로 채점 받기
4. 막힌 부분만 이 치트시트 재방문
5. 다음 문제: **신고 결과 받기 (92334)** — Map 워밍업

---

## 보너스: 카카오 String 기출 자주 쓰는 조합

| 출제 패턴 | 핵심 메서드 |
|---|---|
| 입력 정제 | `toLowerCase` + `replaceAll([^...])` |
| 형식 검증 | `matches(regex)` |
| 자르고 처리 | `split` + 반복 + `String.join` |
| 단어 카운트 | `split(" ")` + Map.merge |
| 패턴 찾기 | `Pattern.compile` + `Matcher.find` |
| 압축/해제 | StringBuilder + 카운터 |
| 회문 | 양쪽 포인터 (`charAt`) |
| 부분 문자열 비교 | `substring` + `equals` 또는 `startsWith` |
