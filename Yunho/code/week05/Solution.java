// https://school.programmers.co.kr/learn/courses/30/lessons/60058
// 괄호 변환

/**
 * if (p == "") {  
 *          return "";
 *      }
 * 처음 1번 조건을 이렇게 했다가 계속 stackoverflow 에러가 발생하 원인파악하는데 시간이 오래 걸렸습니다.. 
 */
import java.util.*;
class Solution {
    public String solution(String p) {
        // 1단계
        if (p.isEmpty()) {  
            return "";
        }
        // 2단계
        int idx = split(p);  
        
        String u = p.substring(0, idx);
        String v = p.substring(idx);
        
        if (isCorrect(u)) { 
            return u + solution(v);
        } else { 
            String answer = "(" + solution(v) + ")";
            answer += change(u.substring(1, u.length() - 1));
            return answer;
        }
    }
    
    private int split(String p) {
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
    
    private boolean isCorrect(String p) {
        int count = 0;
        
        for (char c : p.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }
        
        return true;
    }
    
    private String change(String p) {
        char[] arr = p.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                arr[i] = ')';
            } else {
                arr[i] = '(';
            }
        }
        
        return String.valueOf(arr);
    }
}
