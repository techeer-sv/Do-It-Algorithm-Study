import java.util.*;
import java.io.*;



public class Main {
    static int[] rangeX = { -1, 0, 1, 0 };
    static int[] rangeY = { 0, 1, 0, -1 };
    static int N;
    static int[][] map; // 대나무 숲
    static int[][] dp;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
 
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, DFS(i, j));
            }
        }
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
    public static int DFS(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        
        // 판다가 대나무 숲에서 최소한 1년은 살 수 있으므로
        // dp[x][y] = 1로 초기화 할 수 있음.
        dp[x][y] = 1;
 
        int dx, dy;
        // 상하좌우 검사.
        for (int i = 0; i < 4; i++) {
            dx = x + rangeX[i];
            dy = y + rangeY[i];
            
            if (dx < 0 || dy < 0 || dx >= N || dy >= N) {
                continue;
            }
            
            if (map[x][y] < map[dx][dy]) {
                dp[x][y] = Math.max(dp[x][y], DFS(dx, dy) + 1);
                DFS(dx, dy);
            }
        }
        return dp[x][y];
    }
 
}