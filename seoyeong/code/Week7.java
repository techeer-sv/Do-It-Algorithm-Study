// 게임 맵 최단거리
// https://school.programmers.co.kr/learn/courses/30/lessons/1844

import java.util.LinkedList;

public class Week7{
    public static void main(String[]args){
        // System.out.println(solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}})); // 11
        System.out.println(solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}})); // -1
        /*
        * 1,0,1,1,1
        * 1,0,1,0,1
        * 1,0,1,1,1
        * 1,1,1,0,1
        * 0,0,0,0,1
        * */
    }

    public static int searchPath(int[][]maps,int x,int y){
        // 동서남북, x는 행 y는 열
        int [] dx = new int[]{0,0,-1,1};
        int [] dy = new int[]{1,-1,0,0};
        LinkedList<Integer[]>queue = new LinkedList<>();
        queue.add(new Integer[]{x, y});

        while (queue.size()>0){
            Integer[] pos = queue.pollFirst();
            x = pos[0];
            y = pos[1];

            if (x==maps.length-1 && y==maps[0].length-1) break;

            System.out.println("\n"+x+" "+y);
            for (int i=0; i<maps.length; i++){
                for (int j=0; j<maps[0].length;j++){
                    System.out.print(maps[i][j]+" ");
                }
                System.out.println("");
            }




            // 이동할 수 있는 값 구하기
            for (int i=0; i<4; i++){
                int newX = x+dx[i];
                int newY = y+dy[i];
                if (newX >= 0 && newX < maps.length && newY >= 0 && newY < maps[0].length && maps[newX][newY]>0){
                    maps[newX][newY] = maps[x][y]+1;
                    queue.add(new Integer[]{newX, newY});
                }
            }
            maps[x][y] = 0; // 이미 지나온 길은 갈 수 없는 길 (0)으로 만들기
        }

        int max = 0;
        for (int i=0; i<maps.length; i++){
            for (int j=0; j<maps[0].length;j++){
                if (maps[i][j] > max) max = maps[i][j];
            }
        }

        if (maps[maps.length-1][maps[0].length-1] == max && max != 1) return max;
        return -1;
    }

    public static int solution(int[][] maps) {
        int answer = searchPath(maps,0,0);
        return answer;
    }
}
