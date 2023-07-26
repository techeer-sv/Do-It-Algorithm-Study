// 카카오프렌즈 컬러링북
// https://school.programmers.co.kr/learn/courses/30/lessons/1829

import java.util.LinkedList;
import java.util.Queue;

public class DoitJava_Week15_2 {
    public static void main(String[] args){
        int[] ans = solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}});
        System.out.println((ans[0] + " " + ans[1]));// [4, 5]
        int[] ans2 = solution(13, 16, new int[][] {{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0}, {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}});
        System.out.println((ans2[0] + " " + ans2[1]));// [12,120]
    }
        public static int[] solution(int m, int n, int[][] picture) {
            int[] answer = new int[]{0,0};
            Queue<int[]> queue1 = new LinkedList<>();
            Queue<int[]> queue2 = new LinkedList<>();
            int x = 0, y = 0, nx = 0, ny = 0;
            int [] dx = new int[]{-1,0,1,0};
            int [] dy = new int[]{0,1,0,-1};
            int [][] visited = new int[m][n];
            int color = -1; // 색깔 타입
            int colorSize = 0; // 같은 색깔로 이어진 구역 개수
            queue1.add(new int[]{0,0});

            if (picture[x][y]>0){
                answer[0]++;
                colorSize++;
            }


            while (queue1.size()>0 || queue2.size()>0){
                // 이전에 탐색했던 구역은 탐색 완료했으므로, 새로운 구역 탐색을 위해 queue2에 있는 값을 꺼내온다
                if (queue1.size() == 0) {
                    answer[1] = (colorSize > answer[1] ? colorSize : answer[1]);
                    colorSize = 1;
                    if (visited[queue2.peek()[0]][queue2.peek()[1]] == 1){ // 이미 탐색했으면, 건너뛰기
                        queue2.poll();
                        continue;
                    }
//                    System.out.println("지금까지 탐색결과: " + answer[0] + " " + answer[1]); // 현재까지 탐색 결과
//                    for (int i = 0; i < m; i++) {
//                        for (int j = 0; j < n; j++) {
//                            System.out.print(visited[i][j] + " ");
//                        }
//                        System.out.println();
//                    }
//                    System.out.println(queue2.peek()[0] + " " + queue2.peek()[1]);
                    queue1.add(queue2.poll());
                    if (picture[queue1.peek()[0]][queue1.peek()[1]] > 0) answer[0]++;
                }

                int [] pos = queue1.poll();
                x = pos[0];
                y = pos[1];

                if (visited[x][y] == 1) continue;
                visited[x][y] = 1;

                // 바로 이전과 같은 구역을 탐색하고 있을 때
                if (picture[x][y] == color && picture[x][y] > 0) colorSize++;

                color = picture[x][y];

                for (int i = 0; i < 4; i++) {
                    nx = x + dx[i];
                    ny = y + dy[i];
                    if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || visited[nx][ny] == 1) continue;

                    if (picture[nx][ny] == color) {
                        queue1.add(new int[]{nx, ny});
                    } else{ // 같은 색깔이 아닌 경우
                        queue2.add(new int[]{nx, ny});
                    }
                }
            }
            answer[1] = (colorSize > answer[1] ? colorSize : answer[1]);
            return answer;
        }
}
