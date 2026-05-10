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
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word){
        TrieNode node = findNode(word);
        return node != null && node.isEnd;
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
        return findNode(prefix) != null;                                                                                                                                                                                    
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