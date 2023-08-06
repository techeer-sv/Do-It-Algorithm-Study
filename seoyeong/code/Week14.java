// 이친수
// https://www.acmicpc.net/problem/2193

// 1 = 1
// 10 = 2
// 100, 101 = 2
// 1000, 1001, 1010 = 3 = 2+1
// 10000, 10001, 10010, 10100, 10101 = 5 = 3+2
// 100000, 100010, 100100, 101000, 101010, 100001, 100101, 101001 = 8 = 5+3
// 1000000, 1000100, 1001000, 1010000, 1010100, 1000010, 1001010, 1010010, 1000001, 1000101, 1001001, 1010001, 1010101 = 13 = 8+5


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoitJava_Week14 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long [] cnt = new long[91];
        cnt[1] = 1;
        cnt[2] = 1;
        for (int i=3; i<=n; i++) {
            // 전에 꺼에서 뒤에 0만 붙인 것 + 전에 꺼에서 뒤에 1 붙인 것 (가능한 것 중에서)
            cnt[i] = cnt[i-1] + cnt[i-2];
        }
        System.out.println(cnt[n]);
    }
}
