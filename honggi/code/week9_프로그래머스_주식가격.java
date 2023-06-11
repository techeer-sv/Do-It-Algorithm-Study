import java.util.*;
class Solution {
    List<int[]> stock;
    public int[] solution(int[] prices) {
        /*
            주식의 가격이 같거나 상승 기간 계산
            prices의 길이 100,000 -> 최대 nlogn
            queue에 저장되는 원소 -> 인덱스, 주식가격            
        */
        int pricesL = prices.length;
        int[] answer = new int[pricesL];
        
        stock = new ArrayList<int[]>(pricesL);
        
        for (int i = 0; i<pricesL; i++){
            
            // queue의 저장된 원소 중, 가격이 떨어진 주식을 찾아 기록 및 제거
            for (int j = 0; j<stock.size(); j++){
                int[] s = stock.get(j);
                if ( s[1] > prices[i] ){
                    answer[s[0]] = i-s[0];
                    stock.remove(j--);
                }
            }
            stock.add(new int[]{i, prices[i]});
        }
        
        // queue에 남은 원소 -> 끝까지 가격이 떨어지지 않은 주식들
        for (int j = 0; j<stock.size(); j++){
            int[] s = stock.get(j);
            answer[s[0]] = pricesL - 1 - s[0];
        }
        
        return answer;
    }
}