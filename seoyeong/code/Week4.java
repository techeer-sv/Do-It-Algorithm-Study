// 정렬 알고리즘
// https://school.programmers.co.kr/learn/courses/30/lessons/42746

import java.util.Arrays;
import java.util.stream.Collectors;

public class Week4 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{6,10,2,0})); // "62100"
        System.out.println(solution(new int[]{3, 30, 34, 5, 9})); // "9534330"
        System.out.println(solution(new int[]{4,43,40,2,100})); // "443402100"
        System.out.println(solution(new int[]{6,6,6,6})); // "6666"
        System.out.println(solution(new int[]{99,998,999,0,0})); // "9999999800"
        System.out.println(solution(new int[]{45, 454})); // "45 454"  "454 45"
        System.out.println(solution(new int[]{1,10,100,1000})); // "1101001000"
    }


    // 1. 입력된 수들을 String형 배열로 만들기    [2,6,10,0] => ["2","6","10","0"]
    // 2. 아스키코드값으로 비교하기 (두 개씩 비교)   아스키코드 (26) vs 아스키코드 (62)
    public static String solution(int[] numbers) {
        String answer = "";

        // 1. 입력된 수들을 string형 배열로 만들기 + 모든 수가 0인 경우를 고려하여, 합을 구하기
        int sum = 0; // 합
        String [] arr = new String[numbers.length];
        for (int i=0; i<numbers.length; i++){
            arr[i] = Integer.toString(numbers[i]);
            if (sum == 0) sum += numbers[i];
        }
        if (sum == 0)  return "0";


        // 2. 값 비교하기
        Arrays.sort(arr, (a, b) -> (b+a).compareTo(a+b));

        answer = Arrays.stream(arr) // 문자열 배열 전달
                .collect(Collectors.joining()); // 문자열 배열을 Join
        return answer;
    }
}
