// https://www.acmicpc.net/problem/2636

/*
13 12
0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 1 0 0 0
0 1 1 1 0 0 0 1 1 0 0 0
0 1 1 1 1 1 1 0 0 0 0 0
0 1 1 1 1 1 0 1 1 0 0 0
0 1 1 1 1 0 0 1 1 0 0 0
0 0 1 1 0 0 0 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Week21_치즈 {
    public static void main(String[]args) throws IOException {
        // 초기화
        int time = 1;
        int leftCheese = 0;
        int x, y, xx, yy = 0;
        int [] nx = new int[]{-1,0,1,0};
        int [] ny = new int[]{0,1,0,-1};
        Queue<int[]>queue = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] size = br.readLine().split(" ");
        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);
        int[][]square = new int[n][m];
        boolean[][]visited = new boolean[n][m];
        for (int i=0; i<n; i++){
            String [] tmp = br.readLine().split(" ");
            for (int j=0; j<m; j++){
                square[i][j] = Integer.parseInt(tmp[j]);
                if (square[i][j] == 1) leftCheese++;
            }
        }

        queue.add(new int[]{0,0});


        // 공기 중에 접촉한 치즈 -> 치즈모양의 변화
        while (leftCheese > 0) {

            int[][] nextSquare = new int[n][m];
            for (int i = 0; i < nextSquare.length; i++) {
                System.arraycopy(square[i], 0, nextSquare[i], 0, nextSquare[0].length);
            }
            System.out.println(time + " Cheese: " + leftCheese);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(square[i][j] + " ");
                }
                System.out.println();
            }


            while (queue.size() > 0) {
                int[] xy = queue.poll();
                x = xy[0];
                y = xy[1];
                visited[x][y] = true;
                /*
                nextSquare[xx][yy] = 0;
                leftCheese--;
                */
                time++;
                for (int k = 0; k < 4; k++) {
                    xx = x + nx[k];
                    yy = y + ny[k];
                    if (xx >= 0 && xx < n && yy >= 0 && yy < m && visited[xx][yy]==false) {
                        queue.add(new int[]{xx, yy});
                    }
                }
            }

            for (int i = 0; i < nextSquare.length; i++) {
                System.arraycopy(nextSquare[i], 0, square[i], 0, square[0].length);
            }
        }
        System.out.println(time);
    }
}
