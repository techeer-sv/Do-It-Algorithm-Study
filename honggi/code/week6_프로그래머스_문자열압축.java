class Solution {
    int sL;
    public int solution(String s) {
        /*
            1개 이상 단위로 문자열을 압축하여 표현한 문자열 중, 가장 짧은 것의 길이를 return
            
            1. n개 단위로 문자열 압축
            2. 문자열 길이 대소비교
            
            시간복잡도 : 10^3 -> n^2logn까지 가능
            - 완탐 -> n + n/2 + n/3 .. n/n < n^2
        */

        int answer = Integer.MAX_VALUE;
        sL = s.length();
        
        for (int i = 1; i <= sL; i++){
            answer = Math.min(answer, compressString(s, i));
        }
        return answer;
    }
    
    // 문자열 압축
    public int compressString(String s, int digit){
        int index = 0;
        
        // digit 단위로 pattern을 분리
        while (index + digit <= s.length()){
            String pattern = s.substring(index, index + digit);

            // 중복되는 pattern 검사
            String[] removedStringInfo = removeDuplicatePattern(s, pattern, index);
            
            // pattern이 연속되는 부분이 없는 경우
            if (s.equals(removedStringInfo[0]))
                index += digit;
            else{ 
                s = removedStringInfo[0];
                // 다음 pattern이 시작되는 index 계산
                index += digit + removedStringInfo[1].length();
            }
        }
        return s.length();
    }
    
    // pattern과 일치하는 연속된 문자열의 개수를 계산 및 압축
    public String[] removeDuplicatePattern(String s, String pattern, int index){
        int patternCount = 1;
        int digit = pattern.length();
        
        while (index + 2*digit <= s.length()){
            String comparedPattern = s.substring(index + digit, index + 2*digit);
            
            // 연속되는 문자열이 존재하는 경우
            if (pattern.equals(comparedPattern)){
                // 해당 문자열 제외
                s = s.substring(0, index) + s.substring(index+digit);
                patternCount++;
            }else{
                break;
            }
        }
        
        // 연속되는 문자열이 존재하지 않는 경우
        if (patternCount == 1)
            return new String[]{s, String.valueOf(patternCount)};
        else{
            // pattern 앞에 연속된 문자열 개수를 붙여서 반환
            return new String[]{s.substring(0, index) + patternCount + 
                               s.substring(index, s.length()), String.valueOf(patternCount)};
        }
    }
    
    
}