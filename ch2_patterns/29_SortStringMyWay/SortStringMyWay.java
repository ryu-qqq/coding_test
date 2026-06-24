/**
 * 문자열 내 마음대로 정렬하기 (Programmers 12915, LV1)
 *
 * 문제: 각 문자열의 n번째 글자를 기준으로 오름차순 정렬. n번째 글자가 같으면 그들끼리는 사전순.
 *   ["sun","bed","car"], n=1 → ["car","bed","sun"]   (n=1 글자: 'u','e','a')
 *   ["abce","abcd","cdx"], n=2 → ["abcd","abce","cdx"]  ('c','c','x' → c 둘은 사전순)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것: Comparator 의 1차 키와 2차 키는 각각 무엇? 어떻게 체이닝?)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
import java.util.*;

class SortStringMyWay {

    public String[] solution(String[] strings, int n) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
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
