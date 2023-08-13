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
