import java.util.*;
class 프로그래머스_가장큰수 {
    /*
        가장 큰 수를 만들 수 있도록 numbers를 오름차순으로 정렬하여 큰 수 만들기
        정렬 방법 -> 두 원소로 만들 수 있는 두 개의 수를 대소 비교
    */
    public String solution(int[] numbers) {
        String answer = "";
        String[] numberI = new String[numbers.length];

        Arrays.setAll(numberI, i -> String.valueOf(numbers[i]));
        Arrays.sort(numberI, (a, b) -> (a+b).compareTo(b+a));

        for (int i = numberI.length-1; i>=0; i--)
            answer += numberI[i];
        
        // 모든 원소가 0인 경우
        if (answer.charAt(0) == '0')
            return "0";
        else
            return answer;
    }
}