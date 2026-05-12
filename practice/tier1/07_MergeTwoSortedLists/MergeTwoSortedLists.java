/**
 * LeetCode 21 - Merge Two Sorted Lists
 *
 * 문제: 오름차순 정렬된 두 연결 리스트를 하나로 병합한다.
 *
 * --- 인터페이스 ---
 *   ListNode mergeTwoLists(ListNode l1, ListNode l2)
 *
 * --- 시간복잡도 목표 ---
 *   시간 O(N + M), 공간 O(1) (반복 버전)
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
 *   ❌ NullPointerException at line 54 (`cur.next = cur1;` 에서 cur 이 null).
 *
 * --- 가장 큰 문제: `cur.next = ...` 대입이 없다 ---
 *
 *   너 코드의 분기:
 *       if (cur1.val >= cur2.val) {
 *           cur = cur2;          // ⚠️ cur 변수만 cur2 로 덮어쓰기
 *           cur2 = cur2.next;
 *       } else {
 *           cur = cur1;          // ⚠️ 마찬가지
 *           cur1 = cur1.next;
 *       }
 *       cur = cur.next;          // ⚠️ 그리고 또 cur 의 next 로 이동
 *
 *   문제 1: **dummy 와 첫 노드 사이의 연결이 만들어지지 않는다.**
 *     - 표준 패턴은 `cur.next = (작은 노드)` 로 dummy 의 꼬리에 노드를 *붙이는* 것.
 *     - 너 코드는 cur 변수를 다른 노드로 덮어쓰기만 함. 노드들 사이의 next 관계는 그대로 (원본 리스트 그대로).
 *     - 결과적으로 dummy.next 가 한 번도 세팅 안 됨 → return dummy.next = null 가능성.
 *
 *   문제 2: 추가로 `cur = cur.next;` 가 잘못된 방향으로 cur 을 이동시킨다.
 *     - 분기 안에서 cur = cur2 (예: L2[1]) 로 설정한 직후, cur = cur.next 하면 cur 은 L2[1].next = L2[3].
 *     - 즉 "방금 선택한 노드의 *다음 원본 노드*" 로 cur 이 점프. 이게 dummy 의 꼬리가 아님.
 *
 *   손 시뮬레이션 (l1=[1,2,4], l2=[1,3,4]):
 *     초기: cur1=L1[1], cur2=L2[1], cur=dummy
 *     iter 1: 1>=1 → if. cur=L2[1]. cur2=L2[3]. cur=cur.next=L2[3].
 *             ※ dummy.next 는 여전히 null! L2[1] 은 dummy 와 연결 안 됨.
 *     iter 2: 1>=3? no → else. cur=L1[1]. cur1=L1[2]. cur=cur.next=L1[2].
 *     iter 3: 2>=3? no → else. cur=L1[2]. cur1=L1[4]. cur=L1[4].
 *     iter 4: 4>=3? yes → if. cur=L2[3]. cur2=L2[4]. cur=L2[4].
 *     iter 5: 4>=4? yes → if. cur=L2[4]. cur2=null. cur=cur.next=null. (L2[4].next 가 null 이라)
 *     while 종료. cur=null, cur1=L1[4], cur2=null.
 *     if(cur1==null) → false
 *     if(cur2==null) cur.next = cur1  →  null.next = L1[4]  💥 NPE
 *
 * --- 표준 merge 패턴 (3 단계) ---
 *
 *   while (l1 != null && l2 != null):
 *     1) 작은 쪽 노드를 cur 의 꼬리에 *붙인다*  →  cur.next = (작은 노드)
 *     2) 작은 쪽 포인터를 한 칸 전진             →  l1 = l1.next  (또는 l2)
 *     3) cur 도 한 칸 전진                       →  cur = cur.next
 *
 *   while 종료 후:
 *     - 한쪽 리스트가 남아있다면 통째로 cur.next 에 연결  →  cur.next = (남은 쪽)
 *
 *   return dummy.next;
 *
 *   너 코드와의 차이는 단 하나:
 *     [너 코드]      cur = cur1;           // cur 변수를 cur1 으로 덮어쓰기 (연결 X)
 *     [표준 패턴]    cur.next = cur1;      // cur 의 꼬리에 cur1 을 *연결* (핵심!)
 *
 *   "변수 재할당" 과 "next 필드 대입" 의 차이를 정확히 구분해야 한다.
 *     - `cur = X`        →  내가 들고 있는 포인터를 X 로 바꾼다. 리스트 자체는 변화 없음.
 *     - `cur.next = X`   →  내가 가리키는 노드의 next 필드를 X 로 만든다. 리스트의 연결 구조가 바뀜.
 *
 * 🔴 결정적 오류:
 *
 *   1. `cur.next = cur1` (또는 cur2) 가 없다. (line 43, 46)
 *      → 위 설명대로 dummy 와의 연결이 끊김.
 *
 *   2. `cur = cur.next` 가 잘못된 자리. (line 50)
 *      → 표준 패턴에선 "방금 붙인 노드 자리로 cur 을 옮긴다" 역할.
 *      → 너 코드에선 "방금 선택한 노드의 *다음 원본 노드*" 로 점프. 의미가 다름.
 *
 *   3. 마지막 남은 거 연결의 if 2개. (line 53-54)
 *      → if-else if 가 더 명확. 지금 형태도 동작은 하지만, cur 이 null 일 가능성 (위 #1, #2 의 결과) 에서 NPE.
 *      → 또는 한 줄: `cur.next = (cur1 != null) ? cur1 : cur2;`
 *
 * 🟡 함정 / 엣지 케이스:
 *
 *   4. 비교 조건 `>=` 의 등호 처리. (line 42)
 *      → 양쪽 같은 값일 때 어느 쪽을 먼저 붙이는지의 차이. 결과는 동일하니 OK.
 *      → 단 안정성 (stable merge) 관점에선 `<=` 로 l1 우선이 일반적. 면접에선 어느 쪽이든 OK 지만 의도 설명 가능해야.
 *
 *   5. 둘 다 null 입력: while 안 들어감 → dummy.next=null → return null. ✓
 *   6. 한쪽만 null 입력: while 안 들어감 → if 분기에서 cur.next = (남은 쪽). cur=dummy 이므로 dummy.next 에 직결. ✓
 *      (지금 코드도 이 빈 가드 두 케이스는 통과한다 — 양쪽 다 있을 때만 깨짐)
 *
 * --- 핵심 통찰 (직접 답 X) ---
 *
 *   merge 의 본질:
 *     **dummy 라는 가짜 head 를 만들고, 그 꼬리에 작은 노드를 하나씩 *붙여나간다*.**
 *
 *   비유: 빈 봉투 (dummy) 가 있다. 두 더미 (l1, l2) 의 맨 위 카드를 비교해 작은 걸 봉투에 *집어넣는다*.
 *     - "집어넣는다" = `cur.next = (작은 노드)` + `cur = cur.next`
 *     - cur 은 봉투의 가장 마지막 카드를 가리키는 포인터 (= "다음 넣을 자리의 직전").
 *
 *   너 코드는 카드를 봉투에 안 넣고 그냥 손으로만 가리키고 있는 상태.
 *
 * --- 다음 단계 ---
 *   1) 위 핵심 통찰 종이에 적기.
 *   2) 표준 패턴 3 단계를 본인 표현으로 코드에 옮기기 (답 보지 말고).
 *   3) 손 시뮬레이션 l1=[1,2,4], l2=[1,3,4] 따라가서 dummy.next 가 1 로 시작하는지 확인.
 *   4) java -ea ... 통과 확인.
 */
