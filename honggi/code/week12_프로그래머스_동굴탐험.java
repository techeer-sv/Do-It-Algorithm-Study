import java.util.LinkedList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;

class Solution {
    /*
        특정 방의 경우 방문하기 전 방문해야 할 방이 존재하며, 이러한 규칙을 모두 지키며 모든 방을
        탐험이 가능한지. (0번 방부터 탐험 시작)
        
        주의사항
        - 모든 방은 양방향으로 연결
        - 먼저 방문해야 할 방이 겹치는 경우는 x
        
        1. 각 방마다 연결 가능한 통로 정보, order 정보를 정리하여 저장
                
    */
    LinkedList<Integer>[] paths;
    int[] orderInfo;
    Queue<Integer> queue;
    Map<Integer, Integer> findOrder;
    boolean[] visited;
    public boolean solution(int n, int[][] path, int[][] order) {
        paths = new LinkedList[n];
        queue = new LinkedList();
        orderInfo = new int[n];
        findOrder = new HashMap<Integer, Integer>();
                
        for (int[] p : path){
            if (paths[p[0]] == null){
                paths[p[0]] = new LinkedList();
            }
            paths[p[0]].add(p[1]);
            
            if (paths[p[1]] == null){
                paths[p[1]] = new LinkedList();
            }            
            paths[p[1]].add(p[0]);
        }
                
        for (int[] o : order){
            orderInfo[o[1]] = o[0];
            findOrder.put(o[0], o[1]);
        }
        
        if (findOrder.containsValue(0))
            return false;
                
        for (int i = 0; i < Math.abs(n); i++){
            visited = new boolean[n];
            exploration();
        }
        
        boolean answer = true;                
        for (int i = 0; i<n; i++){
            if (!visited[i]){
                answer = false;
                break;
            }
        }
        
        return answer;
    }
    
    public void exploration(){
        queue.add(0);
        visited[0] = true;
        
        while (!queue.isEmpty()){
            int room = queue.remove();
            LinkedList<Integer> rooms = paths[room];
            for (int r : rooms){
                if (!visited[r]){
                    Integer o = findOrder.remove(r);
                    
                    if (o != null){
                        orderInfo[o] = 0;
                        queue.clear();
                        break;
                    }
                    
                    if (orderInfo[r] == 0){
                        visited[r] = true;
                        queue.add(r);
                    }               
                }
            }
        }
    }
}