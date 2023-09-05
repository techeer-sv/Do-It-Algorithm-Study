class Solution {
    public boolean[][] check;
    public int[] dx = {1, -1, 0, 0};
    public int[] dy = {0, 0, 1, -1};
    public int count=0;

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        check = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(!check[i][j] && picture[i][j]!=0){
                    dfs(i, j, m, n, picture, picture[i][j]);
                    maxSizeOfOneArea = Math.max(count, maxSizeOfOneArea);
                    numberOfArea++;
                    count=0;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public void dfs(int x, int y, int m, int n, int[][] picture, int area){
        check[x][y] = true;
        count++;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx <0 || ny<0 || nx>=m || ny>=n) continue;
            if(check[nx][ny]) continue;
            if(picture[nx][ny]== area) {
                dfs(nx, ny, m, n, picture, area);
            }
        }
    }
}
