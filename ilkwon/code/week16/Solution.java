class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {

        int answer = Integer.MAX_VALUE;
        int[][] distance = new int[n][n]; // 지점사이 최소 택시요금

        for (int i = 0; i < n; i++) { // 최소 전 제일 큰값으로 초기화
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distance[i][j] = 0; // 대각선 상에는 0으로 초기화
                } else {
                    distance[i][j] = 10000000; // 문제 조건에 맞게 초기화 값 설정
                }
            }
        }

        for (int i = 0; i < fares.length; i++) { // 모든 지점사이 최소요금 저장(양방향)
            distance[fares[i][0] - 1][fares[i][1] - 1] = fares[i][2];
            distance[fares[i][1] - 1][fares[i][0] - 1] = fares[i][2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, distance[s - 1][i] + distance[i][a - 1] + distance[i][b - 1]);
        }

        return answer;
    }
}
