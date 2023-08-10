//https://www.acmicpc.net/problem/5557
// 1학년


import java.util.*;
import java.io.*;
/**
 * 방향성 그림은 pr에 올려두었습니다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N - 1][21];
        dp[0][arr[0]] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int num = 0; num <= 20; num++) {
                if (num - arr[i] >= 0) {
                    dp[i][num] += dp[i - 1][num - arr[i]];
                }
                if (num + arr[i] <= 20) {
                    dp[i][num] += dp[i - 1][num + arr[i]];
                }
            }
        }

        bw.write(Long.toString(dp[N - 2][arr[N - 1]]));
        bw.flush();
        br.close();
        bw.close();
    }
}
