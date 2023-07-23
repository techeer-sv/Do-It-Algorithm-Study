// 계단수
// https://www.acmicpc.net/problem/1562
// 10 1
// 길이가 N, N=1일때부터, N=40일 때 까지 답을 모두 더하면 126461847755이 나온다.

// n=1 => 0
// n=2 12 10         21 23     34 32 45 43 56 54 67 65 78 76 89 87 98 => 17
// n=3 123 121 101   2


// 126 461 847 755
// 4 123 168 604 200

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoitJava_Week15 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());  // 1 ~ 100
        long [] answer = new long[101];
        answer[1] = 0L;
        answer[2] = 17L; // 2*8 + 1 ? 17
        answer[3] = 32L; // 3 + 4*6 + 3 + 2 ?  17 + 15
        answer[4] = 61L; // 6 + 7 + 8*4 + 7 + 6 + 3 ? 32 +29
        answer[5] = 116L; // 10 + 14 + 15 + 16*2 + 15 + 14 + 10 + 6  ? 61 + 55

        for (int i=6; i<n; i++){
            answer[i] = answer[i-1]*2 - (i-2);
        }

        for (int i=1; i<41; i++){
            System.out.println("i= "+i + ", "+ answer[i]);
        }
        //System.out.println(answer[n] % 1000000000);
    }
}
