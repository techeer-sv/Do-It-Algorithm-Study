// 게임 개발
// https://www.acmicpc.net/problem/1516

// 위상정렬
// 진입차수가 0인 노드를 큐에 넣는다.
// 큐가 빌 때까지 다음의 과정을 반복한다.
//① 큐에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거
//② 새롭게 진입차수가 0이 된 노드를 큐에 삽입

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class DoitJava_Week11 {
    /*
10 20 14 18 17

5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
    */


    /*
2 6 2 4 2

5
2 -1
2 1 5 -1
2 -1
2 3 -1
2 -1
*/

        /*
2 10 6 4 2

5
2 -1
2 1 3 5 -1
2 4 -1
2 5 -1
2 -1
*/


    public static void main(String[]args) throws IOException {
        // 변수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> buildings = new ArrayList<>(); // 빌딩 짓는 데 걸리는 시간 및 선행되어야 하는 건물

        // 입력 + buildings + ans
        for (int i = 0; i < n; i++) {
            String[] t1 = br.readLine().split(" ");
            Integer [] t2 = new Integer[t1.length];
            for (int j=0; j<t1.length; j++){
                t2[j] = Integer.parseInt(t1[j]);
            }
            buildings.add(Arrays.asList(t2));
        }


        // 위상 정렬
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0; // 빌딩을 짓는 데 걸리는 총 시간
        int [] visited = new int[n]; // 이미 선행된 빌딩이었는지 확인
        for (int i=0; i<n; i++) {
            // 진입 차수가 0일 때
            if (buildings.get(i).size()<3){
                System.out.println(buildings.get(i).get(0));
                continue;
            }

            // 진입 차수가 0보다 클 때
            queue.add(i);
            while (!queue.isEmpty()) {
                int k = queue.poll();
                if (visited[k] == 0) {
                    cnt += buildings.get(k).get(0); // 빌딩을 짓는 데 걸리는 기본 시간
                    visited[k] = 1;
                    int j = 1;
                    while (buildings.get(k).get(j) > -1) {
                        int next = buildings.get(k).get(j)-1; // 인덱스 값이라 -1 해줘야함
                        if (visited[next] == 0) queue.add(next);
                        j++;
                    }

                }
            }
            System.out.println(cnt);
            cnt = 0;
            Arrays.fill(visited,0);
        }
    }
}
