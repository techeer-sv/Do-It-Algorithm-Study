// LCS
// https://www.acmicpc.net/problem/9251
// 정답 https://st-lab.tistory.com/139

/*
ACAYKP
CAPCAK
4
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoitJava_Week14_2 {
    static char[] str1;
    static char[] str2;
    static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        dp = new Integer[str1.length][str2.length];
        System.out.println(LCS(str1.length - 1, str2.length - 1));
    }

    static int LCS(int x, int y) {
        // 인덱스 밖 (공집합)일 경우 0 반환
        if (x == -1 || y == -1) return 0;

        // 만약 탐색하지 않은 인덱스라면?
        if (dp[x][y] == null) {
            dp[x][y] = 0;

            // str1의 x번째 문자와 str2의 y번째 문자가 같은지 검사
            if (str1[x] == str2[y]) {
                dp[x][y] = LCS(x - 1, y - 1) + 1;
                return dp[x][y];
            }
            // 같지 않다면 LCS(dp)[x-1][y]와 LCS(dp)[x,y-1] 중 큰 값으로 초기화
            dp[x][y] = Math.max(LCS(x - 1, y), LCS(x, y - 1));
        }
        return dp[x][y];
    }
}
