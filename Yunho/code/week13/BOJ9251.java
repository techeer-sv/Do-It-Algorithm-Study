//https://www.acmicpc.net/problem/9251
//LCS

import java.util.*;
import java.io.*;
/**
 * https://propercoding.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-LCS-%EC%B5%9C%EC%9E%A5-%EA%B3%B5%ED%86%B5-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4
 * https://st-lab.tistory.com/139
 * lcs 개념 참고 링크
 * 
 * 이차원 배열을 선언해서 각각을 비교해 같은 문자일시 + 1을 해서 나중에 최종 반대편 항에 나오는 값이 LCS이다
 * 비슷한 개념으로 LIS라는 개념이 있다. -> 한쪽을 숫자로 두고 계산 
 */


public class Main {

    static char[] str1;
    static char[] str2;
    static Integer[][] dp;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();

        dp = new Integer[str1.length][str2.length];

        bw.write(Integer.toString(lcs(str1.length - 1, str2.length - 1)));
        bw.flush();
        br.close();
        bw.close();
    }

    public static int lcs(int a, int b) {

        if (a < 0 || b < 0)
            return 0;

        if (dp[a][b] == null) {
            dp[a][b] = 0;
            if (str1[a] == str2[b])
                dp[a][b] = lcs(a - 1, b - 1) + 1;
            else
                dp[a][b] = Math.max(lcs(a - 1, b), lcs(a, b - 1));
        }

        return dp[a][b];
    }
}
