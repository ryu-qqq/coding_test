/**
 * 문자열 내 마음대로 정렬하기 (Programmers 12915, LV1)  [정답]
 *
 * 핵심: 두 단계 비교 Comparator. 1차 키 = n번째 글자(오름차순), 동점이면 2차 키 = 문자열
 *       전체의 사전순. Comparator.comparingInt(...).thenComparing(naturalOrder()) 체이닝.
 * 복잡도: 정렬 O(M log M) (M = 배열 길이). 자세한 해설 → SOLUTION.md
 */
import java.util.*;

class SortStringMyWay {

    public String[] solution(String[] strings, int n) {

        Arrays.sort(strings,
            Comparator.comparingInt((String s) -> s.charAt(n))
            .thenComparing(Comparator.naturalOrder())
        );

        return strings;
    }

    public static void main(String[] args) {
        SortStringMyWay s = new SortStringMyWay();

        assert Arrays.equals(
            s.solution(new String[]{"sun", "bed", "car"}, 1),
            new String[]{"car", "bed", "sun"});

        assert Arrays.equals(
            s.solution(new String[]{"abce", "abcd", "cdx"}, 2),
            new String[]{"abcd", "abce", "cdx"});

        System.out.println("✅ SortStringMyWay: All tests passed");
    }
}
