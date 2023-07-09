import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    static HashMap<Integer, LinkedList<Integer>> preConstructed;
    static int[] dp;
    static int[] buildInfo;

    public static void main(String[] args) throws IOException {
        /*
            게임 개발 - https://www.acmicpc.net/problem/1516

            건물을 짓기 위해 먼저 지어야 하는 건물 존재 가능 
                -> dfs와 dp를 이용하여 최단 시간 + 메모리 활용

            1. 건물마다 짓는데 걸리는 시간, 먼저 지어야 하는 건물 정보 분류하기
                먼저 지어야 하는 건물 정보가 없는경우 -> 건물 짓는데 걸리는 시간이 최소 시간

            2. 건물 짓기 (dfs)
                1) 먼저 지어야 하는 건물들 중, 가장 오래걸리는 건물 시간 구하기
                2) 해당 건물을 짓는데 걸리는 시간 더하기

         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        preConstructed = new HashMap<>(n);
        buildInfo = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int buildTime = Integer.parseInt(line[0]);
            LinkedList<Integer> preInfo = new LinkedList<>();

            if (line.length == 2) {
                dp[i] = buildTime;
            }

            for (int j = 1; j < line.length - 1; j++) {
                preInfo.add(Integer.parseInt(line[j]) - 1);
            }

            buildInfo[i] += buildTime;
            preConstructed.put(i, preInfo);
        }

        for (int i = 0; i < n; i++) {
            dp[i] = dfs(i);
            System.out.println(dp[i]);
        }

    }

    public static int dfs(int index) {
        if (dp[index] != 0) {
            return dp[index];
        }

        LinkedList<Integer> pc = preConstructed.get(index);
        int buildTime = 0;

        // 먼저 지어야 하는 건물 리스트가 다 지어지는 경우 -> 가장 지어지는 시간이 오래걸리는 건물 찾기
        for (Integer num : pc) {
            buildTime = Math.max(buildTime, dfs(num));
        }

        dp[index] = buildTime + buildInfo[index];

        return dp[index];
    }

}