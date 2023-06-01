import java.util.*;

class Solution {
    /*
        problem link - https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
        
        bfs algorithm 
        1. queue에서 원소를 제거
        2. 해당 정점(위치)의 edge(상하좌우)를 queue에 add
        3. 도착지에 방문할 때 까지 1~2번 반복

        queue의 int[] 원소의 의미 -> {row, col, depth}

    */
    boolean[][] isVisited;
    int rl,cl;
    int[][] drdc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    int min = Integer.MAX_VALUE;
    Queue<int []> child = new LinkedList();
    
    public int solution(int[][] maps) {
        rl = maps.length;
        cl = maps[0].length;
        isVisited = new boolean[rl][cl];
        
        // 벽에 해당하는 경우 방문한 것으로 처리
        for (int i = 0; i< rl; i++){
            for (int j = 0; j<cl; j++){
                if (maps[i][j] != 1)
                    isVisited[i][j] = true;
            }
        }
        
        int result = bfs(0,0);
        
        if (min != Integer.MAX_VALUE)
            return result;
        else
            return -1;
    }

    public int bfs(int rowI, int columnI){
        child.add(new int[]{rowI, columnI, 1});
        
        while(!child.isEmpty()){
            int[] c = child.remove();
            if (isVisited[c[0]][c[1]]){
                continue;
            }else {
                
                // bfs에서 도착지에 도달 -> 최단 거리를 의미
                if (c[0] == rl-1 && c[1] == cl-1){
                    min = Math.min(min, c[2]);
                    break;
                }
                
                isVisited[c[0]][c[1]] = true;

                // 상하좌우 길 탐색
                for (int[] direction : drdc) {
                    rowI = c[0] + direction[0];
                    columnI = c[1] + direction[1];
                    if (checkIndex(rowI, columnI)){
                        child.add(new int[]{rowI, columnI, c[2] + 1});
                    }
                }
            }
        }
        
        return min;
    }

    // 위치가 탐색 가능한지 검증
    public boolean checkIndex(int rowI, int columnI){
        if (rowI < 0 || rowI >=rl || columnI < 0 || columnI >= cl)
            return false;
        else
            return true;
    }
}