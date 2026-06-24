/**
 * JadenCase 문자열 만들기 (Programmers 12951, LV2)
 *
 * 문제: 모든 단어의 첫 글자는 대문자, 나머지는 소문자로. 단어는 공백 1개로 구분.
 *   ⚠️ 공백이 연속되거나 문장이 공백으로 시작/끝날 수 있고, 그 공백은 보존되어야 한다.
 *   "3people unFollowed me" → "3people Unfollowed Me"
 *   "  hello   world  "     → "  Hello   World  "   (공백 보존)
 *
 * --- 핵심 아이디어 ---
 *   (스스로 떠올려볼 것: 공백을 어떻게 보존하며 쪼갤까? split 옵션?)
 *
 * --- 함정 ---
 *   (스스로 떠올려볼 것)
 *
 * 막히면 → SOLUTION.md, 정답 코드 → Solution.java
 */
class JadenCase {

    public String solution(String s) {
        // TODO: 구현
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        JadenCase sol = new JadenCase();

        assert sol.solution("3people unFollowed me").equals("3people Unfollowed Me");
        assert sol.solution("for the last week").equals("For The Last Week");
        assert sol.solution("  hello   world  ").equals("  Hello   World  ");
        assert sol.solution("FOR THE").equals("For The");
        assert sol.solution("3PEOPLE").equals("3people");

        System.out.println("✅ JadenCase: All tests passed");
    }
}
