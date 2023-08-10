//https://school.programmers.co.kr/learn/courses/30/lessons/1829
//카카오프랜즈 컬러링북

import java.util.*;

/**
 * bfs로 인접한 같은 색상의 영역을 탐색 
 * 방문한 곳은 visited로 구분
 */

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        boolean[][] visited = new boolean[m][n];
        int[] answer = new int[2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    int areaSize = bfs(i, j, picture, visited);
                    answer[0]++; 
                    answer[1] = Math.max(answer[1], areaSize); 
                }
            }
        }

        return answer;
    }

    public int bfs(int x, int y, int[][] picture, boolean[][] visited) {
        int areaSize = 0;
        int color = picture[x][y];
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            areaSize++;

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx >= 0 && nx < picture.length && ny >= 0 && ny < picture[0].length) {
                    if (picture[nx][ny] == color) {
                        if (!visited[nx][ny]) {
                            queue.offer(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }

        return areaSize;
    }
}

