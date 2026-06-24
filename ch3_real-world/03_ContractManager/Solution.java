import java.util.*;

/**
 * 부동산 계약 관리 (상태 머신, 실무 설계형)  [정답]
 *
 * 문제: 계약은 DRAFT → REVIEW → SIGNED → ACTIVE → CLOSED 순서로만 한 칸씩 전진한다.
 *       건너뛰기/뒤로가기/종료 후 변경은 금지.
 *
 * 핵심 아이디어: 상태를 enum 으로 표현하고, "다음 상태"를 enum 자신이 알게(next()) 한다.
 *   - CLOSED 에서 next() 는 예외 → 전이 규칙이 enum 안에 캡슐화되어 Manager 가 단순해진다.
 * 복잡도: create/advance/getStatus 모두 O(1).
 *
 * 설계 포인트:
 *   - 전이 규칙(상태 머신)을 enum 으로, 계약 저장/조회를 Manager 로 분리.
 *     전이 순서가 바뀌어도 Manager 코드는 그대로, enum.next() 만 고친다.
 * 자세한 해설 → SOLUTION.md
 */
class ContractManager {

    private Map<String, ContractStatus> map = new HashMap<>();

    public void createContract(String id) {
        if (map.containsKey(id)) {
            throw new IllegalArgumentException(String.format("이미 존재하는 계약 입니다. %s, 현재 계약 상태 %s", id, map.get(id).toString()));
        }

        map.put(id, ContractStatus.DRAFT);
    }

    public void advance(String id) {
        if (!map.containsKey(id)) {
            throw new IllegalArgumentException(String.format("존재하지 않는 계약 아이디 입니다. %s", id));
        }

        ContractStatus cur = map.get(id);
        ContractStatus next = cur.next();
        map.put(id, next);
    }

    public String getStatus(String id) {
        if (!map.containsKey(id)) {
            throw new IllegalArgumentException(String.format("존재하지 않는 계약 아이디 입니다. %s", id));
        }
        return map.get(id).toString();
    }

    enum ContractStatus {
        DRAFT, REVIEW, SIGNED, ACTIVE, CLOSED;

        ContractStatus next() {
            return switch (this) {
                case DRAFT -> REVIEW;
                case REVIEW -> SIGNED;
                case SIGNED -> ACTIVE;
                case ACTIVE -> CLOSED;
                case CLOSED -> throw new IllegalStateException("이미 종료된 계약 입니다.");
            };
        }
    }

    public static void main(String[] args) {
        ContractManager cm = new ContractManager();

        cm.createContract("C-001");
        assert cm.getStatus("C-001").equals("DRAFT") : "초기 상태 DRAFT";
        cm.advance("C-001");
        assert cm.getStatus("C-001").equals("REVIEW") : "REVIEW";
        cm.advance("C-001");
        assert cm.getStatus("C-001").equals("SIGNED") : "SIGNED";

        // 없는 계약 advance → 예외
        boolean noId = false;
        try {
            cm.advance("A-001");
        } catch (IllegalArgumentException e) {
            noId = true;
        }
        assert noId : "없는 계약은 예외";

        // 끝까지 진행 후 CLOSED 에서 또 advance → 예외
        cm.createContract("B-001");
        cm.advance("B-001"); // REVIEW
        cm.advance("B-001"); // SIGNED
        cm.advance("B-001"); // ACTIVE
        cm.advance("B-001"); // CLOSED
        assert cm.getStatus("B-001").equals("CLOSED") : "CLOSED 도달";
        boolean closedAdv = false;
        try {
            cm.advance("B-001");
        } catch (IllegalStateException e) {
            closedAdv = true;
        }
        assert closedAdv : "CLOSED 에서 advance 는 예외";

        // 중복 생성 → 예외
        boolean dup = false;
        try {
            cm.createContract("C-001");
        } catch (IllegalArgumentException e) {
            dup = true;
        }
        assert dup : "중복 생성은 예외";

        System.out.println("✅ ContractManager: All tests passed");
    }
}
