import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        //문자열 배열 생성
        String[] str = new String[numbers.length];

        // numbers값을 str에 삽입
        for (int i = 0; i < numbers.length; i++) {
            str[i] = Integer.toString(numbers[i]);
        }
        
        //compareTo() -> 문자열 비교 사용, 람다식
        Arrays.sort(str, (str1, str2) -> (str2 + str1).compareTo(str1 + str2));

        //join으로 인덱스 연결
        return String.join("", str);


    }
}

//테스트 11,,, 에러 발생 ,,, 
//compareTo 공부중,,,