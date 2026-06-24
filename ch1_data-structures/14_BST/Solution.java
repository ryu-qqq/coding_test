import java.util.ArrayList;
import java.util.List;

/**
 * ═══════════════════════════════════════════════════════════
 *  Problem: Binary Search Tree (BST) — Direct Implementation  [정답]
 * ═══════════════════════════════════════════════════════════
 *
 * 핵심: "Node 반환 패턴" — helper(node, val)가 새(또는 같은) 노드를 반환하고
 *       부모가 그 반환값을 자기 left/right에 재대입한다. root는 root = helper(root, val) 한 줄로 처리.
 * 불변식: 모든 노드 N에 대해 left subtree < N.val < right subtree.
 * 복잡도: insert/search/delete 평균 O(log n) 최악 O(n)(불균형), inorder O(n).
 * 자세한 해설 → SOLUTION.md
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
        // node.val == val 이면 중복 → 아무것도 안 함(무시)

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
            // 케이스 1, 2: 자식 0개 또는 1개 → 있는 자식(또는 null) 반환
            if(node.left == null) return node.right;
            if(node.right == null) return node.left;

            // 케이스 3: 자식 2개 → in-order successor(오른쪽 서브트리 최솟값) 값 복사 후 재귀 삭제
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
