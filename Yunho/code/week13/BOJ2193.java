// https://www.acmicpc.net/problem/2193
// 이천수

import java.io.*;
import java.util.*;

public class Main {
    /**
     * 처음에는 이친수에 해당하는 규칙을 찾으면 되나 싶었는데 경우의 수가 2^90을 고려해야한다.
     * 문제를 다시 읽어보니 개수만 구하면 되므로 앞 수가 0일경우 1인 경우를 고려해서 점화식을 세움
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        long dp[] = new long[N+2];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for(int i = 3; i<= N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        bw.write(Long.toString(dp[N]));
        bw.flush();
        br.close();
        bw.close();
    }
}