import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
        1. 입력에 따라, 정사각형 정보 저장
        2. 치즈 녹는 단계 진행
            1) 치즈 내부에 존재하는 치즈 셀인지 파악
            2) 가장자리 치즈인 경우, 주변 셀 중 공기 셀이 존재할 때 녹임(true -> false)
        3. 치즈가 모두 증발한 경우, 종료
     */
    static int height;
    static int width;
    static int cheese;
    static boolean[][] map;
    static int[][] xy = {{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        map = new boolean[height][width];
        cheese = 0;

        StringTokenizer st;
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                String in = st.nextToken();
                if (in.equals("1")){
                    map[i][j] = true;
                    cheese++;
                }
            }
        }
        int hour = 0, lastCheese = 0;

        while (cheese > 0){
            lastCheese = cheese;
            map = meltCheese();
            hour++;
        }
        System.out.println(hour);
        System.out.println(lastCheese);

    }

    private static boolean[][] meltCheese() {
        // n 시간과, n+1시간때의 치즈 상태를 구분하기 위한 변수
        boolean[][] visited = new boolean[height][width];
        for (int i = 0; i<height; i++){
            visited[i] = map[i].clone();
        }

        // 모든 셀에 대하여, 치즈가 녹는 과정 진행
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // 치즈가 존재하면서, 내부 치즈 셀이 아닌 경우
                if (map[i][j] && !checkInsideCheese(i, j)){
                    for (int k = 0; k < 4; k++) {
                        int nx = i + xy[k][0];
                        int ny = j + xy[k][1];
                        // 주변 셀 중, 공기가 포함된 셀인 경우
                        if (!outOfRange(nx, ny) && !map[nx][ny]){
                            visited[i][j] = false;
                            cheese--;
                            break;
                        }
                    }
                }
            }
        }
        return visited;
    }

    // bfs를 통해 사각형 판의 끝자락에 도달 -> 해당 칸의 치즈는 맨 가장자리 치즈임을 의미
    private static boolean checkInsideCheese(int x, int y){
        boolean[][] visited = new boolean[height][width];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        while (!queue.isEmpty()){
            int[] pos = queue.remove();
            if (!visited[pos[0]][pos[1]]) {
                visited[pos[0]][pos[1]] = true;
                for (int i = 0; i < 4; i++) {
                    int nx = pos[0] + xy[i][0];
                    int ny = pos[1] + xy[i][1];
                    if (outOfRange(nx, ny)){
                        return false;
                    } else if (!map[nx][ny] && !visited[nx][ny]){
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return true;
    }

    private static boolean outOfRange(int x, int y){
        return x == height || x < 0 || y == width || y < 0;
    }

}