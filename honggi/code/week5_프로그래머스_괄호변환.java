import java.util.Stack;
class Solution {
    public String solution(String p) {
        // 1
        if (p.equals(""))
            return "";

        // 2
        String[] split = divideUV(p);
        return sortBracket(split[0], split[1]);
    }

    public String[] divideUV(String p){
        int lbc = 0, rbc = 0;
        String result = "";
        String remainS = "";
        for (int i = 0; i<p.length(); i++){
            char b = p.charAt(i);
            if (b == '(')
                lbc++;
            else
                rbc++;

            result += b;
            if (lbc == rbc){
                if (i != p.length()-1)
                    remainS += p.substring(i+1, p.length());
                break;
            }
        }
        return new String[]{result, remainS};
    }


    public String sortBracket(String u, String v){
        // 3
        if (isCorrectBracketString(u)){
            return u + solution(v);
        } else { // 4
            String result = "(" + solution(v) + ")";
            u = u.substring(1, u.length()-1);
            return result + reverseU(u);
        }
    }

    public boolean isCorrectBracketString(String bracket){
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i<bracket.length(); i++){
            if (stack.empty()){
                stack.push(bracket.charAt(i));
            } else {
                if (bracket.charAt(i) == '('){
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