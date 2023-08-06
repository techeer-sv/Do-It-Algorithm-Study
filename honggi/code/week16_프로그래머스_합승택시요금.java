import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    
    /*
        https://school.programmers.co.kr/learn/courses/30/lessons/72413

        풀이 방법
        1. 시작 위치 s에서 모든 정점에 대해 다익스트라 알고리즘을 적용하여 최소 요금 구하기
        2. 출발지로부터 연결된 모든 길에 대해 하단의 과정 반복
            1) queue에서 삭제한 현재 위치 i까지의 공통 요금(1번에서 구한 최소요금 활용)과, A와 B의 목적지 도착 요금을 구하여 합산
            2) 이전 위치의 결과값과 비교하여 최솟값 갱신
            3) 현재 위치와 연결된 모든 길에 대해, 방문하지 않은 지점을 queue에 삽입
        
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
    LinkedList<Fee>[] maps;
    int QSize;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        maps = new LinkedList[n+1];
        N = n;
        QSize = (N*(N-1))/2+2;
        
        for(int i = 1; i<=n; i++){
            maps[i] = new LinkedList<Fee>();
        }
        
        for (int i = 0; i<fares.length; i++){
            maps[fares[i][0]].add(new Fee(fares[i][1], fares[i][2]));
            maps[fares[i][1]].add(new Fee(fares[i][0], fares[i][2]));
        }
        
        return commonFee(s, a, b);
    }
    
    public int commonFee(int start, int end1, int end2){
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Fee> queue = new PriorityQueue<>(QSize, (a, b) -> a.fee - b.fee);
        
        int[] sFee = calculateFee(start, 0);
        int result = sFee[end1] + sFee[end2];

        queue.add(new Fee(start, 0));
        
        while (!queue.isEmpty()){
            Fee f = queue.remove();
            
            if (!visited[f.loc]){
                visited[f.loc] = true;

                int[] aFee = calculateFee(f.loc, end1);
                int[] bFee = calculateFee(f.loc, end2);

                result = Math.min(sFee[f.loc] + aFee[end1] + bFee[end2], result);

                for (Fee e : maps[f.loc]){
                    if (!visited[e.loc] && result > f.fee + e.fee){
                        queue.add(new Fee(e.loc, f.fee + e.fee));
                    }
                }
            }
        }
            
        return result;
    }
    
    // dijkstra 시간복잡도 - nlogn (힙구조 PriorityQueue 사용)
    public int[] calculateFee(int start, int end){
        boolean[] visited = new boolean[N+1];
        int[] dp = new int[N+1];
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] = 0; 

        PriorityQueue<Fee> queue = new PriorityQueue<>(QSize, (a, b) -> a.fee - b.fee);
        
        queue.add(new Fee(start,0));
        
        while (!queue.isEmpty()){
            Fee f = queue.remove();
            
            if (f.loc == end){
                break;
            }
            if (!visited[f.loc]){
                visited[f.loc] = true;

                for (Fee e : maps[f.loc]){
                    if (!visited[e.loc] && dp[e.loc] > e.fee + f.fee){
                        dp[e.loc] = e.fee + f.fee;
                        queue.add(new Fee(e.loc, e.fee + f.fee));
                    }
                }
            }
       }
        return dp;
    }    
}