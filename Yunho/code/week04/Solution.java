// https://school.programmers.co.kr/learn/courses/30/lessons/42746
// 가장 큰 수
/**
 * 1,2,3,4,6,11 케이스 통과를 못했습니다 조금더 확인해보겠습니다.
 */
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        // 비교를 위해 문자열 형태로 변경 
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++){
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        for (int i = 0; i < strNumbers.length - 1; i++) {
            for (int j = i + 1; j < strNumbers.length; j++) {
                String str1 = strNumbers[i];
                String str2 = strNumbers[j];
        
                if ((str2 + str1).compareTo(str1 + str2) > 0) {
                    // str1이 str2보다 작은 경우 위치를 교환
                    String temp = strNumbers[i];
                    strNumbers[i] = strNumbers[j];
                    strNumbers[j] = temp;
                }
            }
        }
        
        for(String k:strNumbers){
            answer += k;
        }

        return answer;
    }
}