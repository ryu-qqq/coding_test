/**
 * 신규 아이디 추천 (Programmers 72410, LV1)
 *
 * 문제: new_id 를 7단계 규칙대로 변환한 결과를 반환.
 *   1) 대문자 → 소문자
 *   2) 소문자/숫자/"-"/"_"/"." 빼고 모두 제거
 *   3) "." 가 2번 이상 연속되면 하나로 치환
 *   4) "." 가 처음이나 끝에 있으면 제거
 *   5) 빈 문자열이면 "a" 대입
 *   6) 길이 16 이상이면 첫 15자만, 그래도 끝이 "." 이면 제거
 *   7) 길이 2 이하이면 마지막 문자를 길이 3이 될 때까지 끝에 반복해서 붙임
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것)
 *
 * --- 정규식 포인트 ---
 *   (스스로 떠올려볼 것: 2단계의 "허용 글자가 아닌 것 제거", 3단계의 점 이스케이프와 수량자)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class NewIdRecommendation {

    public String solution(String new_id) {
        // TODO: 구현 (7단계를 순서대로)
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        NewIdRecommendation s = new NewIdRecommendation();
        assert s.solution("...!@BaT#*..y.abcdefghijklm").equals("bat.y.abcdefghi");
        assert s.solution("z-+.^.").equals("z--");
        assert s.solution("=.=").equals("aaa");
        assert s.solution("123_.def").equals("123_.def");
        assert s.solution("abcdefghijklmn.p").equals("abcdefghijklmn");

        System.out.println("✅ NewIdRecommendation: All tests passed");
    }
}
