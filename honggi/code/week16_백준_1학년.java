import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] nums;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[n];
        dp = new long[n][21];

        for (int i = 0; st.hasMoreTokens(); i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        dp[0][nums[0]] = 1;

        long result = calNum();
        System.out.println(result);
    }

    private static long calNum() {
        int index = 1;
        while (index < n-1){
            for (int i = 0; i < 21; i++) {
                if (dp[index-1][i] != 0){
                    int nextPlus = i + nums[index];
                    int nextMinus = i - nums[index];

                    if (nextPlus >= 0 && nextPlus <= 20){
                        dp[index][nextPlus] += dp[index-1][i];
                    }

                    if (nextMinus >= 0 && nextMinus <= 20){
                        dp[index][nextMinus] += dp[index-1][i];
                    }
                }
            }
            index++;
        }

        return dp[n-2][nums[n-1]];
    }
}