class MergeTwoSortedLists {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    static class Solution {
        // 1 3
        // 2 4
        //  dummy -> 1  

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;

            while(l1 != null && l2 != null){

                if(l1.val >= l2.val){
                    cur.next = l2;
                    l2 = l2.next;
                }else{
                    cur.next = l1;
                    l1 = l1.next; //  1- > 3으로 이동
                }

                cur = cur.next; // dummy -> 1 -> 3
            }

            cur.next = l1 != null ? l1 : l2;
           
            return dummy.next;
        }
    }

    private static ListNode build(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : arr) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    private static int[] toArray(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;
        int[] arr = new int[n];
        int i = 0;
        for (ListNode p = head; p != null; p = p.next) arr[i++] = p.val;
        return arr;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        ListNode r1 = sol.mergeTwoLists(build(new int[]{1, 2, 4}), build(new int[]{1, 3, 4}));
        assert java.util.Arrays.equals(toArray(r1), new int[]{1, 1, 2, 3, 4, 4}) : "merge 124,134";

        ListNode r2 = sol.mergeTwoLists(null, null);
        assert r2 == null : "both null";

        ListNode r3 = sol.mergeTwoLists(null, build(new int[]{0}));
        assert java.util.Arrays.equals(toArray(r3), new int[]{0}) : "one null";

        ListNode r4 = sol.mergeTwoLists(build(new int[]{1, 5, 9}), build(new int[]{2}));
        assert java.util.Arrays.equals(toArray(r4), new int[]{1, 2, 5, 9}) : "interleave";

        System.out.println("✅ MergeTwoSortedLists: All tests passed");
    }
}
