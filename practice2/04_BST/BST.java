import java.util.ArrayList;
import java.util.List;

/**
 * ═══════════════════════════════════════════════════════════
 *  Problem: Binary Search Tree (BST) — Direct Implementation
 * ═══════════════════════════════════════════════════════════
 *
 * Implement a Binary Search Tree storing distinct int values.
 * `java.util.TreeMap / TreeSet` are NOT allowed.
 *
 * --- Function Description ---
 *
 *   class BST {
 *     BST()
 *     void           insert(int val)        // ignore duplicates
 *     boolean        search(int val)
 *     void           delete(int val)        // ignore if not present
 *     List<Integer>  inorder()              // ascending order
 *   }
 *
 * --- BST Invariant ---
 *
 *   For every node N:
 *     all values in left subtree  < N.val
 *     all values in right subtree > N.val
 *
 * --- Constraints ---
 *
 *   1 <= number of operations <= 10^5
 *   -10^9 <= val <= 10^9
 *
 * --- Sample Operations ---
 *
 *   insert(5), insert(3), insert(8), insert(1), insert(4)
 *   inorder()           → [1, 3, 4, 5, 8]
 *   search(4)           → true
 *   search(99)          → false
 *   delete(3)            // 자식 2 개 → in-order successor (4) 로 대체
 *   inorder()           → [1, 4, 5, 8]
 *
 * --- Time Complexity Target ---
 *
 *   insert / search / delete : 평균 O(log n), 최악 O(n) (불균형)
 *   inorder : O(n)
 *
 * --- Hints (스스로 떠올려볼 것) ---
 *
 *   • "Node 반환 패턴": helper(node, val) 가 새 (또는 같은) node 를 반환,
 *     부모는 그 반환값을 자기 left/right 에 재대입.
 *       → root = helper(root, val); 한 줄로 root 처리도 자동.
 *
 *   • delete 의 세 케이스:
 *       1) 자식 0개 (리프)     → null 반환
 *       2) 자식 1쪽            → 있는 자식 반환
 *       3) 자식 양쪽           → in-order successor (= 오른쪽 서브트리의 최솟값) 의
 *                                 *값* 을 복사 + 그 successor 를 오른쪽 서브트리에서 재귀 삭제
 *       ※ 케이스 1, 2 는 두 줄로 통일 가능:
 *            if (node.left == null)  return node.right;
 *            if (node.right == null) return node.left;
 *
 *   • findMin: while (node.left != null) node = node.left;
 *
 *   • inorder: 왼쪽 다 처리 → 자기 → 오른쪽 다 처리.
 */
class BST {

    private static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    private Node root;

    public BST() {
        root = null;
    }

    // ───────────────────────────────────────────
    // insert
    // ───────────────────────────────────────────
    public void insert(int val) {
        root = insertHelper(root, val);
    }

    public static Node insertHelper(Node node, int val){
        if(node == null)  return new Node(val);
        
        if(node.val < val) node.right =  insertHelper(node.right, val);
        else if(node.val > val) node.left = insertHelper(node.left, val);

        return node;
    }


    // ───────────────────────────────────────────
    // search
    // ───────────────────────────────────────────
    public boolean search(int val) {
        return searchHelper(root, val);
    }

    public boolean searchHelper(Node node, int val){
        if(node == null) return false;
        if(node.val == val) return true;
        return node.val > val ? searchHelper(node.left, val) :searchHelper(node.right, val);
    }


    // ───────────────────────────────────────────
    // delete (세 케이스 함정)
    // ───────────────────────────────────────────
    public void delete(int val) {
        root = deleteHelper(root, val);
    }

    public Node deleteHelper(Node node, int val){
        if(node == null) return null;
        if(node.val > val) node.left = deleteHelper(node.left, val);
        else if(node. val < val) node.right = deleteHelper(node.right, val);
        else{

            if(node.left == null) return node.right;
            if(node.right == null) return node.left;

            Node successor = findMin(node.right);
            node.val = successor.val;
            node.right = deleteHelper(node.right, successor.val);
        }

        return node;
    }


    public Node findMin(Node node){
        while(node.left != null) node = node.left;
        return node;
    }


    // ───────────────────────────────────────────
    // inorder (= 정렬된 순회)
    // ───────────────────────────────────────────
    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    public void inorderHelper(Node node, List<Integer> results){
        if(node == null) return;
        inorderHelper(node.left, results);
        results.add(node.val);
        inorderHelper(node.right, results);
    }


    public static void main(String[] args) {
        BST bst = new BST();
        int[] toInsert = {5, 3, 8, 1, 4, 7, 9, 2, 6};
        for (int v : toInsert) bst.insert(v);

        // 1) insert + inorder
        assert bst.inorder().equals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9))
            : "inorder sorted";

        // 2) search
        assert bst.search(7);
        assert bst.search(1);
        assert !bst.search(10);
        assert !bst.search(0);

        // 3) 중복 insert 무시
        bst.insert(5);
        assert bst.inorder().size() == 9 : "duplicate ignored";

        // 4) delete - 리프
        bst.delete(2);
        assert !bst.search(2);
        assert bst.inorder().equals(List.of(1, 3, 4, 5, 6, 7, 8, 9));

        // 5) delete - 자식 1쪽
        bst.delete(1);
        assert !bst.search(1);
        assert bst.inorder().equals(List.of(3, 4, 5, 6, 7, 8, 9));

        // 6) delete - 자식 2쪽 (3 은 좌:없음/우:4 ... 트리 모양 따라)
        bst.delete(3);
        assert !bst.search(3);
        assert bst.inorder().equals(List.of(4, 5, 6, 7, 8, 9));

        // 7) 루트 삭제 (자식 2쪽)
        bst.delete(5);
        assert !bst.search(5);
        assert bst.inorder().equals(List.of(4, 6, 7, 8, 9));

        // 8) 없는 값 delete - 무시
        bst.delete(100);
        assert bst.inorder().equals(List.of(4, 6, 7, 8, 9));

        // 9) 모두 삭제
        BST bst2 = new BST();
        bst2.insert(10);
        bst2.delete(10);
        assert !bst2.search(10);
        assert bst2.inorder().isEmpty();

        // 10) 한쪽 치우친 트리
        BST bst3 = new BST();
        for (int v : new int[]{1, 2, 3, 4, 5}) bst3.insert(v);
        assert bst3.inorder().equals(List.of(1, 2, 3, 4, 5));
        bst3.delete(3);
        assert bst3.inorder().equals(List.of(1, 2, 4, 5));

        System.out.println("✅ BST: All tests passed");
    }
}
