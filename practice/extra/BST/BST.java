import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree (BST) 직접 구현
 *
 * 문제: int 값을 저장하는 BST 를 처음부터 구현한다.
 *   - Java TreeMap / TreeSet 사용 금지.
 *
 * --- 인터페이스 ---
 *   void insert(int val)
 *   boolean search(int val)
 *   void delete(int val)
 *   List<Integer> inorder()       (in-order 순회 결과 = 오름차순 정렬)
 *
 * --- 시간복잡도 목표 ---
 *   insert / search / delete : 평균 O(log n), 최악 O(n) (불균형)
 *   inorder : O(n)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *   ※ "왼쪽 서브트리 모든 값 < 노드 < 오른쪽 서브트리 모든 값"
 *
 * --- 함정 (면접 단골) ---
 *   delete 의 세 케이스:
 *     1) 리프 노드 (자식 0개)
 *     2) 자식이 한 쪽만 있는 노드
 *     3) 자식이 양쪽 다 있는 노드 → in-order successor (오른쪽 서브트리의 최솟값) 로 대체
 *
 *   재귀 vs 반복:
 *     insert/search 는 재귀가 더 깔끔하지만, 반복으로도 가능 (스택 절약).
 *     delete 는 재귀가 압도적으로 깔끔.
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

    // ─────────────────────────────────────────────
    // insert: 같은 값은 무시 (중복 미허용 정책)
    // ─────────────────────────────────────────────
    public void insert(int val) {
        root = insertHelper(root, val);
    }

    private Node insertHelper(Node node, int val){
        if(node == null) return new Node(val);
        if(val < node.val) node.left = insertHelper(node.left, val);
        else if(val > node.val)node.right= insertHelper(node.right, val);
        return node;
    }

    // ─────────────────────────────────────────────
    // search: 값 존재 여부
    // ─────────────────────────────────────────────
    public boolean search(int val) {
        return searchHelper(root, val);
    }

    private boolean searchHelper(Node node, int val){
        if(node ==null) return false;
        if(val == node.val) return true;
        return val < node.val ?
            searchHelper(node.left, val) : searchHelper(node.right, val);
    }

    // ─────────────────────────────────────────────
    // delete: 값을 가진 노드 제거 (없으면 무시)
    // ─────────────────────────────────────────────
    public void delete(int val) {
        deleteHelper(root, val);
    }

    private Node deleteHelper(Node node, int val){
        if(node == null) return null;
        if(val < node.val) node.left = deleteHelper(node.left, val);
        else if(val >node.val) node.right = deleteHelper(node.right, val);
        else{
            if(node.left == null) return node.right;
            if(node.right == null) return node.left;

            Node successor = findMin(node.right);
            node.val = successor.val;
            node.right = deleteHelper(node.right, successor.val);
        }

        return node;
    }

    private Node findMin(Node node){
        while(node.left != null) node = node.left;
        return node;
    }

    // ─────────────────────────────────────────────
    // inorder: in-order 순회 → BST 면 결과가 오름차순
    // ─────────────────────────────────────────────
    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Integer> result){
        if(node == null) return;
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }


    public static void main(String[] args) {
        BST bst = new BST();

        // 1) insert + inorder → 오름차순 확인
        int[] toInsert = {5, 3, 8, 1, 4, 7, 9, 2, 6};
        for (int v : toInsert) bst.insert(v);

        List<Integer> sorted = bst.inorder();
        assert sorted.equals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9))
            : "inorder should be sorted ascending. got: " + sorted;

        // 2) search: 있는 값 / 없는 값
        assert bst.search(7) : "7 should exist";
        assert bst.search(1) : "1 should exist";
        assert !bst.search(10) : "10 should NOT exist";
        assert !bst.search(0) : "0 should NOT exist";

        // 3) 중복 insert 무시
        bst.insert(5);
        assert bst.inorder().size() == 9 : "duplicate insert should be ignored";

        // 4) delete - 리프 노드 (자식 0개)
        bst.delete(2);     // 2 는 리프
        assert !bst.search(2) : "2 should be deleted";
        assert bst.inorder().equals(List.of(1, 3, 4, 5, 6, 7, 8, 9))
            : "after delete 2";

        // 5) delete - 자식 한 쪽만 있는 노드
        bst.delete(1);     // 1 은 자식이 없거나 한쪽
        assert !bst.search(1);
        assert bst.inorder().equals(List.of(3, 4, 5, 6, 7, 8, 9));

        // 6) delete - 자식 양쪽 다 있는 노드 (가장 까다로움)
        bst.delete(3);     // 3 은 자식 4 와 (없음) ... 트리 모양에 따라 다름
        assert !bst.search(3);
        // 결과는 트리 모양에 따라 다르지만, sorted 여야 함
        List<Integer> after3 = bst.inorder();
        assert after3.equals(List.of(4, 5, 6, 7, 8, 9))
            : "after delete 3, still sorted: " + after3;

        // 7) 루트 삭제 (양쪽 자식 다 있음)
        bst.delete(5);
        assert !bst.search(5);
        assert bst.inorder().equals(List.of(4, 6, 7, 8, 9))
            : "after delete root 5";

        // 8) 없는 값 delete - 무시
        bst.delete(100);
        assert bst.inorder().equals(List.of(4, 6, 7, 8, 9));

        // 9) 모두 삭제 후 빈 상태
        BST bst2 = new BST();
        bst2.insert(10);
        bst2.delete(10);
        assert !bst2.search(10);
        assert bst2.inorder().isEmpty() : "should be empty";

        // 10) 단일 노드 트리 + 한쪽 치우친 트리
        BST bst3 = new BST();
        int[] skew = {1, 2, 3, 4, 5};   // 오른쪽으로 치우침
        for (int v : skew) bst3.insert(v);
        assert bst3.inorder().equals(List.of(1, 2, 3, 4, 5));
        bst3.delete(3);
        assert bst3.inorder().equals(List.of(1, 2, 4, 5));

        System.out.println("✅ BST: All tests passed");
    }
}
