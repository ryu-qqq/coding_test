/**
 * LeetCode 641 - Design Circular Deque
 *
 * 문제: 고정 용량의 원형 디큐 (양방향 삽입/삭제).
 *
 * --- 인터페이스 ---
 *   insertFront(value): boolean
 *   insertLast(value): boolean
 *   deleteFront(): boolean
 *   deleteLast(): boolean
 *   getFront(): int     (-1 if empty)
 *   getRear(): int      (-1 if empty)
 *   isEmpty(): boolean
 *   isFull(): boolean
 *
 * --- 시간복잡도 목표 ---
 *   모든 연산 O(1)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * ====================================
 * === 채점 결과 (2026-05-11) ===
 * ====================================
 *
 * --- 실행 결과 ---
 *   ❌ AssertionError at line 108  →  `assert dq.getRear() == 2` 깨짐.
 *
 * --- 가장 큰 문제: head/tail 의 의미가 코드 안에서 정반대로 충돌한다 ---
 *
 *   너 코드를 의미별로 분류해봐:
 *
 *     [insertFront / deleteFront 가 건드리는 변수]
 *        insertFront → tail 이동 (line 47)
 *        deleteFront → tail 이동 (line 69)
 *      → 이 두 메서드는 "tail 이 front 자리" 라고 가정하고 있다.
 *
 *     [insertLast / deleteLast 가 건드리는 변수]
 *        insertLast  → head 이동 (line 59)
 *        deleteLast  → head 이동 (line 78)
 *      → 이 두 메서드는 "head 가 rear 자리" 라고 가정하고 있다.
 *
 *     [getFront / getRear 가 보는 변수]
 *        getFront → arr[head] (line 86)   ← head 가 front 라고 가정
 *        getRear  → arr[tail] (line 91)   ← tail 이 rear 라고 가정
 *
 *   즉:
 *     insert/delete 그룹의 가정: "tail=front, head=rear"
 *     get 그룹의 가정:           "head=front, tail=rear"
 *   → **정반대**. 한 코드 안에 두 컨벤션이 섞여있다. 04번 끝에 잡았던 표준 (head=front, tail=rear) 이 무너짐.
 *
 *   손 시뮬레이션 (capacity=3, 너 코드 그대로):
 *     초기: head=0, tail=-1, size=0
 *     insertLast(1):  head=(0-1+3)%3=2, arr[2]=1  →  arr=[_,_,1], head=2, tail=-1
 *     insertLast(2):  head=(2-1+3)%3=1, arr[1]=2  →  arr=[_,2,1], head=1, tail=-1
 *     insertFront(3): tail=(-1+1)%3=0,  arr[0]=3  →  arr=[3,2,1], head=1, tail=0
 *     getRear() → arr[tail] = arr[0] = 3   ← 기대값은 2 (rear). ❌
 *     getFront() → arr[head] = arr[1] = 2   ← 기대값은 3 (front). ❌
 *
 *   디큐의 논리적 순서로는 [3, 1, 2] 인데, 코드는 arr=[3,2,1] 로 거꾸로 채워져 있고 get 도 거꾸로 본다.
 *
 * 🔴 결정적 오류:
 *
 *   1. insertFront 는 head 를, insertLast 는 tail 을 건드려야 한다. (line 47, 59)
 *      → 04 번 (CircularQueue) 에서 잡은 컨벤션: head=front, tail=rear.
 *      → 그 컨벤션 그대로 가면:
 *           insertFront: head 를 한 칸 뒤로 (head = (head-1+cap)%cap), arr[head]=v
 *           insertLast:  tail 을 한 칸 앞으로 (tail = (tail+1)%cap), arr[tail]=v
 *      → 지금 너 코드는 두 함수가 건드리는 변수를 서로 바꿔 끼웠다.
 *
 *   2. deleteFront / deleteLast 도 마찬가지로 변수가 뒤바뀌어 있다. (line 69, 78)
 *      → deleteFront: head 가 한 칸 전진 → head = (head+1)%cap
 *      → deleteLast:  tail 이 한 칸 후퇴 → tail = (tail-1+cap)%cap
 *
 *   3. 빈 deque 에 insertFront 만 호출하면 getRear() 가 arr[-1] 폭발 가능. (line 41, 91)
 *      → 초기 tail=-1. insertFront 에서 tail 을 안 건드리면 (=#1 고치고 나면 더 그렇다) tail 은 -1 그대로.
 *      → getRear() 의 isEmpty 가드는 size==0 검사인데, 1번 insertFront 후엔 size=1 이라 가드 통과 → arr[-1] 💥.
 *      → 해결: **첫 insert (size==0 → size==1 전환) 시 head 와 tail 을 같은 자리로 동기화**.
 *      → 한 원소만 있을 땐 head 와 tail 이 같은 인덱스를 가리켜야 한다 (불변식).
 *      → 코드 패턴 두 가지:
 *           (a) insertFront/Last 안에서 `if (size == 0) { 다른 쪽 인덱스도 같이 세팅 }`
 *           (b) 초기값을 head=0, tail=0 으로 잡고, 첫 insert 는 "이동 없이" 그 자리에 쓰고 size 만 ++.
 *               두 번째부터만 인덱스 이동.
 *      → 어느 패턴이 더 깔끔한지 직접 따져봐.
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   4. insertLast 의 주석이 사고를 잘못 따라가고 있다. (line 57-58)
 *        // 이게 [ () () ] 사이즈 2인경우 헤드 맨처음 0
 *        // -1 + 2 -> 1 / 2 -> 1 그러니까 맨뒤
 *      → 이 주석은 head 가 rear 라는 잘못된 매핑을 기반으로 작성됨.
 *      → 컨벤션 잡고 나서 주석도 같이 고쳐야 함.
 *
 *   5. insertFront 의 (-1 + 1) % capacity = 0 도 마찬가지로 잘못된 매핑 결과. (line 47)
 *      → 사실 tail = -1 에서 +1 해서 0 으로 가는 동작 자체는 04 번 (CircularQueue) 의 enQueue 동작과 동일.
 *      → 그래서 이 함수는 사실상 "insertLast" 의 동작을 하고 있다. 이름만 insertFront.
 *
 * --- 권장 재구성 (04 번 컨벤션 유지) ---
 *
 *   불변식:
 *     - head = 가장 앞 (front) 원소의 인덱스
 *     - tail = 가장 뒤 (rear) 원소의 인덱스
 *     - size==1 일 때 head == tail (같은 자리)
 *     - size==0 일 때 head/tail 값은 의미 없음 (get 이 -1 반환)
 *     - 0 <= size <= capacity
 *
 *   메서드별 매트릭스:
 *      insertFront: head = (head-1+cap)%cap; arr[head] = v
 *                   ※ 단 size==0 이면 head 안 옮기고 그 자리에 쓰기 (또는 옮기고 tail=head 동기화)
 *      insertLast:  tail = (tail+1)%cap;     arr[tail] = v
 *                   ※ size==0 동기화 동일
 *      deleteFront: head = (head+1)%cap
 *      deleteLast:  tail = (tail-1+cap)%cap
 *      getFront:    arr[head]
 *      getRear:     arr[tail]
 *
 * --- 손 시뮬레이션 (수정 후 기대 동작, capacity=3) ---
 *   초기: head=0, tail=0 (또는 -1), size=0
 *   insertLast(1):  tail=0 (또는 옮긴 뒤 0), arr=[1,_,_], size=1, head=tail=0
 *   insertLast(2):  tail=1, arr=[1,2,_], size=2
 *   insertFront(3): head=(0-1+3)%3=2, arr=[1,2,3], size=3
 *   getFront() → arr[2]=3 ✓
 *   getRear()  → arr[1]=2 ✓
 *   deleteLast(): tail=(1-1+3)%3=0, size=2
 *   insertFront(4): head=(2-1+3)%3=1, arr=[1,4,3]? 잠깐, arr[1]=4 → arr=[1,4,3], size=3
 *   getFront() → arr[1]=4 ✓
 *
 * --- 다음 단계 ---
 *   1) head/tail 의 의미를 종이에 한 줄씩 적기 (불변식).
 *   2) insert/delete 4 개 함수의 변수 사용을 일관되게 다시 짜기.
 *   3) 빈 deque 첫 insert 처리 (size==0 → size==1 전환 시 동기화) 결정.
 *   4) java -ea ... 통과 확인.
 */
