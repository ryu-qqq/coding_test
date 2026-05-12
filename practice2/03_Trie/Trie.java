/**
 * ═══════════════════════════════════════════════════════════
 *  Problem: Trie (Prefix Tree) — Direct Implementation
 * ═══════════════════════════════════════════════════════════
 *
 * Implement a Trie for lowercase English words.
 *
 * --- Function Description ---
 *
 *   class Trie {
 *     Trie()
 *     void    insert(String word)        // insert a word
 *     boolean search(String word)        // exact word match
 *     boolean startsWith(String prefix)  // any word starts with prefix?
 *   }
 *
 * --- Behavior ---
 *
 *   • insert("apple") then search("apple") → true
 *   • insert("apple") then search("app")   → false   (단어 자체로 등록 안 됨)
 *   • insert("apple") then startsWith("app") → true  (접두사)
 *   • 빈 문자열 입력은 가정하지 않음.
 *
 * --- Constraints ---
 *
 *   • word, prefix consist of lowercase English letters only.
 *   • 1 <= word.length, prefix.length <= 2000
 *   • At most 3 * 10^4 calls to insert, search, startsWith.
 *
 * --- Sample Operations ---
 *
 *   insert("apple")
 *   search("apple")       → true
 *   search("app")         → false
 *   startsWith("app")     → true
 *   insert("app")
 *   search("app")         → true
 *
 * --- Time Complexity Target ---
 *
 *   insert / search / startsWith : O(L)   (L = 문자열 길이)
 *
 * --- Hints (스스로 떠올려볼 것) ---
 *
 *   • 각 노드는 자식 26 개 (a~z) 를 가짐. → children = new TrieNode[26]
 *   • char c 의 인덱스: c - 'a'
 *   • isEnd 플래그로 "이 노드가 단어의 끝인지" 표시.
 *       insert 끝나면 isEnd = true.
 *       search 는 끝 노드의 isEnd 까지 검사.
 *       startsWith 는 끝 노드만 도달하면 OK (isEnd 무시).
 *   • search 와 startsWith 의 차이는 마지막 한 줄 (isEnd 검사 여부).
 */

class Trie {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // ───────────────────────────────────────────
    // insert: 한 글자씩 내려가며 없으면 새 노드 생성.
    //         마지막에 isEnd = true.
    // ───────────────────────────────────────────
    public void insert(String word) {
        // TODO
    }

    // ───────────────────────────────────────────
    // search: 한 글자씩 따라가며 없으면 false.
    //         끝까지 갔으면 isEnd 검사.
    // ───────────────────────────────────────────
    public boolean search(String word) {
        // TODO
        return false;
    }

    // ───────────────────────────────────────────
    // startsWith: search 와 동일하되 isEnd 검사 X.
    //              끝까지 도달했으면 true.
    // ───────────────────────────────────────────
    public boolean startsWith(String prefix) {
        // TODO
        return false;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");

        assert trie.search("apple") == true  : "search apple";
        assert trie.search("app")   == false : "app not inserted yet";
        assert trie.startsWith("app") == true : "prefix app";

        trie.insert("app");
        assert trie.search("app") == true : "app now inserted";

        // 여러 단어
        Trie t2 = new Trie();
        t2.insert("car");
        t2.insert("card");
        t2.insert("care");
        t2.insert("careful");
        assert t2.search("car") == true;
        assert t2.search("card") == true;
        assert t2.search("care") == true;
        assert t2.search("careful") == true;
        assert t2.search("careless") == false;
        assert t2.search("ca") == false;
        assert t2.startsWith("car") == true;
        assert t2.startsWith("care") == true;
        assert t2.startsWith("careless") == false;
        assert t2.startsWith("cat") == false;

        // 단일 문자
        Trie t3 = new Trie();
        t3.insert("a");
        assert t3.search("a") == true;
        assert t3.search("ab") == false;
        assert t3.startsWith("a") == true;
        assert t3.startsWith("b") == false;

        // 같은 단어 두 번 insert → 동작 동일
        t3.insert("a");
        assert t3.search("a") == true;

        // 긴 단어
        Trie t4 = new Trie();
        t4.insert("abcdefghijklmnopqrstuvwxyz");
        assert t4.search("abcdefghijklmnopqrstuvwxyz");
        assert t4.startsWith("abcd");
        assert !t4.search("abcd");   // 단어 아님, prefix 만

        System.out.println("✅ Trie: All tests passed");
    }
}
