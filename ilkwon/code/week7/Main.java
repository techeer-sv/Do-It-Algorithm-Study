import java.util.*;

class Solution {
    int[] xt = {1, -1, 0, 0};
    int[] yt = {0, 0, 1, -1};

    public int solution(int[][] maps) {
        
        int answer = 0;
        int x = maps.length;
        int y = maps[0].length;
        int[][] way = new int[x][y];
        way[0][0] = 1;


        bfs(maps, way);
        answer  = way[x-1][y-1];
        
        if (answer == 0){
            answer = -1;
        }

        return answer;
    }

    public void bfs(int[][] maps, int[][] way){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for ( int i=0; i < 4; i++){
                int xm = x + xt[i];
                int ym = y + yt[i];
                if (xm >= 0 && xm < maps.length && ym >= 0 && ym < maps[0].length  && way[xm][ym]==0 && maps[xm][ym] == 1){
                    way[xm][ym] = way[x][y] + 1;
                    queue.add(new int[]{xm,ym});
                }
            }
        }
    }
}