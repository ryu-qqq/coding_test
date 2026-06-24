/**
 * LeetCode 208 - Implement Trie (Prefix Tree)  [정답]
 *
 * 핵심: 각 노드가 children[26] + isEnd 를 갖는 트리. 글자마다 idx = c-'a' 로 자식을 따라간다.
 *       search는 끝 노드의 isEnd까지 확인, startsWith는 경로 존재만 확인.
 * 복잡도: 모든 연산 O(L) (L = 단어/접두사 길이).
 * 자세한 해설 → SOLUTION.md
**/

class Trie{

    private static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void insert(String word){
        TrieNode node = root;
        for(char c : word.toCharArray()){
            int idx = c - 'a';
            if(node.children[idx] == null){
                node.children[idx] = new TrieNode();   // 없는 글자면 새 노드 생성
            }
            node = node.children[idx];
        }
        node.isEnd = true;   // 단어 끝 표시
    }

    public boolean search(String word){
        TrieNode node = findNode(word);
        return node != null && node.isEnd;   // 경로 존재 + 단어 끝
    }

    private TrieNode findNode(String s){
        TrieNode node = root;
        for(char c : s.toCharArray()){
            int idx = c - 'a';
            if(node.children[idx] == null) return null;
            node = node.children[idx];
        }
        return node;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;   // 경로만 존재하면 OK
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
