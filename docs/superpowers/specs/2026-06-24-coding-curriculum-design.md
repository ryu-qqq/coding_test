# 코딩테스트 학습 커리큘럼 재편성 — 설계 문서

작성일: 2026-06-24
목적: 개인적으로 여기저기 흩어 풀어온 코딩테스트 풀이를, 이직 준비하는 친구가
**커리큘럼 순서대로 직접 풀어볼 수 있는** 학습 레포로 재편성한다.

---

## 1. 배경 / 문제

현재 레포는 유기적으로 자라나서 다음 문제가 있다.

- **중복**: 같은 문제 세트가 `tier1~3`(정답본) 과 `practice/`(골격, 일부는 또 풀려있음) 에 이중 존재.
  `practice2/`(자료구조 6개) 도 tier 일부와 중복.
- **혼재**: `.class` 컴파일 산출물, 개인 면접 자료(`INTERVIEW_DAY.md`, `jobpostings.md`,
  특정 회사 대비 `interview-cards.md`)가 학습 자료와 섞여 있음.
- **남이 보기 어려움**: 챕터/난이도 흐름이 폴더명에 드러나지 않음.

## 2. 목표

세 챕터의 학습 흐름을 가진 깔끔한 커리큘럼 레포를 만든다.

1. **Ch1 순수 자료구조 직접 구현** — 자료구조 자체를 0부터 만든다.
2. **Ch2 대표 유형** — 자료구조를 *사용하는* 대표 알고리즘 패턴.
3. **Ch3 실무 기준 코드 작성** — 정책 분리/OOP 가 들어가는 설계형 문제.

각 챕터 README 에 그 챕터의 유형 목록·빈출도·풀이 순서를 담는다.

## 3. 핵심 결정 (확정)

| 항목 | 결정 | 비고 |
|------|------|------|
| 답/해설 분리 | **문제별 3-파일 동거** (단일 브랜치) | 별도 브랜치 대신. checkout 없이 바로 참고 |
| 재편성 범위 | **새 구조로 이전 + 기존 폴더 삭제** | tier*/practice/practice2 중복 단일화 |
| 해설 깊이 | **전 문제 풀 해설** (아이디어+복잡도+함정) | `interview-cards.md` 카드 재활용 |
| 개인 자료 | **`_personal/` 로 격리** | 삭제 아님, 루트에서만 치움 |

## 4. 최종 디렉토리 구조

```
coding-curriculum 루트/
├── README.md                      커리큘럼 전체 안내 + 진행 순서 + 환경(Java 21 단일파일)
├── ch1_data-structures/           순수 자료구조 직접 구현
│   ├── README.md                  유형 목록 + 빈출도 + 풀이 순서
│   ├── 01_MinStack/
│   │   ├── MinStack.java          ← 골격 (// TODO 본문, main 테스트 포함) ★학습자가 푸는 것
│   │   ├── Solution.java          ← 정답 코드 (runnable)
│   │   └── SOLUTION.md            ← 해설 (아이디어/복잡도/함정)
│   └── ...
├── ch2_patterns/                  대표 알고리즘 유형
│   ├── README.md
│   └── (해시·투포인터 / 트리 / 그래프 / 힙 / DP / 문자열)
├── ch3_real-world/                실무 기준 설계형
│   ├── README.md
│   └── (환전 / 주차장 / 계약 / 정산)
├── appendix/
│   └── cheatsheets/               string·comparator·함수형·datetime·interval (java/cheatsheets 이동)
├── _personal/                     개인 면접 자료 (커리큘럼 아님, 루트에서 격리)
│   ├── INTERVIEW_DAY.md
│   ├── jobpostings.md
│   ├── interview-cards.md         (원본 보존; 내용은 각 SOLUTION.md 로 흡수)
│   └── run, run.sh
└── .gitignore                     *.class, out/, .idea  (이미 존재 → 추적 해제 추가)
```

### 문제별 3-파일 규칙 (모든 챕터 공통)

- `<Name>.java` — **골격**. 메서드 본문을 `// TODO` 로 비우고, 검증용 `main()` assert 테스트는 유지.
  학습자는 `java -ea <Name>.java` 로 채우며 검증한다.
- `Solution.java` — **정답**. 기존 정답본을 그대로 둔다 (runnable). 파일명과 클래스명이
  달라도 Java 21 source-file 모드(`java Solution.java`)로 단독 실행 가능.
- `SOLUTION.md` — **해설**. 인터페이스 → 핵심 아이디어 → 시간/공간 복잡도 → 불변식 → 함정.

> 골격과 Solution 은 별도 파일이라 함께 컴파일되지 않으므로 클래스명 충돌 없음.

## 5. 챕터별 문제 매핑 (중복 단일화 결과)

### Ch1 — 순수 자료구조 직접 구현 (17)
- **스택/큐**: MinStack(LC155), QueueUsingTwoStacks(LC232), StackUsingQueue(LC225),
  MyCircularQueue(LC622), MyCircularDeque(LC641)
