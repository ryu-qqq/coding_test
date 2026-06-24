import java.util.*;

/**
 * 환율 변환기 (실무 설계형)
 *
 * 문제: "USD:KRW:1390.50,..." 형식 환율 문자열을 파싱해, 두 통화 사이를 변환한다.
 *       직접 환율이 없으면 다른 통화를 경유(다단계)해서라도 변환한다.
 *
 * --- 인터페이스 ---
 *   CurrencyExchange(String rateData)
 *   double convert(String from, String to, double amount)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것 — 통화를 노드, 환율을 간선으로 본다면?)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것 — 역방향 환율, 누적 환율, BFS 방문 표시)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class CurrencyExchange {

    // TODO: 필요한 필드를 선언하세요 (통화 → 이웃 통화/환율)

    public CurrencyExchange(String rateData) {
        // TODO: 초기화 (정방향 + 역방향 등록)
        throw new UnsupportedOperationException("TODO");
    }

    public double convert(String from, String to, double amount) {
        // TODO: 구현 (다단계 경유 허용, 경로 없으면 예외)
        throw new UnsupportedOperationException("TODO");
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
            // TODO: 구현 (100만 미만 2%, 이상 1.5% 수수료)
            throw new UnsupportedOperationException("TODO");
        }
    }

    static class PromotionPolicy implements FeePolicy {

        @Override
        public Double applyFee(Double amount) {
            // TODO: 구현 (프로모션: 수수료 없음)
            throw new UnsupportedOperationException("TODO");
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
