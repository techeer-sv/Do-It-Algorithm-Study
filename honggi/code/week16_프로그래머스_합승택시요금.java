import java.util.HashMap;
import java.util.Map.Entry;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;


class Solution {
    class Fee{
        int dst;
        int fee;
        
        public Fee(int d, int f){
            this.dst = d;
            this.fee = f;
        }
    }
    int[] dp;
    HashMap<Integer, Integer>[] maps;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dp = new int[n+1];
        maps = new HashMap[n+1];
        
        for(int i = 0; i<n; i++){
            maps[i] = new HashMap(200);
        }
        
        int answer = 0;
        for (int i = 0; i<fares.length; i++){
            maps[fares[i][0]].put(fares[i][1], fares[i][2]);
        }
        
        for (int i = 1; i<=n; i++){
            dp[i] = maps[s].getOrDefault(i, Integer.MAX_VALUE);
        }
        
        int feeA = calculateFee(s, a);
        System.out.println(Arrays.toString(dp));
        int feeB = calculateFee(s, b);
        System.out.println(Arrays.toString(dp));
        
        return answer;
    }
    
    public int calculateFee(int start, int end){
        Queue<Fee> queue = new LinkedList<>();
        queue.add(new Fee(0,0));
        while (!queue.isEmpty()){
            Fee f = queue.remove();
            
            if (dp[f.dst] < f.fee)
                continue;
            
            for (Entry<Integer, Integer> e : maps[f.dst].entrySet()){
                dp[e.getKey()] = Math.min(dp[e.getKey()], e.getValue() + f.fee);
                queue.add(new Fee(e.getKey(), e.getValue() + f.fee));
            }
        }
        
        return dp[end];
    }
    
}