// 문자열 검색 알고리즘
// https://school.programmers.co.kr/learn/courses/30/lessons/60058

public class Week5 {
    public static void main(String[] args) {
        System.out.println(solution("(()())()"));
        System.out.println(solution(")("));
        System.out.println(solution("()))((()"));
    }


    /*
    1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
    2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
    3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
        3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
    4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
        4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
        4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
        4-3. ')'를 다시 붙입니다.
        4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
        4-5. 생성된 문자열을 반환합니다.
     */


    // 올바른 괄호의 형태인지 검사하는 함수
    public static boolean checkCorrect (String u){
        int bracket_l = 0;
        int bracket_r = 0;

        for (int i=0; i<u.length(); i++){
            if (u.charAt(i) == '(') bracket_l++;
            else bracket_r++;
            if (bracket_r > bracket_l){  // 왼쪽 괄호보다 오른쪽 괄호의 갯수가 더 많다면, 올바르지 않은 형태
                return false;
            }
        }
        return true;
    }


    // 메인 함수
    public static String solution(String p) {

        if (p.length() == 0) return  "";  // 빈 문자열일 경우, 빈 문자 반환

        // 균형 잡힌 문자열 구하기 (a,b)
        int a = 0; // '('
        int b = 0; // ')'
        String u = ""; // 문자열 분해 1
        String v = ""; // 문자열 분해 2

        for (int i=0; i<p.length(); i++){
            if (p.charAt(i) == '(') a++;
            else b++;
            if (a==b) {
                u = p.substring(0,i+1);
                v = p.substring(i+1,p.length());
                break;
            }
        }

        // 대상 문자열이 올바른 문자열 (괄호 형태)인지 확인하기
        if (checkCorrect(u)){ // 올바른 문자열이라면
            return u + solution(v);
        }

        // 올바르지 않은 문자열이라면
        String x = "(" + solution(v) + ")";
        u = u.substring(1,u.length()-1);
        for (int i=0; i<u.length(); i++){
            if (u.charAt(i) == '(') x += ')';
            else x += '(';
        }
        return x;

    }
}