- **링크드리스트**: ReverseLinkedList(LC206), MergeTwoSortedLists(LC21),
  LinkedListCycle(LC141), RemoveNthFromEnd(LC19), DesignLinkedList(LC707)
- **핵심 구조**: MyHashMap(LC706), MinHeap(직접구현), Trie(LC208), BST
- **고급/조합**: LRUCache(LC146), LFUCache(LC460), Skiplist(LC1206)

### Ch2 — 대표 유형 (29)
- **해시/투포인터**: TwoSum(LC1), BestTimeBuySell(LC121), ContainerWithMostWater(LC11),
  ThreeSum(LC15), GroupAnagrams(LC49)
- **트리 재귀**: MaxDepth(LC104), SameTree(LC100), InvertBinaryTree(LC226),
  LevelOrder(LC102), LCAofBST(LC235), ValidateBST(LC98)
- **그래프 DFS/BFS**: NumberOfIslands(LC200), RottingOranges(LC994), NumberOfProvinces(LC547)
- **힙 응용**: KthLargest(LC703), TopKFrequent(LC347), FindMedianFromStream(LC295)
- **디자인**: InsertDeleteGetRandom(LC380)
- **DP**: ClimbingStairs(LC70), HouseRobber(LC198), CoinChange(LC322), LIS(LC300)
- **문자열(카카오형)**: 신규아이디추천, 신고결과받기, 튜플, 문자열압축, 제이든케이스,
  오픈채팅방, 내맘대로정렬하기

### Ch3 — 실무 기준 설계형 (5)
- CurrencyExchange(환전 — 정책 분리), ParkingLot(주차장 — 요금), ContractManager(계약 — 상태),
  ProfitSharing(정산)
- **MeetingRoomBooking(미팅룸 예약)** — 신규 작성 문제.
  - 동작: 여러 방 중 빈 방에 시간 구간 예약. 모든 방이 그 시간에 겹치면 거절.
  - 시간 표현: 당일 기준 `"HH:MM"` 문자열 → 분 단위 int 변환.
  - 인터페이스: `MeetingRoomBooking(int roomCount)` → `int book(String start, String end)`
    가 배정된 방 번호 반환, 모두 겹치면 `-1`.
  - 겹침 판정: `[s1,e1)` vs `[s2,e2)` 는 `s1 < e2 && s2 < e1` 일 때 겹침.
  - 설계 포인트: 시간 파싱 / 방 배정 정책 분리, `appendix/cheatsheets/interval-overlap` 직결.
  - 취소·조회·최단 빈시간 같은 부가 연산은 없음(단순 예약만).

총 ~51문제.

### 경계 판단 (확정)
- `InsertDeleteGetRandom(LC380)` → 디자인이지만 알고리즘 성격이 강해 **Ch2**.
- `LRU/LFU/Skiplist` → 기존 tier3였으나 "자료구조 설계"라 **Ch1 고급**으로 승격.

## 6. README 구성

### 루트 README.md
- 한 줄 소개 + 3챕터 학습 흐름 다이어그램(텍스트)
- 환경: Java 21 단일 파일 실행(JEP 458), 외부 의존성 없음
- 풀이 방법: 골격 `java -ea` 로 채우기 → 막히면 SOLUTION.md → 정답 Solution.java
- 챕터 링크 + 권장 진행 순서(Ch1 → Ch2 → Ch3)

### 챕터별 README.md
- 그 챕터가 기르는 능력 한 문단
- 유형 그룹별 문제 표: `# | 문제 | LC | 빈출도(★) | 핵심 개념`
- 풀이 순서 추천 (워밍업 → 본게임)

## 7. 작업 방식 (구현 시)

1. 새 디렉토리 골격 생성 (`ch1~3`, `appendix`, `_personal`).
2. 각 문제: 정답본을 `Solution.java` 로 배치 → 본문을 `// TODO` 로 비운 골격 생성 →
   `interview-cards` 해당 카드로 `SOLUTION.md` 작성(+보강).
3. 챕터/루트 README 작성.
4. `git rm --cached` 로 `.class` 추적 해제, `cheatsheets`/개인자료 이동.
5. 기존 `tier1~3`, `practice/`, `practice2/`, `string/`, `staged/`(이전 후) 정리.
6. 샘플 1문제로 `java -ea` 실행 검증 후 전체 적용.

## 8. 비목표 (YAGNI)

- 별도 solutions 브랜치 / 자동 채점 CI / 테스트 러너 프레임워크 — 만들지 않음.
- 새 문제 추가 — 이번 작업은 **기존 풀이의 재편성**에 한정.
  예외: `MeetingRoomBooking` 1문제만 신규 작성(Ch3 보강).
- 무관한 리팩터링 — 정답 코드 로직은 건드리지 않고 배치/골격화만.
