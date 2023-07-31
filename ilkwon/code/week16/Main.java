

import java.util.*;

class Main{
    public static void main(String argc[]){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] arr = new int[N];
        long[][] dp = new long[N][21];
        for(int i=0; i<N; i++){
            arr[i] = scan.nextInt();
        }
        int min = 0; // -일 경우
        int plus = 0; // +일 경우

        dp[0][arr[0]] = 1;
        for(int i=1; i<N-1; i++){
            for(int j=0; j<21; j++){
                plus += j+arr[i];
                min -= j+arr[i];
            }
        }

    }

}