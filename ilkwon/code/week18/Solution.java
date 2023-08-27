/*
import java.util.*;

class Solution {
    int max = 0;

    public int solution(int[][] triangle) {
        calculate(triangle, 0, 0, 0);
        return max;
    }

    private void calculate(int[][] triangle, int row, int col, int sum) {
        if (row == triangle.length) {
            max = Math.max(max, sum);
            return;
        }

        sum += triangle[row][col];
        calculate(triangle, row + 1, col, sum);
        calculate(triangle, row + 1, col + 1, sum);
    }
}
*/

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][triangle.length + 1];

        dp[0][1] = triangle[0][0];

        for(int i = 1 ; i < triangle.length; i++) {
            for(int j = 1; j <= triangle[i].length; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j - 1];
                answer = Math.max(answer, dp[i][j]);
            }
        }
        return answer;
    }
}

