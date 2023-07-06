import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    /*
        특정 방의 경우 방문하기 전 방문해야 할 방이 존재하며, 이러한 규칙을 모두 지키며 모든 방을
        탐험이 가능한지. (0번 방부터 탐험 시작)
        
        주의사항
        - 먼저 방문해야 할 방이 겹치는 경우는 x
        - 임의의 서로 다른 두 방 사이의 최단경로는 딱 한 가지 -> cycle이 존재하지 않음
        
        1. 각 방마다 연결 가능한 통로 정보, order 정보를 정리하여 저장
        2. 0번방을 queue에 삽입
        3. queue의 head를 제거하여 연결된 방을 탐색
            1) 해당 방이 order에 포함된 방인 경우([A,B]중 B에 속할 때) 
              -> A를 방문할 때 까지 기다리도록 Set에 저장
            2) 해당 방이 order에 포함된 방인 경우([A,B]중 A에 속할 때)
              ① Set에서 B를 삭제 및 queue에 삽입
              ② 자신의 depth보다 높은 방들을 queue에 삽입
            3) 해당 방이 order에 포함되지 않는 방(전제조건 x)인 경우
              -> 자신의 depth보다 높은 방들을 queue에 삽입
        4. queue가 빌 때 까지 3번 반복
    */
    LinkedList<Integer>[] paths;
    int[] orderInfo;
    Queue<Integer> queue;
    HashMap<Integer, Integer> findOrder;
    HashSet<Integer> waitRoom;
    int[] degree;
    public boolean solution(int n, int[][] path, int[][] order) {
        paths = new LinkedList[n];  
        queue = new LinkedList();
        waitRoom = new HashSet();
        orderInfo = new int[n];
        findOrder = new HashMap<Integer, Integer>();
        degree = new int[n];
        
        // 리스트 초기화
        for (int i = 0; i<n; i++){
            paths[i] = new LinkedList();
        }

        // 각 방마다 연결 가능한 통로 정보 정리
        for (int[] p : path){
            paths[p[0]].add(p[1]);
            paths[p[1]].add(p[0]);
        }
        
        // 진입차수 정리
        sortDegree();
        
        // order 정보 정리
        for (int[] o : order){
            orderInfo[o[1]] = o[0];
            findOrder.put(o[0], o[1]);
        }
        
        exploration(n);
        
        // order가 모두 삭제된 경우 -> 모든 방을 탐색한 경우.
        return findOrder.isEmpty();
    }
    
    // 진입차수 정리 (0번 방을 root로 하고, tree의 depth로 이용)
    public void sortDegree(){
        queue.add(0);
        
        while (!queue.isEmpty()){
            int i = queue.remove();
            LinkedList<Integer> path = paths[i];
            
            for (int p : path){
                // 0번 방이 아니거나 아직 차수가 정리되지 않은 방 -> 방 i보다 depth가 깊은 방
                if (p != 0 && degree[p] == 0){
                    degree[p] = degree[i] + 1;
                    queue.add(p);
                } 
            }
        }
    }
    
    // 탐사 진행
    public void exploration(int n){
        queue.add(0);
        
        while (!queue.isEmpty()){
            int room = queue.remove();
            
            // 먼저 방문해야할 방 조건이 없는 경우
            if (orderInfo[room] == 0){
                Integer o = findOrder.remove(room);
                
                // 해당 방을 방문함으로써 방문할 수 있는 방이 생기는 경우
                if (o != null){ 
                    orderInfo[o] = 0;
                    // 방문하지 못했던 방이 존재할 때 -> queue 삽입
                    if (waitRoom.remove(o)){
                        queue.add(o);
                    }
                }

                // 자신의 depth보다 높은 방들을 queue에 삽입
                LinkedList<Integer> rooms = paths[room];
                for (int r : rooms){
                    if (degree[room] < degree[r]){
                        queue.add(r);
                    }
                }
            } else {
                // 먼저 방문해야할 방을 방문할 때 까지 대기
                waitRoom.add(room);
            }
        }
    }
}