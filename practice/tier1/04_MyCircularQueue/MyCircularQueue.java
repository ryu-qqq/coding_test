/**
 * LeetCode 622 - Design Circular Queue
 *
 * 문제: 고정 용량의 원형 큐. 다 차면 새 원소 거부.
 *
 * --- 인터페이스 ---
 *   enQueue(value): boolean   (성공 시 true)
 *   deQueue(): boolean        (성공 시 true)
 *   Front(): int              (-1 if empty)
 *   Rear(): int               (-1 if empty)
 *   isEmpty(): boolean
 *   isFull(): boolean
 *
 * --- 시간복잡도 목표 ---
 *   모든 연산 O(1)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 초기화 트릭 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * ====================================
 * === 채점 결과 (2026-05-11) ===
 * ====================================
 *
 * --- 실행 결과 ---
 *   ❌ ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 3
 *      at Rear(line 67), 첫 enQueue 3번 후 Rear() 호출에서 즉시 사망.
 *
 * --- 가장 큰 문제: 불변식이 안 잡혔다 ---
 *   "--- 불변식 ---" 섹션이 비어있는 게 우연이 아니다. head 와 tail 이 각각 "무엇을 가리키는지"
 *   정의가 흐릿한 상태에서 코드를 쓰니까 곳곳에서 어긋난다.
 *
 *   먼저 종이에 적어봐:
 *     "head 는 [             ] 의 인덱스다."
 *     "tail 은 [             ] 의 인덱스다."
 *   빈칸 채우고 나서 코드를 다시 읽으면 어느 줄이 정의에 어긋나는지 보일 거다.
 *
 *   표준 정의 (이걸 따르길 권장):
 *     head = "가장 오래된 원소" 의 인덱스. → Front() 가 반환할 값의 위치.
 *     tail = "가장 최근에 enQueue 된 원소" 의 인덱스. → Rear() 가 반환할 값의 위치.
 *     size = 현재 원소 개수. (capacity 와 별개로 추적)
 *
 *   그러면:
 *     enQueue: tail 을 다음 칸으로 이동 (mod capacity) 후 arr[tail] = value. head 는 안 건드림.
 *     deQueue: head 를 다음 칸으로 이동 (mod capacity). tail 은 안 건드림.
 *     Front:   arr[head]
 *     Rear:    arr[tail]
 *
 *   너 코드는 이 매트릭스가 거꾸로 또는 빠져있다 — 아래 🔴 들이 다 그 결과.
 *
 * 🔴 결정적 오류:
 *
 *   2. enQueue 가 tail 을 건드리지 않는다. (line 40-48)
 *      → 표준 정의대로면 enQueue 시 tail 이 이동해야 한다.
 *      → 너 코드는 head 를 증가시키고 있는데, head 의 의미가 무엇인지 다시 따져봐.
 *      → head 가 "가장 오래된 원소 인덱스" 라면 enQueue 와 무관해야 한다.
 *      → head 가 "다음 enQueue 자리" 라면 deQueue 와 Front() 는 어떻게 동작해야 하나?
 *      → 어느 컨벤션을 쓰든 일관성이 무너지면 안 됨. 너 코드는 두 컨벤션이 섞여있다.
 *
 *   3. Rear() 가 ArrayIndexOutOfBoundsException 으로 사망. (line 67 + 생성자 line 36)
 *      → tail 이 초기값 -1 인데, enQueue 후에도 그대로 -1. arr[-1] → 폭발.
 *      → #2 의 직접적인 결과.
 *      → 손 시뮬레이션: enQueue(1) 한 직후 Rear() 부르면 무엇이 반환돼야 하나? 1 이지?
 *        그런데 너 코드에서 arr[tail] = arr[-1] 이 1 일 수 있나?
 *
 *   4. Front() 가 잘못된 인덱스를 본다. (line 62)
 *      → 너 코드의 enQueue 는 `arr[head] = value; head++;` 이다.
 *        즉 enQueue(1) 후 head=1, arr[0]=1.
 *      → 이 상태에서 Front() = arr[head] = arr[1] → 0 (초기값) 반환. 1 이 아님.
 *      → head 를 "다음 자리" 로 쓰고 있는데, Front 는 "현재 자리" 의 값을 원함. 같은 변수에 두 의미가 충돌.
 *
 *   5. Front() / Rear() 의 빈 처리가 명세와 다르다. (line 61, 66)
 *      → 명세 (line 9-10): "-1 if empty". 코드: IllegalStateException 던짐.
 *      → main 테스트도 `q2.Front() == -1` 을 기대 → 예외 던지면 테스트 깨짐.
 *      → MinStack/Queue 처럼 예외 격상하는 게 일반적으로는 더 좋은 패턴이지만, 이 문제만큼은 명세 따라가야.
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   6. head/tail 변수명 자체는 한국 컨벤션 따라 OK 이지만, 너가 사용한 의미는 반대 (또는 혼합) 다.
 *      → "head 는 enQueue 자리, tail 은 deQueue 대상" 으로 정해놓고 일관되게 가도 동작은 됨.
 *      → 다만 가독성을 위해 표준 (head=front, tail=rear) 따르는 것 권장.
 *
 * 🟢 개선 가능:
 *
 *   7. capacity 필드를 따로 두기보다 arr.length 를 직접 써도 된다. (final int[] 이므로 immutable)
 *      → 중복 상태 → 어긋날 가능성 (지금은 안 어긋났지만).
 *
 * --- 권장 재구성 (백지에서 다시 짤 때 가이드) ---
 *
 *   필드:
 *     int[] arr; int capacity; int head=0; int tail=-1; int size=0;
 *     (또는 tail=0 으로 시작하고 enQueue 에서 먼저 쓰고 후증가 — 둘 다 가능, 단 일관성 유지)
 *
 *   enQueue(v):
 *     1) isFull → return false
 *     2) tail = (tail+1) % capacity
 *     3) arr[tail] = v
 *     4) size++
 *     5) return true
 *
 *   deQueue():
 *     1) isEmpty → return false
 *     2) head = (head+1) % capacity
 *     3) size--
 *     4) return true
 *
 *   Front(): isEmpty → -1; else arr[head]
 *   Rear():  isEmpty → -1; else arr[tail]
 *
 *   불변식 (반드시 적어두기):
 *     - 0 <= size <= capacity
 *     - size > 0 이면, head 는 가장 오래된 원소 인덱스, tail 은 가장 최근 원소 인덱스.
 *     - size == 0 이면 head/tail 값은 의미 없음 (Front/Rear 가 -1 반환).
 *
 * --- 손 시뮬레이션 (capacity=3) ---
 *   초기: head=0, tail=-1, size=0
 *   enQueue(1): tail=(-1+1)%3=0, arr=[1,_,_], size=1
 *   enQueue(2): tail=1, arr=[1,2,_], size=2
 *   enQueue(3): tail=2, arr=[1,2,3], size=3 (full)
 *   Rear() → arr[2]=3 ✓
 *   deQueue(): head=(0+1)%3=1, size=2
 *   enQueue(4): tail=(2+1)%3=0, arr=[4,2,3], size=3 (wrap-around 발생!)
 *   Rear() → arr[0]=4 ✓
 *   Front() → arr[1]=2 ✓
 *
 *   ↑ 이 시뮬레이션이 머리 속에서 막힘 없이 도는지 손으로 한 번 따라가봐.
 *   특히 wrap-around (4번째 enQueue 가 0번 자리에 덮어쓰기) 가 핵심.
 *
 * --- 다음 단계 ---
 *   1) 위 "권장 재구성" 보지 말고, 종이에 불변식 먼저 적기.
 *   2) 백지에서 다시 코드 짜기 (덮어쓰기).
 *   3) java -ea ... 통과 확인.
 *   4) 막히면 다시 채점 요청.
 */
