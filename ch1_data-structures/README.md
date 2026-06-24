# Ch1. 순수 자료구조 직접 구현

자료구조를 **0부터 손으로 만드는** 챕터. 라이브 코딩(HackerRank·CoderPad)에서 단골로 나온다.
면접에서는 "이 자료구조의 핵심 약속(불변식)이 뭐냐"부터 말로 정리하고 시작하는 게 포인트.

## 문제 목록

| # | 문제 | LC | 빈출도 | 핵심 개념 |
|---|------|----|:------:|-----------|
| 01 | MinStack | 155 | ★★★★ | 보조 스택으로 min O(1) 유지 |
| 02 | QueueUsingTwoStacks | 232 | ★★★★ | amortized, in/out 스택 |
| 03 | StackUsingQueue | 225 | ★★★ | 큐로 스택 흉내 |
| 04 | MyCircularQueue | 622 | ★★★ | 링버퍼, head/size 산수 |
| 05 | MyCircularDeque | 641 | ★★★ | 양방향 링버퍼 |
| 06 | ReverseLinkedList | 206 | ★★★★★ | 포인터 3개 뒤집기 |
| 07 | MergeTwoSortedLists | 21 | ★★★★ | dummy head 병합 |
| 08 | LinkedListCycle | 141 | ★★★★ | Floyd 토끼·거북이 |
| 09 | RemoveNthFromEnd | 19 | ★★★★ | 간격 포인터 |
| 10 | DesignLinkedList | 707 | ★★★ | 이중 연결 + sentinel |
| 11 | MyHashMap | 706 | ★★★★ | 버킷 + 체이닝 |
| 12 | MinHeap | 직접구현 | ★★★★★ | siftUp/siftDown 인덱스 산수 |
| 13 | Trie | 208 | ★★★★ | 자식 배열/맵, prefix |
| 14 | BST | 직접구현 | ★★★★ | insert/search/delete 3케이스 |
| 15 | LRUCache | 146 | ★★★★★ | HashMap + 이중 연결 리스트 |
| 16 | LFUCache | 460 | ★★★ | freq 버킷 + LRU 결합 |
| 17 | Skiplist | 1206 | ★★ | 다단 인덱스, 확률적 레벨 |

## 풀이 순서 추천

1. **워밍업**: 06(ReverseLinkedList) → 02(Queue↔Stack) — 손에 익은 포인터/스택 패턴 빠르게 점검.
2. **링버퍼**: 04 → 05 — head/size 산수, 가득 참/빈 상태 구분.
3. **핵심 구조**: 12(MinHeap) → 13(Trie) → 11(MyHashMap) → 14(BST).
4. **본 게임(빈출 최강)**: 15(LRUCache) — HashMap + 이중 연결 리스트 결합. 이거 하나는 손에 굳히기.
5. 여유 되면 16(LFU) → 17(Skiplist).

> 시간 부족하면 **12(Heap) + 15(LRU)** 만이라도 확실히.
