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
            모든 건물을 짓는데 걸리는 최소 시간 구하기
            건물을 짓기 위해 우선적으로 지어야 하는 건물 존재 가능 -> dp를 이용한 풀이
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

        for (Integer num : pc) {
            buildTime += dfs(num);
        }

        dp[index] = buildTime + buildInfo[index];

        return dp[index];
    }

}