import java.util.*;
class Solution {
    List<int[]> stock;
    public int solution1(int[] prices){

        // 정확도 O, 시간초과..
        int pricesL = prices.length;
        int[] answer = new int[pricesL];
        stock = new ArrayList<Integer>(pricesL);
        
        for (int i = 0; i<pricesL; i++){
            
            for (int j = 0; j<i; j++){
                if (answer[j] == 0 && stock.get(j) > prices[i])
                    answer[j] = i-j;
            }
            stock.add(prices[i]);
        }
        
        for (int i = 0; i<pricesL; i++){
            if (answer[i] == 0)
                answer[i] = pricesL -1 - i;
        }
        
        return answer;
        
    }