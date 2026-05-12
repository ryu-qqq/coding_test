# Practice 2 — 면접 직전 자료구조 직접 구현 (HackerRank 스타일)

면접 라이브 코딩 (HackerRank, CoderPad 등) 단골 패턴.
3개 문제, 우선순위 순.

## 문제 리스트

| # | 문제 | 빈출도 | 예상 시간 |
|---|---|---|---|
| 01 | Min Heap (Priority Queue) | ★★★★★ | 30분 |
| 02 | LRU Cache | ★★★★★ | 1시간 |
| 03 | Trie (Prefix Tree) | ★★★★ | 30분 |
| 04 | Binary Search Tree | ★★★★ | 30~45분 |
| 05 | Queue using 2 Stacks (LC 232) | ★★★ | 15분 |
| 06 | Stack using Queue (LC 225) | ★★★ | 15분 |

## 풀이 순서 추천

1. **05/06 워밍업 (Stack ↔ Queue 변환)** — 15분씩, 손에 익은 패턴 빨리 점검.
2. **01 Min Heap** — 인덱스 산수 + siftUp/siftDown 복습.
3. **04 BST** — delete 세 케이스 함정 점검.
4. **03 Trie** — 짧고 외우기 좋음.
5. **02 LRU Cache 본 게임** — HashMap + Doubly LinkedList 결합 패턴. 면접 최강 빈출.

시간 부족하면 **01 (Heap) + 02 (LRU)** 만이라도 손에 굳히기.

## HackerRank 환경 노트

- 실제 면접은 `Scanner` 또는 `BufferedReader` 로 입력 받고 `System.out.println` 으로 출력.
- 이 학습 파일은 검증 편의를 위해 `main` 에 assert 패턴.
- 시험장에선 입출력 처리 1 분만 더 쓰면 됨. 핵심은 **자료구조 구현 자체**.

## 면접 답변 흐름 (3 문제 모두 공통)

1. **"이 자료구조의 핵심 약속이 뭐냐"** 부터 말로 정리 (1~2 문장).
2. **인터페이스 (메서드 시그너처) 빈 골격** 부터 작성.
3. 각 메서드의 **시간복잡도** 명시.
4. **함정 케이스** 한두 개 (null, 중복, 빈 자료구조 등) 처리 명시.
5. 구현 → 손 시뮬레이션으로 검증.

행운을 빈다. 침착하게.
