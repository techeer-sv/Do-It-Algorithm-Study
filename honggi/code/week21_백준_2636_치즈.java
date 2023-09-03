import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
        https://www.acmicpc.net/problem/2636
        
        1. 입력에 따라, 정사각형 정보 저장
        2. 치즈 증발 단계 진행
        3. 치즈가 모두 증발할 때 까지 반복
     */

    static int height;
    static int width;
    static int cheese;
    static int[][] xy = {{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
//        System.setIn(new java.io.FileInputStream("/Users/hong/Documents/algorithm/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        boolean[][] map = new boolean[width][height];
        cheese = 0;

        StringTokenizer st;
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                String in = st.nextToken();
                if (in.equals("1")){
                    map[j][i] = true;
                    cheese++;
                }
            }
        }
        int hour = 0, lastCheese = 0;

        while (cheese > 0){
            lastCheese = cheese;
            map = meltCheese(map);
            hour++;
            for (boolean[] c: map){
                System.out.println(Arrays.toString(c));
            }
            System.out.println(cheese);
        }
        System.out.println(hour);
        System.out.println(lastCheese);

    }

    private static boolean[][] meltCheese(boolean[][] m) {
        boolean[][] visited = new boolean[width][height];
        for (int i = 0; i<width; i++){
            visited[i] = m[i].clone();
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (m[i][j]){
                    for (int k = 0; k < 4; k++) {
                        int nx = i + xy[k][0];
                        int ny = j + xy[k][1];
                        if (!checkBoundary(nx, ny) && !m[nx][ny]){
                            visited[i][j] = false;
                            cheese--;
                        }
                    }
                }
            }
        }
        return visited;
    }

    private static boolean checkBoundary(int x, int y){
        return x == width || x < 0 || y == height || y < 0;
    }

}