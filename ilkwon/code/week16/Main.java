import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] array = new int[N];

        for (int i=0; i<N; i++) {
            array[i] = scan.nextInt();
        }

        long[][] dp = new long[N][21];
        dp[0][array[0]] = 1; // 처음 시작점 경로 1

        for (int i=1; i<N-1; i++) {
            for (int j=0; j<21; j++) {
                if (dp[i-1][j]>0) { //이전 경로가 0보다 큰 경우
                    if (j+array[i]<=20) {
                        dp[i][j+array[i]] += dp[i-1][j];
                    }
                    if (j-array[i] >= 0) {
                        dp[i][j-array[i]] += dp[i-1][j];
                    }
                }
            }
        }
        System.out.println(dp[N-2][array[N-1]]);
    }
}
