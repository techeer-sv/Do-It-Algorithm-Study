class Solution {

    // 정확도 O, 효율성 X.. 다른 방법 강구해보기

    boolean[][] isVisited;
    int rl,cl;
    int[][] drdc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    int min = Integer.MAX_VALUE;
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
        int result = dfs(0,0,0);
        
        if (min != Integer.MAX_VALUE)
            return result;
        else
            return -1;
    }
    
    public int dfs(int rowI, int columnI, int depth){
        if (!checkIndex(rowI, columnI) || isVisited[rowI][columnI])
            return -1;
        else if (rowI == rl-1 && columnI == cl-1){
            min = Math.min(depth+1, min);
            return min;
        }
        
        int result = -1;
        isVisited[rowI][columnI] = true;
        for (int[] move : drdc){
            result = dfs(rowI + move[0], columnI + move[1], depth+1);
            if (result != -1){
                min = Math.min(min, result);
            }
        }
        isVisited[rowI][columnI] = false;
        
        return min;
    }
    
    public boolean checkIndex(int rowI, int columnI){
        if (rowI < 0 || rowI >=rl || columnI < 0 || columnI >= cl)
            return false;
        else
            return true;
    }
}