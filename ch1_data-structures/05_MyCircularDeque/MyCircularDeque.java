import java.util.*;
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
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class MyCircularDeque {

    // TODO: 필요한 필드를 선언하세요

    public MyCircularDeque(int k) {
        // TODO: 초기화
    }

    public boolean insertFront(int value) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean insertLast(int value) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean deleteFront() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean deleteLast() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public int getFront() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public int getRear() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isFull() {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
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
