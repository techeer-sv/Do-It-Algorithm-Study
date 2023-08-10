import java.util.*;
import java.io.*;

class LCS {
    static char[] str1;
    static char[] str2;
    static Integer[][] dp;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] argc) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray(); // 문자열 1 입력
        str2 = br.readLine().toCharArray(); // 문자열 2 입력

        dp = new Integer[str1.length][str2.length];


        System.out.println(lcs(str1.length-1, str2.length-1));
    }
    public static int lcs(int a, int b) {

        if(a<0||b<0)
            return 0; //비교 불가 -> 공통문자열이 만들어 질 수 없을때

        if(dp[a][b]==null) { // 결과 계산이 아직되지 않았을 때
            dp[a][b]=0;
            if(str1[a] == str2[b]) // 해당 인덱스에 존재하는 문자가 같다면,
                dp[a][b] = lcs(a-1, b-1)+1; // 두 문자가 LCS에 포함된다 -> dp[a][b]에 저장
            else
                // 두 문자가 다르다면 계속해서 찾는 과정 진행 -> lcs(a-1, b)와 lcs(a, b-1) 중에서 더 큰 값을 선택하여 저장
                dp[a][b] = Math.max(lcs(a-1,b), lcs(a,b-1));
        }

        return dp[a][b];
    }

}