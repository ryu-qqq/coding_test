/**
 * 신규 아이디 추천 (Programmers 72410, LV1)  [정답]
 *
 * 핵심: 문제의 7단계 규칙을 "순서 그대로" 절차적으로 옮긴다. 1~4단계는 정규식 replaceAll
 *       한 줄씩으로, 5~7단계는 length 체크 + if 로 처리한다.
 * 정규식 포인트:
 *   - 2단계 [^a-z0-9_.-] : character class 맨 앞 ^ = 부정(여집합). "허용 글자가 아닌 것"을 제거.
 *   - 3단계 \\.{2,}      : \\. 은 진짜 점(. 은 메타문자라 이스케이프), {2,} 는 "2번 이상".
 * 복잡도: O(N) (N = new_id 길이). 자세한 해설 → SOLUTION.md
 */
class NewIdRecommendation {

    public String solution(String new_id) {
        // 1단계: 모든 대문자를 소문자로
        String word = new_id.toLowerCase();

        // 2단계: 소문자, 숫자, "-", "_", "." 빼고 모두 제거 (허용 글자가 "아닌 것"을 제거)
        word = word.replaceAll("[^a-z0-9_.-]", "");

        // 3단계: "." 가 2번 이상 연속되면 하나로 치환 (\\. = 진짜 점, {2,} = 2회 이상)
        word = word.replaceAll("\\.{2,}", ".");

        // 4단계: "." 가 처음이나 끝에 있으면 제거
        if(word.startsWith(".")){
            word = word.substring(1);
        }

        if(word.endsWith(".")){
            word = word.substring(0, word.length()-1);
        }

        // 5단계: 빈 문자열이면 "a" 대입
        if(word.isEmpty()){
            word = "a";
        }

        // 6단계: 길이가 16 이상이면 첫 15자만 남기고, 그래도 끝이 "." 이면 제거
        if(word.length() >= 16){
            word = word.substring(0, 15);
            if(word.endsWith(".")){
                word = word.substring(0, word.length()-1);
            }
        }

        // 7단계: 길이가 2 이하이면 마지막 문자를 길이 3이 될 때까지 반복해서 끝에 붙임
        if(word.length() <=2){
            int len = word.length();
            Character lastChar = word.charAt(len- 1);
            while(len < 3){
                word += String.valueOf(lastChar);
                len++;
            }
        }

        return word;
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
