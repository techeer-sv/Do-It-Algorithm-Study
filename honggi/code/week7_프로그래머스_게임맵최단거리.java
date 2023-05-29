import java.util.*;

class Solution {
    boolean[][] isVisited;
    int rl,cl;
    int[][] drdc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    int min = Integer.MAX_VALUE;
    Queue<int []> child = new LinkedList();
    
    public int solution(int[][] maps) {
        rl = maps.length;
        cl = maps[0].length;
        isVisited = new boolean[rl][cl];
        
        for (int i = 0; i< rl; i++){
            for (int j = 0; j<cl; j++){
                if (maps[i][j] != 1)
                    isVisited[i][j] = true;
            }
        }
        
        int result = bfs(0,0,0);
        
        if (min != Integer.MAX_VALUE)
            return result;
        else
            return -1;
    }

    public int bfs(int rowI, int columnI, int depth){
        child.add(new int[]{0,0,1});
        
        while(!child.isEmpty()){
            int[] c = child.remove();
            if (isVisited[c[0]][c[1]]){
                continue;
            }else {
                
                if (c[0] == rl-1 && c[1] == cl-1){
                    min = Math.min(min, c[2]);
                    break;
                }
                
                isVisited[c[0]][c[1]] = true;
                
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

    public boolean checkIndex(int rowI, int columnI){
        if (rowI < 0 || rowI >=rl || columnI < 0 || columnI >= cl)
            return false;
        else
            return true;
    }
}