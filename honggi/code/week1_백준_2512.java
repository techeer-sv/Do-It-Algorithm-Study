import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2512 {
    public static void main(String[] args) throws IOException {
        /*
            1. 총합이 예산보다 적은 경우 -> 요청 금액 중 최대값 산출
            2. 총합이 예산보다 큰 경우 -> 이분 탐색을 이용한 상한액 찾기
                상한액 결정 방법
                1. left부터 n까지 총 예산 금액 budget에 대한 평균 금액 avg(=k/n) 측정
                2. avg 보다 높은 지방의 index 찾기 (이분 탐색 이용)
                    - avg와 같은 요청 금액이 존재하는 경우, 해당 지방의 요청까지 포함 (= index++)
                3. left부터 index-1까지 모든 지방의 요구 금액을 예산 금액에서 빼기
                4. left 갱신 (= index 대입)
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wants = new int[n];
        int total = 0, left = 0, index = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            wants[i] = Integer.parseInt(st.nextToken());
            total += wants[i];
        }
        int budget = Integer.parseInt(br.readLine());

        Arrays.sort(wants);
        int avg = budget/n;

        if (total <= budget)    // 총 합이 예산보다 적은 경우
            System.out.println(wants[n - 1]);
        else {
            while (index < n && wants[index] <= avg) {
                index = Arrays.binarySearch(wants, index, n , avg);
                index = index >= 0 ? (index+1) : -(index+1);

                for (int i = left; i < index; i++){
                    budget -= wants[i];
                }
                left = index;
                avg = budget/(n - left);
            }
            System.out.println(avg);
        }
    }
}
