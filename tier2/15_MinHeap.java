/**
 * Min Heap (Priority Queue) 직접 구현
 *
 * 문제: 배열 기반 이진 힙 구현 (Java PriorityQueue 사용 금지)
 *
 * --- 인터페이스 ---
 *   insert(val)
 *   extractMin(): int
 *   peek(): int
 *   size(): int
 *
 * --- 시간복잡도 목표 ---
 *   insert: O(log n)
 *   extractMin: O(log n)
 *   peek: O(1)
 *   buildHeap (배치): O(n)  (도전 과제)
 *
 * --- 핵심 아이디어 (수도코드) ---
 *   배열로 완전 이진 트리 표현:
 *     parent(i) = (i - 1) / 2
 *     left(i)   = 2*i + 1
 *     right(i)  = 2*i + 2
 *
 *   insert(val):
 *     arr[size++] = val
 *     siftUp(size - 1)
 *
 *   extractMin():
 *     min = arr[0]
 *     arr[0] = arr[--size]
 *     siftDown(0)
 *     return min
 *
 *   siftUp(i):
 *     while i > 0 and arr[parent(i)] > arr[i]:
 *       swap(parent(i), i)
 *       i = parent(i)
 *
 *   siftDown(i):
 *     while left(i) < size:
 *       smallest = i
 *       if left(i)  < size and arr[left(i)]  < arr[smallest]: smallest = left(i)
 *       if right(i) < size and arr[right(i)] < arr[smallest]: smallest = right(i)
 *       if smallest == i: break
 *       swap(i, smallest)
 *       i = smallest
 *
 * --- 불변식 (Heap Property) ---
 *   모든 노드 i에 대해 arr[parent(i)] <= arr[i]
 *
 * --- 면접 포인트 ---
 *   - extractMin에서 왜 마지막 원소를 root로 옮기고 siftDown 하나?
 *     → 완전 이진 트리 형태를 깨지 않으면서 root를 비우기 위함
 *   - 왜 siftUp/siftDown 둘 다 필요한가?
 *     → insert는 leaf에서 위로, extract는 root에서 아래로
 */
class MinHeap {

    public MinHeap(int capacity) {
        // TODO: int[] 배열, size 초기화
    }

    public void insert(int val) {
        // TODO
    }

    public int extractMin() {
        // TODO
        return 0;
    }

    public int peek() {
        // TODO
        return 0;
    }

    public int size() {
        // TODO
        return 0;
    }

    private void siftUp(int i) {
        // TODO
    }

    private void siftDown(int i) {
        // TODO
    }

    public static void main(String[] args) {
        MinHeap h = new MinHeap(20);
        int[] input = {5, 3, 8, 1, 9, 2, 7, 4, 6};
        for (int v : input) h.insert(v);
        assert h.size() == 9;
        assert h.peek() == 1;

        // 정렬되어 나와야 함
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int e : expected) {
            int actual = h.extractMin();
            assert actual == e : "expected " + e + " but got " + actual;
        }
        assert h.size() == 0;

        // 단일 원소
        MinHeap h2 = new MinHeap(5);
        h2.insert(42);
        assert h2.peek() == 42;
        assert h2.extractMin() == 42;
        assert h2.size() == 0;

        // 중복 값
        MinHeap h3 = new MinHeap(5);
        h3.insert(3);
        h3.insert(3);
        h3.insert(1);
        assert h3.extractMin() == 1;
        assert h3.extractMin() == 3;
        assert h3.extractMin() == 3;

        System.out.println("✅ MinHeap: All tests passed");
    }
}
