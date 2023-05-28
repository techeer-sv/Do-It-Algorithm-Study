import java.util.*;

class Solution {
    public int solution(String s) {

        int answer = s.length();

        for (int i = 1; i <= s.length()/2; i++) {
            String compare = s.substring(0,i); //비교기준
            int count = 1; //compare수
            String str = compare;
            for (int j = i; j < s.length();j+=i) {
                int lastIndex = Math.min(j+i, s.length());
                String tmp = s.substring(j,lastIndex);
                
                if(tmp.equals(compare))
                {
                    count++;
                }else {
                    if(count!=1) {
                        str+=count;
                    }
                    str+=tmp;
                    compare = tmp;
                    count = 1;
                }
            }

            if(count!=1) {
                str+=count;
            }
            answer = Math.min(answer, str.length());
        }
        return answer;
    }
}