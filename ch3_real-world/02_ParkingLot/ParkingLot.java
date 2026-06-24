import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 * 주차장 정산 시스템 (실무 설계형)
 *
 * 문제: 차량 입·출차를 기록하고 주차 시간(분)과 요금을 계산한다. 시간은 "HH:MM"(당일).
 *
 * --- 인터페이스 ---
 *   void enter(String carNumber, String time)
 *   int  exit(String carNumber, String time)   // 주차 시간(분) 반환
 *   int  calculateFee(int minutes)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것 — "지금 주차 중인 차"를 어떻게 추적?)
 *
 * --- 불변식 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것 — 없는 차 출차 / 중복 입차 / 30분 경계 / 요금 상한)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class ParkingLot {

    // TODO: 필요한 필드를 선언하세요 (차번호 → 입차시각)

    public void enter(String carNumber, String time) {
        // TODO: 구현 (중복 입차면 예외)
        throw new UnsupportedOperationException("TODO");
    }

    public int exit(String carNumber, String time) {
        // TODO: 구현 (없는 차면 예외, 주차 분 반환)
        throw new UnsupportedOperationException("TODO");
    }

    public int calculateFee(int minutes) {
        // TODO: 구현 (Fee 정책에 위임)
        throw new UnsupportedOperationException("TODO");
    }

    static class Fee {

        private int fee;

        public Fee() {
            fee = 1000;
        }

        private int settlement(int minutes) {
            // TODO: 구현 (30분까지 1000, 초과분 10분당 500, 상한 15000)
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();

        // 정상: 90분 → 기본 1000 + 30분 초과분(60분)을 10분당 500 → 6*500 = 4000
        lot.enter("12가3456", "09:30");
        int min1 = lot.exit("12가3456", "11:00");
        assert min1 == 90 : "90분";
        assert lot.calculateFee(min1) == 4000 : "90분 요금 4000";

        // 30분 이하 → 기본 1000
        lot.enter("34나5678", "10:00");
        int min2 = lot.exit("34나5678", "10:15");
        assert min2 == 15 : "15분";
        assert lot.calculateFee(min2) == 1000 : "15분 요금 1000";

        // 없는 차 출차 → 예외
        boolean noCar = false;
        try {
            lot.exit("99호9999", "12:00");
        } catch (IllegalStateException e) {
            noCar = true;
        }
        assert noCar : "없는 차 출차는 예외";

        // 중복 입차 → 예외
        lot.enter("11가1111", "08:00");
        boolean dup = false;
        try {
            lot.enter("11가1111", "09:00");
        } catch (IllegalStateException e) {
            dup = true;
        }
        assert dup : "중복 입차는 예외";

        // 경계: 31분 → 30분 초과 1분 → ceilDiv(1,10)=1 → 1000 + 500 = 1500
        lot.enter("22나2222", "10:00");
        int min3 = lot.exit("22나2222", "10:31");
        assert min3 == 31 : "31분";
        assert lot.calculateFee(min3) == 1500 : "31분 요금 1500";

        // 상한: 아주 오래 → 최대 15000 으로 캡
        lot.enter("33다3333", "00:00");
        int min4 = lot.exit("33다3333", "23:59");
        assert lot.calculateFee(min4) == 15000 : "요금 상한 15000";

        System.out.println("✅ ParkingLot: All tests passed");
    }
}
