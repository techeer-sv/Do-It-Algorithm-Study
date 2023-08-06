// 합승 택시 요금
// https://school.programmers.co.kr/learn/courses/30/lessons/72413

/*
n	s	a	b	fares	                         result
6	4	6	2	[[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]]	82
7	3	4	1	[[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]]	14
6	4	5	6	[[2,6,6], [6,3,7], [4,6,7], [6,5,11], [2,5,12], [5,3,20], [2,4,8], [4,3,9]]	18
*/

public class DoitJava_Week16_1 {
    static int N, M;
    static int[][] dist;

    public static void main(String[] args) {
        int p1 = solution(6,4,6,2,new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
        System.out.println(p1);  // 82
        int p2 = solution(7,3,4,1,new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
        System.out.println(p2);  // 14
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        dist = new int[N+1][N+1];
        M = fares.length;

        // 플로이드 초기 거리 테이블 초기화
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < N+1; j++) {
                // 자기 자신으로 가는 길은 최소 비용이 0이다.
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }
                // 자기 자신으로 가는 경우를 제외하고는 매우 큰 값(N개의 노드를 모두 거쳐서 가더라도 더 큰 값).
                dist[i][j] = 100000000;
            }
        }
        for (int i = 0; i < M; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            // 가는 경로가 하나가 아닐 수 있다. 따라서 그 중 최소 비용을 저장해두면 된다.
            // System.out.println(start + " "+ end + " " + cost);
            dist[start][end] = Math.min(dist[start][end], cost);
            dist[end][start] = Math.min(dist[end][start], cost);
        }

        // 플로이드 워셜 알고리즘
        // 노드를 1개부터 N개까지 거쳐가는 경우를 모두 고려한다.
        for (int k = 0; k < N+1; k++) {
            // 노드 i에서 j로 가는 경우.
            for (int i = 0; i < N+1; i++) {
                for (int j = 0; j < N+1; j++) {
                    // k번째 노드를 거쳐가는 비용이 기존 비용보다 더 작은 경우 갱신
                    // 또는 연결이 안되어있던 경우(INF) 연결 비용 갱신.
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 최소 합승 요금
        boolean [] visited = new boolean[N+1];

        visited[s] = true;
        int ans = dist[s][a] + dist[s][b];  // 101 (직빵으로 가면 101)
        int x_idx = s;
        int x_val = 100000000;
        int y_idx = 0;
        int c = 0;


        // N번 반복
        for (int i = 0; i < N; i++){
            // 현재 노드에서 이동할 수 있는 최소 거리
            for (int j = 1; j < N+1; j++){
                if (dist[x_idx][j] > 0 && dist[x_idx][j] < x_val && visited[j]==false){
                    y_idx = j;
                    x_val = dist[x_idx][j];
                }
            }

            if (x_val == 100000000) break;
            c += x_val; x_val = 100000000;
            x_idx = y_idx; y_idx = 0;
            visited[x_idx] = true;

            // System.out.println((i+1)+"번째 "+"ans:"+ans + " c:" + c + " idx:"+ x_idx + " " + dist[x_idx][a] + " " + dist[x_idx][b]);
            ans = Math.min(ans, c + dist[x_idx][a] + dist[x_idx][b]);
        }

        return ans;
    }
}
