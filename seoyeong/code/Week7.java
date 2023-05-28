// 게임 맵 최단거리
// https://school.programmers.co.kr/learn/courses/30/lessons/1844

import java.util.LinkedList;
import java.util.Queue;

public class Week7{
    public static void main(String[]args){
        System.out.println(solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));  // 11
        /*
        * 1,0,1,1,1
        * 1,0,1,0,1
        * 1,0,1,1,1
        * 1,1,1,0,1
        * 0,0,0,0,1
        * */
    }

    public static int dfs(int[][]maps,int x,int y){
        // 동서남북, x는 행 y는 열
        int [] dx = new int[]{0,0,-1,1};
        int [] dy = new int[]{1,-1,0,0};
        Queue queue = new LinkedList();


        // 이동할 수 있는 값 구하기
        for (int i=0; i<4; i++){
            x += dx[i];
            y += dy[i];
            if (x >= 0 && x < maps.length && y >= 0 && y < maps[0].length){
                if (maps[x][y] > 0) {
                    queue.add(new int[]{x, y});
                    maps[x][y] += 1;
                    x -= dx[i];
                    y -= dy[i];
                }
            }
        }

        while (queue.size()>0){
            int [] pos = (int[]) queue.poll();
            dfs(maps,pos[0],pos[1]);
        }

        return maps[x][y];
    }

    public static int solution(int[][] maps) {
        int answer = dfs(maps,0,0);
        return answer;
    }
}
