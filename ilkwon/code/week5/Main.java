import java.util.*;
/*
주어진 1~4번까지 순서대로 구현
*/

class Solution {
    public String solution(String p) {
        String answer = "";
        
        return answer;
    }
    
    public static String bracket(String w){

        // 1번
        if(w.isEmpty()==true){
            return "";
        }
        
        // 2번
        int lb = 0;
        int rb = 0;
        int same = 0;
        String u = "";
        String v = "";
        char[] chr = new char[w.length()];
        
        for(int i=0; i< w.length(); i++){
            chr[i]= w.charAt(i);
        }
        
        for(int i=0; i<chr.length; i++){
            if(chr[i]==')'){
                rb++;
            }else{
                lb++;
            }
            if(rb==lb){
                same = i;
                break;
            }
        }
        u = w.substring(0,same+1);
        v = w.substring(same+1);
        
        // 3번  
        if(three(u)){
            return u + bracket(v);
        }

        return "";
    }
    
    // 3번 수행 "올바른 괄호 문자열 이라면"
    public static boolean three(String u){
        int lb = 0;
        int rb = 0;
        char[] chr = new char[u.length()];
        for(int i=0; i< u.length(); i++){
            chr[i]= u.charAt(i);
        }
        
        for(int i=0; i<chr.length; i++){
            if(chr[i]==')'){
                rb++;
            }else{
                lb++;
            }
            if(rb>lb){
                return false;
            }
        }
        return true;
    }
}

// 4번 구현 고민중,,,
// 코드 간결화 필요