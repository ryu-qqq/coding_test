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
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
**/

class Trie{

    private static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    // TODO: 필요한 필드를 선언하세요 (root 등)

    public Trie() {
        // TODO: 초기화
    }


    public void insert(String word){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean search(String word){
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean startsWith(String prefix) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");

        assert trie.search("apple")   : "apple should exist";
        assert !trie.search("app")    : "app not inserted yet";
        assert trie.startsWith("app") : "prefix app exists";

        trie.insert("app");
        assert trie.search("app")     : "app now exists";

        // 빈 Trie
        Trie empty = new Trie();
        assert !empty.search("a")     : "empty search";
        assert !empty.startsWith("a") : "empty startsWith";

        System.out.println("✅ Trie: All tests passed");
    }
}
