// 주식 가격
// https://school.programmers.co.kr/learn/courses/30/lessons/42584#qna

import java.util.Arrays;
import java.util.Stack;

public class DoitJava_Week9 {
    public static void main(String[]args){
        int [] ans = solution(new int[]{1,2,3,2,3}); // [4, 3, 1, 1, 0]
        //int [] ans = solution(new int[]{2,2,3,1,5}); // [3, 2, 1, 1, 0]
        System.out.println(Arrays.toString(ans));
    }

    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length]; // 정답용 int형 배열
        Stack<Integer> stack = new Stack<>(); //int형 스택

        Arrays.fill(answer,0);
        stack.add(0);
        int p = 0; // 과거 주식 시간 ex) 2

        for (int i=1; i<prices.length; i++) {
            //System.out.println("\n"+stack);
            //System.out.println(Arrays.toString(answer));
            while (!stack.empty() && prices[i] < prices[stack.peek()]){
                p = stack.pop();
                answer[p] = i-p;
            }
            stack.add(i);
        }

        // 끝까지 버틴 주식 처리
        while (!stack.empty()){
            p = stack.pop();
            if (p == prices.length-1) answer[p] = 0;
            else answer[p] = prices.length - p -1;
        }

        return answer;
    }
}
