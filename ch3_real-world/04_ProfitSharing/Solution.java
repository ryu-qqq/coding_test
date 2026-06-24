import java.util.*;

/**
 * 수익 분배 (실무 설계형)  [정답]
 *
 * 문제: 투자자별 지분율(%)을 등록하고, 총 수익을 지분율대로 분배한다.
 *       정수 분배라 나누어떨어지지 않으면 남는 1원 단위를 지분 큰 순으로 분배한다.
 *
 * 핵심 아이디어: 1차로 floor(amount * rate / 100) 분배 → 남은 잔액을 지분 내림차순으로 1원씩.
 *   - 지분 합이 정확히 100 이어야 분배 가능(미달이면 예외).
 *   - 잔액 분배로 "합이 정확히 총액"이 되도록 보장.
 * 복잡도: register O(1), distribute O(N log N) (잔액 분배용 정렬).
 *
 * 설계 포인트:
 *   - 등록(register: 지분 검증)과 분배(distribute: 금액 계산)를 메서드로 분리.
 *     누적 지분 검증(100 초과 방지)을 등록 시점에 둬서 분배 단계를 단순하게 유지.
 * 자세한 해설 → SOLUTION.md
 */
class ProfitSharing {

    private Map<String, Integer> map = new HashMap<>();
    private Map<String, Integer> profitMap = new HashMap<>();
    private int sum = 0;

    public void register(String name, int percent) {

        if (map.containsKey(name)) {
            throw new IllegalStateException(String.format("이미 투자한 투자한 투자자입니다. %s", name));
        }

        if (percent > 100 || percent < 0) {
            throw new IllegalArgumentException(String.format("투자 비중은 0이상 100 이하 여야 합니다. %d", percent));
        }

        if (biggerThan100(percent)) {
            throw new IllegalArgumentException(String.format("누적 투자 비중은 100 을 넘을 수 없습니다."));
        }

        map.put(name, percent);
        sum += percent;
    }

    public boolean biggerThan100(int percent) {
        return 100 < sum + percent;
    }

    public boolean smallerThan100() {
        return 100 > sum;
    }

    public Map<String, Integer> distribute(int amount) {
        if (smallerThan100()) {
            throw new IllegalStateException(String.format("누적 투자합이 100 이하 입니다."));
        }
        int temp = amount;

        List<String> nameList = new ArrayList<>();

        for (String name : map.keySet()) {
            int rate = map.get(name);
            int profit = temp * rate / 100;

            profitMap.put(name, (int) profit);
            amount -= profit;
            nameList.add(name);
        }

        if (amount > 0) {
            // 남은 잔액(1원 단위)은 지분 큰 순으로 1원씩 분배
            nameList.sort(Comparator.comparing(s -> map.get(s)).reversed());
            while (amount > 0) {
                for (String name : nameList) {
                    if (!profitMap.containsKey(name)) {
                        throw new IllegalStateException(String.format("해당 투자자를 수익분배자에서 찾을 수 없습니다. %s", name));
                    }

                    profitMap.put(name, profitMap.getOrDefault(name, 0) + 1);
                    amount--;
                    if (amount <= 0) {
                        break;
                    }
                }
            }
        }

        return profitMap;
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
