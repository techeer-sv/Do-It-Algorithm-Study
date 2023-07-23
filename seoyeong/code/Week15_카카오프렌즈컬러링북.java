// 카카오프렌즈 컬러링북
// https://school.programmers.co.kr/learn/courses/30/lessons/1829

import java.util.LinkedList;
import java.util.Queue;

public class DoitJava_Week15_2 {
    public static void main(String[] args){
        int[] ans = solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}});
        System.out.println((ans.toString()));// [4, 5]
    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] answer = new int[2];

        Queue<int[]> queue1 = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();
        int x = 0, y = 0;
        int nx = 0, ny = 0;
        int x2 = 0, y2 = 0;
        int nx2 = 0, ny2 = 0;
        // 동 남 서 북
        int [] dx = new int[]{-1,0,1,-1};
        int [] dy = new int[]{0,1,0,-1};
        int [][] visited = new int[m][n];
        int color;

        queue1.add(new int[]{0,0});

        while (queue1.size()>0){
            int [] pos = queue1.peek();
            x = pos[0];
            y = pos[1];
            visited[x][y] = 1;

            color = picture[nx][ny];
            numberOfArea++;
            int colorSize = 1;
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1) continue;
                if (visited[nx][ny] == 1) continue;

                if (picture[nx][ny] == color) {
                    colorSize++;
                    queue2.add(new int[]{nx, ny});
                } else {
                    queue1.add(new int[]{nx, ny});
                }
            } // for
            while (queue2.size()>0) {
                int [] pos2 = queue2.peek();
                x2 = pos2[0];
                y2 = pos2[1];
                for (int i = 0; i < 4; i++) {
                    nx2 = x2 + dx[i];
                    ny2 = y2 + dy[i];
                    if (nx2 < 0 || nx2 > n - 1 || ny2 < 0 || ny2 > m - 1) continue;
                    if (visited[nx2][ny2] == 1) continue;

                    if (picture[nx2][ny2] == color) {
                        colorSize++;
                        queue2.add(new int[]{nx2, ny2});
                    } else {
                        queue1.add(new int[]{nx2, ny2});
                    }
                } // for
            } // while2
            maxSizeOfOneArea = (colorSize > maxSizeOfOneArea ? colorSize : maxSizeOfOneArea);
        } // while1

        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }


        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}
