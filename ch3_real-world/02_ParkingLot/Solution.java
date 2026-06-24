import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 * 주차장 정산 시스템 (실무 설계형)  [정답]
 *
 * 문제: 차량 입·출차를 기록하고 주차 시간(분)과 요금을 계산한다. 시간은 "HH:MM"(당일).
 *
 * 핵심 아이디어: "현재 주차 중인 차"만 Map<차번호, 입차시각> 으로 추적.
 *   - enter: 이미 있으면 중복 입차 예외, 없으면 입차시각 기록.
 *   - exit: 없으면 예외, 있으면 Duration.between 으로 분 계산 후 Map 에서 제거.
 *   - 요금 계산은 Fee 라는 별도 정책 객체로 분리.
 * 복잡도: enter/exit O(1).
 *
 * 설계 포인트:
 *   - "주차 추적"(ParkingLot)과 "요금 정책"(Fee)을 분리. 요금표가 바뀌어도 입출차 로직은 불변.
 * 자세한 해설 → SOLUTION.md
 */
class ParkingLot {

    private Map<String, LocalTime> parkingMap = new HashMap<>();

    // 입차
    public void enter(String carNumber, String time) {
        if (parkingMap.containsKey(carNumber)) {
            throw new IllegalStateException(String.format("이미 존재하는 차 번호 입니다. %s", carNumber));
        }
        parkingMap.put(carNumber, tLocalTime(time));
    }

    // 출차 → 주차 시간(분) 반환
    public int exit(String carNumber, String time) {
        if (!parkingMap.containsKey(carNumber)) {
            throw new IllegalStateException(String.format("존재하지 않는 차 번호 입니다. %s", carNumber));
        }

        LocalTime exitTime = tLocalTime(time);
        LocalTime arriveTime = parkingMap.get(carNumber);

        parkingMap.remove(carNumber);

        return (int) Duration.between(arriveTime, exitTime).toMinutes();
    }

    public int calculateFee(int minues) {
        Fee fee = new Fee();
        return fee.settlement(minues);
    }

    private LocalTime tLocalTime(String time) {
        return LocalTime.parse(time);
    }

    static class Fee {

        private int fee;

        public Fee() {
            fee = 1000;
        }

        private int settlement(int minutes) {
            if (minutes <= 30) return fee;
            int spendingTime = Math.ceilDiv(minutes - 30, 10);
            fee = fee + spendingTime * 500;

            return Math.min(fee, 15000);
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
