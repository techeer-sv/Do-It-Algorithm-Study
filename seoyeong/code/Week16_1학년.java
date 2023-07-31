// 1학년
// https://www.acmicpc.net/problem/5557

// 메모이제이션 예제 -> https://subinium.github.io/Algorithm/docs/chapter03/6.html

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DoitJava_Week16_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String [] orgNums = br.readLine().split(" ");
        int [] nums = new int[orgNums.length];
        for (int i=0; i<n; i++){
            nums[i] = Integer.parseInt(orgNums[i]);
        }

        // 메모이제이션
        // 세로 n, 가로 21(0<=x<=20) 사이즈 배열 생성
        long [][] dp = new long[n][21];
        dp[0][nums[0]] = 1; // 첫 행에는 8열에 1 인입

        // 1행부터 +, - 한 값의 열에 개수를 추가한다. +1
        for (int i=1; i<n; i++){
            for (int j=0; j<21; j++){
                if (dp[i-1][j] != 0){
                    // 이전의 값에서 + 한 값이 조건을 충족하면, 현재 행에 개수 추가
                    // 8 + 3 = 11, 이전 값 1 그대로 추가
                    if (j + nums[i] >= 0 && j + nums[i] <= 20) dp[i][j + nums[i]] += dp[i - 1][j];
                    // 이전의 값에서 - 한 값이 조건을 충족하면, 현재 행에 개수 추가
                    // 8 - 3 = 5, 이전 값 1 그대로 추가
                    if (j - nums[i] >= 0 && j - nums[i] <= 20) dp[i][j - nums[i]] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[n-2][nums[n-1]]);   // nums의 마지막 수와 같은 값이어야 한다(등호)
    }
}


/*
// 첫 시도, 큐에 계산한 값을 계속 계산 및 저장해가면서 최종의 값 개수를 셈
// n이 많아지면, 시간이 너무 오래 걸림

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class DoitJava_Week16_2 {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String [] orgNums = br.readLine().split(" ");
        int [] nums = new int[orgNums.length];

        int x, plusX, minusX;
        int idx = 1;
        for (int i=0; i<orgNums.length; i++){
            nums[i] = Integer.parseInt(orgNums[i]);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(nums[0]);

        while (idx < orgNums.length-1){
            Queue<Integer> tempQueue = new LinkedList<>();
            while (queue.size() > 0) {
                x = queue.poll();
                plusX = x + nums[idx];
                minusX = x - nums[idx];
                if (plusX >= 0 && plusX <= 20) tempQueue.add(plusX);
                if (minusX >= 0 && minusX <= 20) tempQueue.add(minusX);
            }
            queue.addAll(tempQueue);
            idx++;
        }

        System.out.println(queue.toString());

        while (queue.size()>0){
            if (nums[nums.length-1] == queue.poll())
                answer++;
        }

        System.out.println(answer);
    }
}
*/
