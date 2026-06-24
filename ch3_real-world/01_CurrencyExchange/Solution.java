import java.util.*;

/**
 * 환율 변환기 (실무 설계형)  [정답]
 *
 * 문제: "USD:KRW:1390.50,..." 형식 환율 문자열을 파싱해, 두 통화 사이를 변환한다.
 *       직접 환율이 없으면 다른 통화를 경유(다단계)해서라도 변환한다.
 *
 * 핵심 아이디어: 통화를 노드, 환율을 간선으로 보는 "가중 그래프" + BFS 최단 경유 탐색.
 *   - 정방향(from→to: rate)과 역방향(to→from: 1/rate)을 둘 다 인접리스트에 넣어 양방향 그래프 구성.
 *   - convert 는 시작 통화에서 BFS 로 퍼지며 누적 환율(cur.rate * edge.rate)을 들고 다닌다.
 * 복잡도: 초기화 O(E), convert O(V+E). (V=통화 수, E=환율 항목 수의 2배)
 *
 * 설계 포인트:
 *   - 변환 정책(그래프 탐색)과 수수료 정책(FeePolicy)을 분리 → 수수료 규칙이 바뀌어도
 *     convert 는 안 건드린다. (전략 패턴)
 * 자세한 해설 → SOLUTION.md
 */
class CurrencyExchange {

    private Map<String, Set<Node>> rateMap = new HashMap<>();

    // 환율 문자열을 받아 양방향 그래프로 초기화
    public CurrencyExchange(String rateData) {
        String[] currenStrings = rateData.split(",");

        for (String word : currenStrings) {
            String[] currentString = word.split(":");

            String from = currentString[0];
            String to = currentString[1];
            Double rate = Double.valueOf(currentString[2]);

            // 정방향 + 역방향(1/rate) 동시 등록 → 어느 쪽에서 출발해도 경유 가능
            rateMap.computeIfAbsent(from, k -> new HashSet<>()).add(new Node(to, rate));
            rateMap.computeIfAbsent(to, k -> new HashSet<>()).add(new Node(from, 1 / rate));
        }
    }

    // from 통화 amount 를 to 통화로 변환 (다단계 경유 허용). 경로 없으면 예외.
    public double convert(String from, String to, double amount) {
        Queue<Node> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        if (from.equals(to)) {
            return amount;
        }

        Node node = new Node(from, 1.0);
        q.add(node);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            visited.add(cur.getCountry());
            Set<Node> nodes = rateMap.getOrDefault(cur.getCountry(), new HashSet<>());

            // 이웃 통화 순회
            for (Node n : nodes) {
                // 타겟 도달: 누적 환율(cur) * 마지막 간선(n) * 금액
                if (n.isTarget(to)) {
                    return cur.getRate() * n.getRate() * amount;
                }

                if (!visited.contains(n.getCountry())) {
                    visited.add(n.getCountry());
                    q.add(new Node(n.getCountry(), cur.getRate() * n.getRate()));
                }
            }
        }

        throw new IllegalStateException(String.format("환율 정보가 없습니다 %s", from));
    }

    class Node {

        private final String country;
        private final Double rate;

        public Node(String country, Double rate) {
            this.country = country;
            this.rate = rate;
        }

        String getCountry() {
            return country;
        }

        Double getRate() {
            return rate;
        }

        boolean isTarget(String to) {
            return country.equals(to);
        }
    }

    @FunctionalInterface
    interface FeePolicy {
        Double applyFee(Double amount);
    }

    static class DefaultPolicy implements FeePolicy {

        @Override
        public Double applyFee(Double amount) {
            if (amount < 1_000_000) return amount * 0.98;
            else return amount * 0.985;
        }
    }

    static class PromotionPolicy implements FeePolicy {

        @Override
        public Double applyFee(Double amount) {
            return amount;
        }
    }

    public static void main(String[] args) {
        String rateData = "USD:KRW:1390.50,USD:JPY:155.20,EUR:KRW:1510.00,GBP:USD:1.27";
        CurrencyExchange exchange = new CurrencyExchange(rateData);

        // 직접 환율
        assert exchange.convert("USD", "KRW", 100) == 139050.0 : "USD→KRW";
        assert exchange.convert("EUR", "KRW", 10) == 15100.0 : "EUR→KRW";
        assert exchange.convert("GBP", "USD", 1000) == 1270.0 : "GBP→USD";

        // 같은 통화 → 그대로
        assert exchange.convert("KRW", "KRW", 1000) == 1000.0 : "KRW→KRW";

        // 역방향 (KRW→USD = 1/1390.50)
        assert Math.abs(exchange.convert("KRW", "USD", 1390.50) - 1.0) < 1e-9 : "KRW→USD 역방향";

        // 다단계 경유 (USD→KRW→EUR, 기대값 ≈ 1390.5/1510)
        double usdEur = exchange.convert("USD", "EUR", 1000);
        assert Math.abs(usdEur - (1390.50 / 1510.00) * 1000) < 1e-6 : "USD→EUR 경유";

        // 경로 없는 통화 → 예외
        boolean threw = false;
        try {
            exchange.convert("USD", "BTC", 1);
        } catch (IllegalStateException e) {
            threw = true;
        }
        assert threw : "없는 통화는 예외여야 함";

        // 수수료 정책 분리 검증
        FeePolicy promo = new PromotionPolicy();
        assert promo.applyFee(1000.0) == 1000.0 : "프로모션은 수수료 0";
        FeePolicy def = new DefaultPolicy();
        assert def.applyFee(1000.0) == 1000.0 * 0.98 : "100만 미만 2%";
        assert def.applyFee(2_000_000.0) == 2_000_000.0 * 0.985 : "100만 이상 1.5%";

        System.out.println("✅ CurrencyExchange: All tests passed");
    }
}
