import java.util.*;

/**
 * 수익 분배 (실무 설계형)
 *
 * 문제: 투자자별 지분율(%)을 등록하고, 총 수익을 지분율대로 분배한다.
 *       정수 분배라 나누어떨어지지 않으면 남는 1원 단위를 지분 큰 순으로 분배한다.
 *
 * --- 인터페이스 ---
 *   void                  register(String name, int percent)
 *   Map<String, Integer>  distribute(int amount)
 *   void                  clear()
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것 — 1차 floor 분배 후 남는 잔액은 어떻게 나눌까?)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것 — 분배 결과의 합은?)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것 — 중복 등록 / 누적 100 초과 / 100 미만 분배 / 나머지 처리)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class ProfitSharing {

    // TODO: 필요한 필드를 선언하세요 (이름→지분, 이름→분배액, 누적 지분합)
    private Map<String, Integer> map = new HashMap<>();
    private Map<String, Integer> profitMap = new HashMap<>();
    private int sum = 0;

    public void register(String name, int percent) {
        // TODO: 구현 (중복 등록 / 0~100 범위 / 누적 100 초과 검증 후 등록)
        throw new UnsupportedOperationException("TODO");
    }

    public boolean biggerThan100(int percent) {
        // TODO: 구현 (sum + percent 가 100 초과인가)
        throw new UnsupportedOperationException("TODO");
    }

    public boolean smallerThan100() {
        // TODO: 구현 (현재 누적 지분합이 100 미만인가)
        throw new UnsupportedOperationException("TODO");
    }

    public Map<String, Integer> distribute(int amount) {
        // TODO: 구현 (지분 합 100 미만이면 예외, floor 분배 후 잔액은 지분 큰 순 1원씩)
        throw new UnsupportedOperationException("TODO");
    }

    public void clear() {
        map.clear();
        profitMap.clear();
        sum = 0;
    }

    public static void main(String[] args) {
        ProfitSharing ps = new ProfitSharing();

        // 정상: Bob 60 / Alice 40, 총 100만 → 정확히 떨어짐
        ps.register("Bob", 60);
        ps.register("Alice", 40);
        Map<String, Integer> r1 = ps.distribute(1_000_000);
        assert r1.get("Bob") == 600_000 : "Bob 60%";
        assert r1.get("Alice") == 400_000 : "Alice 40%";
        assert r1.values().stream().mapToInt(i -> i).sum() == 1_000_000 : "합 = 총액";
        ps.clear();

        // 중복 등록 → 예외
        boolean dup = false;
        try {
            ps.register("Bob", 60);
            ps.register("Bob", 30);
        } catch (IllegalStateException e) {
            dup = true;
        }
        assert dup : "중복 투자자는 예외";
        ps.clear();

        // 누적 지분 100 초과 → 예외
        boolean over = false;
        try {
            ps.register("Bob", 60);
            ps.register("Alice", 50);
        } catch (IllegalArgumentException e) {
            over = true;
        }
        assert over : "누적 100 초과는 예외";
        ps.clear();

        // 지분 합 100 미만에서 분배 → 예외
        boolean under = false;
        try {
            ps.register("Bob", 60);
            ps.register("Alice", 30);
            ps.distribute(1_000_000);
        } catch (IllegalStateException e) {
            under = true;
        }
        assert under : "지분 합 100 미만 분배는 예외";
        ps.clear();

        // 안 떨어지는 케이스: 33/35/32, 총 10 → floor 3/3/3 = 9, 잔액 1을 최대 지분(Alice 35)에게
        ps.register("Bob", 33);
        ps.register("Alice", 35);
        ps.register("SAM", 32);
        Map<String, Integer> r2 = ps.distribute(10);
        assert r2.values().stream().mapToInt(i -> i).sum() == 10 : "잔액 포함 합 = 10";
        assert r2.get("Alice") == 4 : "잔액 1원은 최대 지분(Alice)에게";
        assert r2.get("Bob") == 3 && r2.get("SAM") == 3 : "나머지는 floor 그대로";
        ps.clear();

        System.out.println("✅ ProfitSharing: All tests passed");
    }
}
