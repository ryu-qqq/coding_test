/**
 * JadenCase 문자열 만들기 (Programmers 12951, LV2)  [정답]
 *
 * 핵심: split(" ", -1) 로 공백을 보존(빈 토큰까지 유지)하며 쪼개고, 각 단어를
 *       "첫 글자 대문자 + 나머지 소문자" 로 변환 후 다시 공백으로 합친다.
 * 함정 포인트:
 *   - split(" ", -1) 의 -1 이라야 연속/끝 공백이 빈 토큰으로 보존됨 (기본 split 은 끝 빈토큰 제거).
 *   - 첫 글자가 숫자여도 toUpperCase 는 그대로 두므로 분기 불필요. 단 빈 단어는 charAt(0) 보호.
 * 복잡도: O(N). 자세한 해설 → SOLUTION.md
 */
class JadenCase {

    public String solution(String s) {
        StringBuilder sb =new StringBuilder();
        String[] arr = s.split(" ", -1);
        for(String word : arr){
            if(!word.isEmpty()){
                Character firstChar = word.charAt(0);
                Character c = Character.toUpperCase(firstChar);
                word = String.valueOf(c) + word.substring(1).toLowerCase();

            }
            sb.append(word);
            sb.append(" ");
        }

        return sb.toString().substring(0, sb.toString().length()-1);
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
