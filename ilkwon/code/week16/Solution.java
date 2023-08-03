class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {

        int answer = Integer.MAX_VALUE;
        int[][] distance = new int[n][n]; // 지점사이 최소 택시요금

        for(int i=0;i<n;i++){ // 최소 전 제일 큰값으로 초기화
            for(int j=0;j<n;j++){
                if(i==j) continue;
                distance[i][j]=10000000;
            }
        }

        for(int i=0;i<fares.length;i++){  // 모든 지점사이 최소요금 저장(양방향)
            //dist[a][0]출발지 ->dist[b][0]도착지 = 요금
            distance[fares[i][0]-1][fares[i][1]-1]= fares[i][2];
            distance[fares[i][1]-1][fares[i][0]-1]= fares[i][2];
        }

        //s->a->b 지점 최소값
        for(int i=0;i<n;i++){
            answer= Math.min(answer,distance[s-1][i]+distance[i][a-1]+distance[i][b-1]);
        }


        return answer;
    }
}