import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

class Solution {
    
    /*
        모든 지점에서 a와 b를 도착하는데 드는 택시비용 계산 -> dijkstra 이용
    */
    class Fee{
        int loc;
        int fee;
        
        public Fee(int l, int f){
            this.loc = l;
            this.fee = f;
        }
    }
    
    int N;
    int dp[];
    HashMap<Integer, Integer>[] maps;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        maps = new HashMap[n+1];
        N = n;
        
        for(int i = 1; i<=n; i++){
            maps[i] = new HashMap(200);
        }
        
        int answer = 0;
        for (int i = 0; i<fares.length; i++){
            maps[fares[i][0]].put(fares[i][1], fares[i][2]);
            maps[fares[i][1]].put(fares[i][0], fares[i][2]);
        }
        
        dp = calculateFee(s);
        int result = Integer.MAX_VALUE;
        for (int i = 1; i<=n; i++){
            int[] dijkstra = calculateFee(i);
            result = Math.min(dijkstra[a] + dijkstra[b] + dp[i], result);
        }
        return result;
    }
    
    // dijkstra 시간복잡도 - nlogn (힙구조 사용)
    public int[] calculateFee(int start){
        int dp[] = new int[N+1];
        
        for (int i = 1; i<=N; i++){
            dp[i] = maps[start].getOrDefault(i, Integer.MAX_VALUE);
        }

        PriorityQueue<Fee> queue = new PriorityQueue<>((Fee a,Fee b) -> Integer.compare(a.fee, b.fee));
        queue.add(new Fee(start,0));
        while (!queue.isEmpty()){
            Fee f = queue.remove();
            
            if (dp[f.loc] < f.fee)
                continue;
            
            for (Entry<Integer, Integer> e : maps[f.loc].entrySet()){
                dp[e.getKey()] = Math.min(dp[e.getKey()], e.getValue() + f.fee);
                queue.add(new Fee(e.getKey(), e.getValue() + f.fee));
            }
        }
        dp[start] = 0;
        return dp;
    }
}