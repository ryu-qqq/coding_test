# Coding Test - 자료구조 직접 구현

자료구조를 직접 구현하는 유형의 코딩 테스트 연습 환경.

## 환경
- Java 21 (단일 파일 실행 모드 사용 - JEP 458)
- 외부 의존성 없음 (Gradle/Maven 불필요)

## 디렉토리 구조
```
coding_test/
├── tier1/   기초 — 보조 스택/포인터 트릭
│   ├── MinStack.java                 (LC 155)
│   ├── QueueUsingTwoStacks.java      (LC 232)
│   └── MyCircularQueue.java          (LC 622)
├── tier2/   중급 — 핵심 자료구조
│   ├── MinHeap.java                  (직접 구현)
│   ├── MyHashMap.java                (LC 706)
│   └── Trie.java                     (LC 208)
├── tier3/   고급 — 조합형
│   ├── LRUCache.java                 (LC 146)
│   ├── LFUCache.java                 (LC 460)
│   └── Skiplist.java                 (LC 1206)
├── run.sh                            (전체 실행)
└── README.md
```

## 풀이 방법

1. 각 파일의 `// TODO` 부분을 구현
2. 단일 파일 실행 (assertion 활성화 필수):
   ```bash
   java -ea tier1/MinStack.java
   ```
3. 모든 테스트 통과 시 `✅ ... All tests passed` 출력

전체 실행:
```bash
./run.sh
```

## 풀이 순서 권장
1. 각 파일 상단 주석의 **인터페이스 → 시간복잡도 목표 → 수도코드 → 불변식** 순서로 읽기
2. 화이트보드 풀이라 생각하고 자료구조 / 불변식부터 결정
3. main()의 테스트가 통과할 때까지 구현
4. 통과 후 시간복잡도가 목표를 만족하는지 검증

## 면접 포인트
- **불변식 (invariant)**: 모든 연산 후에도 유지되는 속성
- **amortized 분석**: 두 스택 큐가 대표적
- **엣지 케이스**: 빈 상태, 단일 원소, 용량 초과, 중복 키

## 연습 문제
각 티어 본 문제를 푼 뒤 같은 패턴의 LeetCode 변형 문제를 한 번 더 풀어 체화한다.
변형 문제 목록과 면접 단골 질문은 [docs/practice.md](docs/practice.md) 참고.
