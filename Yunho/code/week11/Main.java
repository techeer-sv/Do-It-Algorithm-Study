package Yunho.code.week11;
//https://www.acmicpc.net/problem/1516
//게임 개발

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] time; // 건설 시간
        int[] inDegree; // 선행 건물 수
        int[] result; // 완성 시간
        ArrayList<Integer>[] graph; // 선행 건물 그래프
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        
        time = new int[N + 1];
        inDegree = new int[N + 1];
        result = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int preBuilding = Integer.parseInt(st.nextToken());
                if (preBuilding == -1) break;
                graph[preBuilding].add(i);
                inDegree[i]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                result[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int building = queue.poll();
            for (int nextBuilding : graph[building]) {
                result[nextBuilding] = Math.max(result[nextBuilding], result[building] + time[nextBuilding]);

                if (--inDegree[nextBuilding] == 0) {
                    queue.offer(nextBuilding);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}