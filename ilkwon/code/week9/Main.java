import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i = 1; i<len; i++){
            // while 조건을 empty()를 먼저 두지 않으면 런타임에러 발생합니다.
            while (!stack.empty() && prices[i] < prices[stack.peek()]){
                answer[stack.peek()]= i-stack.pop();
            }
            stack.push(i);
        }

        while (!stack.empty()){
            answer[stack.peek()]= prices.length-stack.pop()-1;
        }
        return answer;
    }
}
