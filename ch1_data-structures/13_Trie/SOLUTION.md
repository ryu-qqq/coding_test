# Trie (LC 208) — 해설

> 막히기 전에 골격(`Trie.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
영문 소문자(a-z) 단어를 저장하는 트라이(접두사 트리).
`insert(word)`, `search(word)`(정확히 일치하는 단어 존재?), `startsWith(prefix)`(이 prefix로 시작하는 단어 존재?).

## 핵심 직관 (1줄)
각 글자마다 `idx = c - 'a'`로 자식을 따라간다. **search는 끝 노드의 `isEnd`까지 확인**, **startsWith는 경로 존재만** 확인.

## 자료구조
- `TrieNode` : `TrieNode[26] children` + `boolean isEnd`(이 노드에서 끝나는 단어가 있는가).
- `root` : 빈 루트 노드.

## 알고리즘
1. `insert(w)` : root에서 시작, 각 글자마다 `children[idx]`가 null이면 새 노드 생성 후 따라가고, 마지막 노드에 `isEnd = true`.
2. `findNode(s)`(공통 헬퍼) : 글자를 따라가다 중간에 null이면 `null`, 끝까지 가면 그 노드 반환.
3. `search(w)` : `findNode(w) != null && node.isEnd`.
4. `startsWith(p)` : `findNode(p) != null`.

## 불변식
같은 prefix를 가진 단어들은 **같은 경로(노드)를 공유**한다.

## 복잡도
- 시간: 모든 연산 O(L) (L = 단어/접두사 길이).
- 공간: O(총 저장 글자 수 × 26).

## 함정 ⚠️
- `search`와 `startsWith`의 차이가 핵심. search는 반드시 `isEnd`까지 봐야 한다("app"이 경로엔 있어도 단어로 등록 안 됐으면 false). startsWith는 경로만 있으면 true.
- 빈 문자열은 `root.isEnd`로 자연스럽게 흡수된다(루프가 0회 → root 반환).
- `idx = c - 'a'`는 소문자 a-z 가정. 대문자/숫자가 들어오면 배열 범위를 벗어난다.

## 대안 / 최적화
- `children[26]` 배열 대신 `HashMap<Character, TrieNode>`를 쓰면 희소한 트라이에서 메모리 절약(대신 배열보다 약간 느림, 알파벳이 클 때 유리).

## 면접 답변 (한국어 1분)
> "트라이는 각 노드가 26개 자식 포인터와 단어 끝 플래그 isEnd를 갖는 트리입니다. insert는 root에서
> 각 글자마다 children[c-'a']를 따라가며 없으면 새 노드를 만들고 마지막 노드의 isEnd를 true로 합니다.
> search와 startsWith는 공통 헬퍼로 경로를 따라간 노드를 얻는데, search는 isEnd까지 확인하고
> startsWith는 경로 존재만 봅니다. 모든 연산이 단어 길이 L에 비례하는 O(L)입니다. 메모리를 줄이려면
> 26 배열 대신 HashMap을 쓸 수 있습니다."

## Follow-up
- **search와 startsWith 차이?** search는 단어가 정확히 등록됐는지(`isEnd=true`)까지 봐야 하고, startsWith는 그 prefix로 시작하는 단어가 하나라도 있는지(경로 존재)만 본다.
- **응용?** 자동완성, 검색 추천, 사전 prefix 검색, 라우팅 테이블. Aho-Corasick 다중 패턴 매칭의 베이스.
- **delete를 추가하려면?** 단어 끝의 isEnd를 끄고, 자식이 없는 노드들을 아래에서부터 정리.
