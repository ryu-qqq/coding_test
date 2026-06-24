# RemoveNthFromEnd (LC 19) — 해설

> 막히기 전에 골격(`RemoveNthFromEnd.java`)부터 직접 채워보고, 그래도 안 풀리면 이 문서를 펼쳐보세요.

## 문제
연결 리스트의 끝에서 N번째 노드를 제거한다.

## 핵심 직관 (1줄)
dummy를 둔 뒤 fast를 먼저 n칸 전진시켜 fast/slow 간격을 벌리고, `fast.next`가 null이 될 때까지 같이 가면 slow가 "삭제 대상의 직전".

## 자료구조
dummy head + 두 포인터(fast, slow).

## 알고리즘
1. `dummy.next = head`, `fast = slow = dummy`.
2. fast를 n번 전진(slow와 간격 n).
3. while `fast.next != null`: 둘 다 1칸 전진.
4. `slow.next = slow.next.next`로 삭제.
5. `dummy.next` 반환.

> 참고: fast를 n+1칸 전진시키고 `while(fast != null)`로 도는 변형도 동일하게 동작한다(둘 다 slow가 삭제 직전에 위치). 본 정답은 fast를 n칸 전진 후 `fast.next != null`로 멈추는 형태다.

## 불변식
loop를 빠져나오는 순간 slow는 삭제 대상의 직전 노드.

## 복잡도
시간 O(L) 한 패스, 공간 O(1).

## 함정 ⚠️
- dummy를 안 쓰면 head 자체가 삭제 대상인 경우(n == 리스트 길이) 분기 처리가 필요해진다.
- 간격을 잘못 잡아 slow가 삭제 대상 자체에 가버리면 단일 연결 리스트라 prev를 알 수 없어 삭제가 안 된다.

## 대안 / 최적화
길이 L을 먼저 구하고 (L-n)번째 직전에서 삭제하는 두 패스 풀이도 가능. 한 패스가 더 우아.

## 면접 답변 (한국어 1분)
> "한 번의 패스로 풀기 위해 dummy head와 fast/slow 두 포인터를 사용했습니다. fast를 먼저 n칸 전진시켜 두 포인터의 간격을 벌리고, fast.next가 null이 될 때까지 둘 다 같이 1칸씩 갑니다. 그 순간 slow가 정확히 삭제 대상의 직전 노드에 위치합니다. 그래서 slow.next = slow.next.next 한 줄로 삭제가 됩니다. dummy를 쓴 이유는 head 자체가 삭제 대상인 경우, 즉 n이 리스트 길이와 같을 때 분기 처리 없이 일관되게 처리하기 위함입니다. 시간은 O(L) 한 패스고 공간은 O(1)입니다."

## Follow-up
- **왜 두 포인터의 간격이 중요한가?** 단일 연결 리스트에서 노드를 삭제하려면 그 직전 노드를 알아야 한다. 간격을 적절히 두어 fast가 끝에 닿을 때 slow가 삭제 직전에 오게 만든다. slow가 삭제 대상 자체에 가면 prev를 알 수 없다.
- **두 패스로 풀면?** 길이 L을 구하고 (L-n)번째 직전까지 가서 삭제한다. 코드는 더 직관적이지만 패스가 두 번이라 한 패스 풀이가 더 우아하다.
