import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] dp;
    static String longS;
    static String smallS;
    static int minL;
    static int maxL;
    public static void main(String[] args) throws IOException {
        /*
            최장 공통 부분 수열 구하기
            ex) ACAYKP, CAPCAK의 LCS = ACAK

            1. 길이가 같은 수열의 경우
            dp[n] = 1부터 n index까지 비교했을 때, 가장 긴 LCS 길이
            dp[n] = Math.max(dp[1], ... , dp[n-1])

            2. 길이가 다른 두 수열 경우 -> 작은 수열에서 큰 수열의 공통 부분 수열을 찾기

         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        minL = Math.min(s1.length(), s2.length());
        maxL = Math.max(s1.length(), s2.length());
        dp = new int[maxL];
        if (maxL == s1.length()){
            longS = s1;
            smallS = s2;
        }
        else{
            longS = s2;
            smallS = s1;
        }
        System.out.println(lcs(minL-1));
        System.out.println(Arrays.toString(dp));

    }

    public static int lcs(int index){
        if (index < 0){
            return 0;
        }
        else if (dp[index] != 0){
            return dp[index];
        }

        if (maxL != minL){
            diffLengthLcs(index, maxL);
        } else {
            if (longS.charAt(index) == smallS.charAt(index))
                dp[index] = Math.max(lcs(index-1)+1, dp[index]);

            for (int i = index-1; i>= 0; i--){
                if (longS.charAt(index) == smallS.charAt(i) ||
                    smallS.charAt(index) == longS.charAt(i) ){
                    dp[index] = Math.max(lcs(i)+1, dp[index]);
                }
            }
        }

        dp[index] = Math.max(lcs(index-1), dp[index]);


        return dp[index];
    }

    public static int diffLengthLcs(int index, int beforeI){
        if (index < 0){
            return 0;
        } else if (dp[index] != 0){
            return dp[index];
        }

        for (int i = maxL-1; i >=0; i--) {
            if (smallS.charAt(index) == longS.charAt(i)){
                if (beforeI > i){
                    dp[index] = Math.max(diffLengthLcs(i-1, i) + 1,
                        diffLengthLcs(index-1, maxL));
                } else {
                    dp[index] = Math.max(diffLengthLcs(i-1, i) + 1,
                        dp[index]);
                }
            }
        }

        if (dp[index] == 0)
            dp[index] = Math.max(diffLengthLcs(index-1, beforeI), dp[index]);

        return dp[index];
    }


}