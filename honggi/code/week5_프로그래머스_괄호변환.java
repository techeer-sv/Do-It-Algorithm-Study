import java.util.Stack;

class Solution {
    public String solution(String p) {
        // 1
        if (p.equals(""))
            return "";
        // 2
        String[] split = makeU(p);
        return sortBracket(split[0], split[1]);
    }
    // u - 균형잡힌 괄호 문자열 (괄호 짝이 맞아야 함), v - p에서 u를 제외한 나머지
    public String sortBracket(String u, String v){
        System.out.println(u +" "+ v);

        // 3
        if (isRightBracketString(u)){
            if (v.equals("")){
                return processFour(u, "");
            }
            else{
                String[] split = makeU(v);
                return u + sortBracket(split[0], split[1]);
            }
        } else { // 4
            return processFour(u,v);
        }
    }

    public String processFour(String u, String v){
        String result = "(";
        if (!v.equals("")){
            String[] split = makeU(v);
            result += sortBracket(split[0], split[1]);
        }

        result += ")";
        u = u.substring(1, u.length()-1);
        return result + reverseU(u);
    }

    public String[] makeU(String p){
        int lbc = 0, rbc = 0;
        String result = "";
        int remainI = p.length();
        for (int i = 0; i<p.length(); i++){
            char b = p.charAt(i);
            if (b == '(')
                lbc++;
            else
                rbc++;

            result += b;
            if (lbc == rbc){
                remainI = i+1;
                break;
            }
        }

        if (p.equals("") || remainI >= p.length()-1)
            return new String[]{result, ""};
        else{
            return new String[]{result, p.substring(remainI)};
        }
    }

    // 올바른 괄호 문자열인지 검사
    public boolean isRightBracketString(String bracket){
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i<bracket.length(); i++){
            if (stack.empty()){
                stack.push(bracket.charAt(i));
            } else {
                if (stack.peek().equals(bracket.charAt(i))){
                    stack.push(bracket.charAt(i));
                } else{
                    stack.pop();
                }
            }
        }
        return stack.empty();
    }

    public String reverseU(String u){
        String result = "";
        for (int i = 0; i<u.length(); i++){
            if (u.charAt(i) == '(')
                result += ")";
            else
                result += "(";
        }
        return result;
    }
}