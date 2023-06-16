//https://school.programmers.co.kr/learn/courses/30/lessons/42584
//주식가격 
/*
 * 문제에서는 방향성을 스택 큐로 풀라는거 같았는데
 * 이중 for문으로 해결될 것 같아 해결했습니다. 시간복잡도가 O(n^2)인데 효율성도 통과했습니다
*/ 


import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        
        int pricesLen = prices.length;
        int[] answer = new int[pricesLen];

        for (int i=0; i<prices.length; i++) {
            answer[i] = 0;
            for (int j=i+1; j<prices.length; j++) {
                answer[i]++;
                if (prices[j] < prices[i]) {
                    break;
                }
            }
        }

        return answer;
    }

}