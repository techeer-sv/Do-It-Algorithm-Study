// https://school.programmers.co.kr/learn/courses/30/lessons/72413
// 합승 택시 요금 
import java.util.*;

/**
 * 모든 경우의 수를 다 따져서 계산을 해야 하나?
 * 음 뭔가 안됨
 */

public class Solution {

    static final int MAX = 20000000;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 주어진 간선 및 노드를 2차원 배열에 표현하기 위한 세팅
        int arr[][] = new int[n+1][n+1];
        for(int i=0; i<n+1; i++){
            Arrays.fill(arr[i], MAX);
        }

        // 가중치를 배열에 표현
        for(int i = 0; i < fares.length; i++){
            for(int j=0; j<fares[i].length; j++){
                int c = fares[i][0];
                int d = fares[i][1];
                int weight = fares[i][2];
                arr[c][d] = weight;
                arr[d][c] = weight;
            }
        }

        // 경로중 가중치가 작은 부분을 선별 
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < n+1; j++) {
                for(int k = 1; k < n+1; k++) {
                    arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                }
            }
        }

        // return arr[s][a] + arr[s][b];

        int answer = arr[s][a] + arr[s][b];
        for (int i = 0; i <= n; i++){
            answer = Math.min(answer, arr[s][i] + arr[a][i] + arr[b][i]);
        }
        return answer;
    }
}