class MyCircularQueue {

    private final int[] arr;
    private final int capacity;
    private int head, tail, size;

    public MyCircularQueue(int k) {
        arr = new int[k];
        capacity = k;
        head = 0;
        tail = -1;
        size = 0;
    }

    public boolean enQueue(int value) {
        if(isFull()) return false;
        tail = (tail + 1) % capacity;
        arr[tail] = value;
        size++;

        return true;
    }
    // tail  head
    //      [ ()  () ]
    //
    //      [  1  () ]
    public boolean deQueue() {
        if(isEmpty()) return false;
        head = (head + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        if(isEmpty()) return -1;
        return arr[head];
    }

    public int Rear() {
        if(isEmpty()) return -1;
        return arr[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        MyCircularQueue q = new MyCircularQueue(3);
        assert q.enQueue(1);
        assert q.enQueue(2);
        assert q.enQueue(3);
        assert !q.enQueue(4);          // 가득 참
        assert q.Rear() == 3;
        assert q.isFull();
        assert q.deQueue();
        assert q.enQueue(4);            // 회전
        assert q.Rear() == 4;
        assert q.Front() == 2;

        // 빈 상태
        MyCircularQueue q2 = new MyCircularQueue(2);
        assert q2.Front() == -1;
        assert q2.Rear() == -1;
        assert !q2.deQueue();

        System.out.println("✅ MyCircularQueue: All tests passed");
    }
}
