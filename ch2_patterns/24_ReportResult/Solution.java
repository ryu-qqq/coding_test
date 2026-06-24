/**
 * 신고 결과 받기 (Programmers 92334, LV1)  [정답]
 *
 * 핵심: 2-pass. (1) "피신고자 → 신고자 Set" 집계로 중복 신고를 자동 제거,
 *       (2) k회 이상 신고당한(=정지된) 유저의 신고자들에게 메일 1통씩 카운트.
 * 자료구조: Map<String, Set<String>> (중복 신고 O(1) 제거), Map<String, Integer> (메일 수).
 * 복잡도: report N건에 대해 O(N) (Set 연산 평균 O(1)). 자세한 해설 → SOLUTION.md
 */
import java.util.*;

class ReportResult {

    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, Set<String>> countMap = new HashMap<>();

        for(String ids : report){
            String[] id = ids.split(" ");
            String shooter = id[0];     // 신고자
            String candidate = id[1];   // 피신고자
            // computeIfAbsent: 없으면 새 Set 을 만들어 map 에 넣고 그 Set 반환.
            // Set.add 는 중복이면 무시 → 같은 신고자의 중복 신고가 자동으로 1회 처리됨.
            countMap.computeIfAbsent(candidate, key -> new HashSet<>()).add(shooter);
        }

        Map<String, Integer> mailCntMap = new HashMap<>();

        for(String candidate : countMap.keySet()){
            Set<String> shooters = countMap.get(candidate);
            if(shooters.size()>=k){   // k회 이상 신고당함 → 정지
                for(String shooter: shooters){
                    // merge: 없으면 1, 있으면 기존값+1
                    mailCntMap.merge(shooter, 1, Integer::sum);
                }
            }
        }

        int[] results = new int[id_list.length];
        for(int i =0; i < id_list.length; i ++){
            results[i] = mailCntMap.getOrDefault(id_list[i], 0);
        }

        return results;
    }

    public static void main(String[] args) {
        ReportResult s = new ReportResult();

        String[] id1 = {"muzi", "frodo", "apeach", "neo"};
        String[] r1 = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        assert Arrays.equals(s.solution(id1, r1, 2), new int[]{2, 1, 1, 0});

        String[] id2 = {"con", "ryan"};
        String[] r2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        assert Arrays.equals(s.solution(id2, r2, 3), new int[]{0, 0});

        System.out.println("✅ ReportResult: All tests passed");
    }
}
