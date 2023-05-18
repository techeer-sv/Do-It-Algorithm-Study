import java.util.*;
/*
주어진 1~4번까지 순서대로 구현
*/

class Solution {
    public String solution(String p) {
        return bracket(p);
    }

    public String bracket(String w) {
        // 1번
        if (w.isEmpty()) {
            return "";
        }

        //2번
        int lb = 0;
        int rb = 0;
        int same = 0;
        String u = "";
        String v = "";

        char[] chr = w.toCharArray();

        for (int i = 0; i < chr.length; i++) {
            if (chr[i] == '(') {
                lb++;
            } else {
                rb++;
            }
            if (lb == rb) {
                same = i;
                break;
            }
        }

        u = w.substring(0, same + 1);
        v = w.substring(same + 1);

        //3번
        if (three(u)) {
            return u + bracket(v);
        // u가 올바른 괄호 문자열이 아닐 경우, v에 대해 재귀 사용(bracket()) -> v 처리 -> u의
        // 내부 문자열을 뒤집어서 외부 괄호와 결합 -> 반환 
        } else {
            StringBuilder result = new StringBuilder();
            result.append("(");
            result.append(bracket(v));
            result.append(")");
            result.append(reverse(u.substring(1, u.length() - 1)));
            return result.toString();
        }
    }

    // 3번 올바른 괄호 문자열 검사
    public boolean three(String u) {
        int lb = 0;
        int rb = 0;
        char[] chr = u.toCharArray();

        for (int i = 0; i < chr.length; i++) {
            if (chr[i] == '(') {
                lb++;
            } else {
                rb++;
            }
            if (rb > lb) {
                return false;
            }
        }
        return true;
    }

    //4번 괄호 뒤집기
    public String reverse(String u) {
        StringBuilder build = new StringBuilder();

        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                build.append(')');
            } else {
                build.append('(');
            }
        }
        return build.toString();
    }
}
