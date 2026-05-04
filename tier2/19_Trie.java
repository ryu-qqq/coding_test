/**
 * LeetCode 208 - Implement Trie (Prefix Tree)
 *
 * 문제: 영어 소문자(a-z) 단어를 저장하는 trie 구현
 *
 * --- 인터페이스 ---
 *   insert(word)
 *   search(word): boolean      (정확히 일치하는 단어가 들어있는가)
 *   startsWith(prefix): boolean (이 prefix로 시작하는 단어가 있는가)
 *
 * --- 시간복잡도 목표 ---
 *   모든 연산 O(L)   (L = 단어/접두사 길이)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   각 노드는 26개의 자식 포인터 + 단어 끝 플래그
 *
 *   class Node {
 *     Node[] children = new Node[26]
 *     boolean isEnd
 *   }
 *
 *   insert(word):
 *     node = root
 *     for ch in word:
 *       i = ch - 'a'
 *       if node.children[i] == null: node.children[i] = new Node()
 *       node = node.children[i]
 *     node.isEnd = true
 *
 *   traverse(s):  // 공통 헬퍼
 *     node = root
 *     for ch in s:
 *       i = ch - 'a'
 *       if node.children[i] == null: return null
 *       node = node.children[i]
 *     return node
 *
 *   search(word):
 *     node = traverse(word)
 *     return node != null && node.isEnd
 *
 *   startsWith(prefix):
 *     return traverse(prefix) != null
 *
 * --- 핵심 차이 ---
 *   search → 반드시 isEnd=true 까지 도달해야 함
 *   startsWith → 경로만 존재하면 됨
 *
 * --- 면접 포인트 ---
 *   - HashMap<Character, Node> vs Node[26] trade-off
 *     (배열은 빠르지만 메모리 많이 씀, 맵은 유연)
 *   - Trie 응용: autocomplete, 검색 추천, 사전 검색
 */
class Trie {

    public Trie() {
        // TODO: root 노드 초기화
    }

    public void insert(String word) {
        // TODO
    }

    public boolean search(String word) {
        // TODO
        return false;
    }

    public boolean startsWith(String prefix) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple");
        assert t.search("apple");        // 정확히 일치
        assert !t.search("app");         // 'app'은 단어로 없음
        assert t.startsWith("app");      // prefix로는 존재
        t.insert("app");
        assert t.search("app");          // 이제는 단어로도 존재

        // 빈 문자열, 단일 문자
        t.insert("a");
        assert t.search("a");
        assert t.startsWith("a");

        // 없는 단어
        assert !t.search("banana");
        assert !t.startsWith("ban");

        System.out.println("✅ Trie: All tests passed");
    }
}
