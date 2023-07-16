/*
i=1 일 때 1 -> 1개
i=2 일 때 10 -> 1개
i=3 일 때 100, 101 -> 2개
i=4 일 때 1000, 1001, 1010 -> 3개
i=5 일 때 10000, 10001, 10101, 10100, 10010 -> 5개
//피보나치와 동일
*/

import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        long[] dp = new long[N+1]; //N=90이면 int초과

        //초기화(0,1일때)
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[n]);

    }
}