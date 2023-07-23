// 카카오프렌즈 컬러링북
// https://school.programmers.co.kr/learn/courses/30/lessons/1829

import java.util.LinkedList;
import java.util.Queue;

public class DoitJava_Week15_2 {
    public static void main(String[] args){
        int[] ans = solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}});
        System.out.println((ans[0] + " " + ans[1]));// [4, 5]
    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] answer = new int[]{0,0};
        Queue<int[]> queue1 = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();
        int x = 0, y = 0, nx = 0, ny = 0;
        int [] dx = new int[]{-1,0,1,-1};
        int [] dy = new int[]{0,1,0,-1};
        int [][] visited = new int[m][n];
        int color = 0;
        int colorSize = 0;
        queue1.add(new int[]{0,0});

        while (queue1.size()>0 || queue2.size()>0){
            if (queue1.size() == 0)
                queue1.add(queue2.poll());
            int [] pos = queue1.poll();
            x = pos[0];
            y = pos[1];
            if (visited[x][y] == 1) continue;
            visited[x][y] = 1;
            if (picture[x][y] != color && picture[x][y] > 0) {
                maxSizeOfOneArea = (colorSize > maxSizeOfOneArea ? colorSize : maxSizeOfOneArea);
                numberOfArea++;
                colorSize = 1;
            } else if (picture[x][y] == color && picture[x][y] > 0)
                colorSize++;
            color = picture[x][y];


            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || visited[nx][ny] == 1) continue;

                if (picture[nx][ny] == color) {
                    queue1.add(new int[]{nx, ny});
                } else{ // 같은 색깔이 아닌 경우 또는 0인 경우
                    queue2.add(new int[]{nx, ny});
                }
            } // for
        } // while1

        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}
