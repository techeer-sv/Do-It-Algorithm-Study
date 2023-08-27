import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {
    /*
        임의의 정점부터, 다른 모든 정점까지의 최소 비용을 구해야함
        dijkstra를 사용하여 풀이
    */
    
    class Path{
        int dst;
        int cost;
        
        public Path(int d, int c){
            this.dst = d;
            this.cost = c;
        }
    }
    
    LinkedList<Path>[] paths;
    public int solution(int n, int[][] costs) {
        paths = new LinkedList[n];
        
        for (int i = 0; i<n; i++){
            paths[i] = new LinkedList<>();
        }
        
        for (int i = 0; i<costs.length; i++){
            paths[costs[i][0]].add(new Path(costs[i][1], costs[i][2]));
            paths[costs[i][1]].add(new Path(costs[i][0], costs[i][2]));
        }

        return dijkstra();
    }
    
    public int dijkstra(){
        PriorityQueue<Path> queue = 
            new PriorityQueue<>((a,b) -> a.cost - b.cost);
        
        int result = 0;
        boolean[] visited = new boolean[paths.length];
        visited[0] = true;
        queue.addAll(paths[0]);
        
        while(!queue.isEmpty()){
            Path p = queue.remove();
            if (!visited[p.dst]){
                visited[p.dst] = true;
                result += p.cost;
                
                for (Path path : paths[p.dst]){
                    if (!visited[path.dst]){
                        queue.add(path);
                    }
                }
            }
        }
        
        return result;
    }
    
}