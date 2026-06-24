import java.util.*;

/**
 * 부동산 계약 관리 (상태 머신, 실무 설계형)
 *
 * 문제: 계약은 DRAFT → REVIEW → SIGNED → ACTIVE → CLOSED 순서로만 한 칸씩 전진한다.
 *       건너뛰기/뒤로가기/종료 후 변경은 금지.
 *
 * --- 인터페이스 ---
 *   void   createContract(String id)   // 초기 상태 DRAFT
 *   void   advance(String id)          // 다음 단계로 한 칸. 불가하면 예외.
 *   String getStatus(String id)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것 — 상태를 무엇으로 표현하고, "다음 상태"는 누가 알게 할까?)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것 — CLOSED 에서 advance / 없는 id / 중복 생성)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class ContractManager {

    // TODO: 필요한 필드를 선언하세요 (계약 id → 상태)

    public void createContract(String id) {
        // TODO: 구현 (중복 생성이면 예외)
        throw new UnsupportedOperationException("TODO");
    }

    public void advance(String id) {
        // TODO: 구현 (없는 id / CLOSED 면 예외)
        throw new UnsupportedOperationException("TODO");
    }

    public String getStatus(String id) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    enum ContractStatus {
        DRAFT, REVIEW, SIGNED, ACTIVE, CLOSED;

        ContractStatus next() {
            // TODO: 구현 (다음 상태 반환, CLOSED 면 예외)
            throw new UnsupportedOperationException("TODO");
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