class MyCircularDeque {

    private final int[] arr;
    private final int capacity;
    private int head, tail;
    private int size;


    public MyCircularDeque(int k) {
        arr = new int[k];
        capacity = k;
        size =0;
        head =0;
        tail = -1;
    }

    public boolean insertFront(int value) {
        if(isFull()) return false;

        head = (head -1 + capacity) % capacity;
        arr[head] = value;

        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if(isFull()) return false;

        tail = (tail + 1) % capacity;
        arr[tail] = value;

        size++;
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()) return false;

        head = (head + 1) % capacity;
        size--;

        return true;
    }

    public boolean deleteLast() {
        if(isEmpty()) return false;

        tail = (tail - 1 + capacity) % capacity;
        size--;

        return true;
    }

    public int getFront() {
        if(isEmpty()) return -1;
        return arr[head];
    }

    public int getRear() {
        if(isEmpty()) return -1;
        return arr[tail];
    }

    public boolean isEmpty() {
        return size ==0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        MyCircularDeque dq = new MyCircularDeque(3);
        assert dq.insertLast(1);
        assert dq.insertLast(2);
        assert dq.insertFront(3);
        assert !dq.insertFront(4);   // 가득 참
        assert dq.getRear() == 2;
        assert dq.isFull();
        assert dq.deleteLast();
        assert dq.insertFront(4);
        assert dq.getFront() == 4;

        // 빈 상태
        MyCircularDeque dq2 = new MyCircularDeque(2);
        assert dq2.isEmpty();
        assert dq2.getFront() == -1;
        assert dq2.getRear() == -1;
        assert !dq2.deleteFront();
        assert !dq2.deleteLast();

        System.out.println("✅ MyCircularDeque: All tests passed");
    }
}
