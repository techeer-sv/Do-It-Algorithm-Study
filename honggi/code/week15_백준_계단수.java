import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
        풀이 참고 - https://gose-kose.tistory.com/107
        계단 수 - 인접한 모든 자리 차이 1

        dp와 비트마스킹을 이용한 문제
        3차원 배열을 사용, dp[자리수][숫자][숫자에 포함된 수]
        (bit masking, 1023 -> 1111111111, 0부터 9까지 모든 수가 포함됨을 의미)

        dp[2][3][(1100)]
        = 2자리 수에 3이 오면서 (2,3)을 사용한 수
        = 23, 43

        계단 수에 적용 -> i 자리에 0~9중 하나인 수 j가 왔을 때, 비트마스킹을 통해 0~9의 숫자를 방문처리
        if j == 1 : dp[i][j][k | 1 << j] += dp[n-1][i+1][k]
        if j == 9 : dp[i][j][k | 1 << j] += dp[n-1][i-1][k]
        else      : dp[i][j][k | 1 << j] += dp[n-1][i-1][k] + dp[n-1][i+1][k]

     */
    static long[][][] dp;
    static int divideN = 1_000_000_000;
    static int bit = 1 << 10;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new long[n+1][10][bit];
        if (n < 10){
            System.out.println(0);
        } else {
            System.out.println(countStair(n));
        }
    }

    private static long countStair(int digit) {
        // 첫 번째 자리에 올 수 있는 수 = 1~9
        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= digit; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bit; k++) {
                    int Bit = k | (1 << j); // k수에 숫자 j가 포함되어있음을 비트연산으로 계산 (숫자 j를 포함한 수가 됨)

                    if (j == 0 || j == 9){
                        dp[i][j][Bit] = j == 0 ? (dp[i][j][Bit] + dp[i-1][j+1][k]) % divideN
                            : (dp[i][j][Bit] + dp[i-1][j-1][k]) % divideN;
                    } else {
                        dp[i][j][Bit] = (dp[i][j][Bit] + dp[i-1][j-1][k] + dp[i-1][j+1][k]) % divideN;
                    }
                }
            }
        }

        // n자리 수에 대해서, 0부터 9까지 숫자로 끝나는 숫자 중 0~9가 모두 포함된 수를 모두 더함
        long result = 0;
        for (int i = 0; i < 10; i++) {
            result = (result + dp[digit][i][bit-1]) % divideN;
        }
        return result;
    }
}