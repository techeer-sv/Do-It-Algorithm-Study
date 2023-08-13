class Solution {
    /*
        모든 섬을 연결하는데 드는 최소 비용 구하기
        -> PriorityQueue를 사용하여 모든 정점을 연결하는데 드는 최소 비용 구하기
        
        1. 임의의 정점을 기준으로 bfs 탐색 진행
        2. queue에서 제거한 섬을 기준으로 주변 정점을 탐색
            1) 아직 방문하지 않은 섬이라면, 해당 섬까지의 최소 비용 업데이트 후 queue에 삽입
            2) 방문한 섬이라면, 기존 최소 비용과 비교하여 더 작은경우 queue에 삽입
        3. queue가 비어있을 때 까지 반복하여 최소 비용 합산

    */
    
    class Path{
        int dst;
        int cost;
        
        Path(int d, int c){
            this.dst = d;
            this.cost = c;
        }
    }
    
    int[] dp;
    LinkedList<Integer>[] paths;
    public int solution(int n, int[][] costs) {
        dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        paths = new LinkedList[n];
        for (int i = 0; i<n; i++){
            paths[i] = new LinkedList<>();
        }
        
        return dijkstra(0);
    }
    
    private int dijkstra(int index){
        PriorityQueue<Path> queue = new PriorityQueue((a,b) -> a.cost - b.cost);
        int result = 0;
        queue.add(index);
        
        while (!queue.isEmpty()){
            
        }
        
        return result;
    }
}