// https://school.programmers.co.kr/learn/courses/30/lessons/60058%5D
// 괄호 변환

/**
 * (남은)주어진 p가 "("로 시작하면 균형 잡힌 문자열이 될때까지 u에 저장 나머지는 v에 저장
 * (남은)주어진 P가  ")"로 시작하면 
 */
import java.util.*;
class Solution {
    public String solution(String p) {
        // 1. 빈문자열 반환
        if(p == ""){
            return p;
        }
        // 2. 문자열 분리 
        int idx = split(p);
        
        String u = p.substring(0, idx);
        String v = p.substring(idx);

        // 3. 4 수행 필요 
        String answer = "";
        return answer;
    }

    // 문자열 분리를 위한 함수 
    public int split(String p) {
        int a = 0;
        int b = 0;
        int idx = 0;
        
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                a++;
            } else {
                b++;
            }
            
            if (a == b) {
                idx = i + 1;
                break;
            }
        }
        
        return idx;
    }
}