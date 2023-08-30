import java.util.*;
// 참고하여 풀이

class Solution {
    public static int solution(int n, int[][] costs) {
        int answer = 0;
        int[][] costList = new int[n][n];
        int minCost = costs[0][2];
        int minIslandFrom = costs[0][0];
        int minIslandTo = costs[0][1];

        List<Boolean> visited = new ArrayList<>(); //방문여부
        for (int i = 0; i < n; ++i) { //초기화
            visited.add(false);
        }

        //비용 정보를 통해 최소 비용과 그에 해당하는 출발/도착 노드를 설정
        for (int i = 0; i < costs.length; ++i) {
            costList[costs[i][0]][costs[i][1]] = costs[i][2];
            costList[costs[i][1]][costs[i][0]] = costs[i][2];

            // 크루스칼 알고리즘의 출발 지점을 찾기 위한 과정
            if (costs[i][2] < minCost) {
                minCost = costs[i][2]; //현재까지 선택한 간선들 중 가장 작은 비용을 가진 간선
                minIslandFrom = costs[i][0];
                minIslandTo = costs[i][1];
            }
        }

        //모든 노드를 방문할 때까지 반복
        // minCost로 선택한 최소 비용 간선을 더하고 해당 노드들을 방문한 것으로 표시
        //이미 방문한 노드 중에서 미방문한 노드로 가는 간선 중 최소 비용의 간선을 선택
        while (visited.contains(false)) {
            answer += minCost;
            visited.set(minIslandFrom, true);
            visited.set(minIslandTo, true);
            minCost = (int)1e9;

            for (int i = 0; i < n; ++i) {
                if (visited.get(i) == true) {
                    for (int j = 0; j < n; ++j) {
                        if (costList[i][j] != 0 && visited.get(j) == false && minCost > costList[i][j]) {
                            minCost = costList[i][j];
                            minIslandFrom = i;
                            minIslandTo = j;
                        }
                    }
                }
            }
        }

        return answer;
    }
}