import java.util.*;

class Main{
    static int N;
    static int M;
    static int cheeseCnt = 0;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Point> cheeseList;

    public static void main(String argc[]){
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1) {
                    cheeseList.add(new Point(i, j));
                    cheeseCnt += 1;
                }
            }
        }

    }
}