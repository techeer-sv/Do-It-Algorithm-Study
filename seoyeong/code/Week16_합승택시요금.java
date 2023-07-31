// 합승 택시 요금
// https://school.programmers.co.kr/learn/courses/30/lessons/72413

/*
n	s	a	b	fares	                         result
6	4	6	2	[[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]]	82
7	3	4	1	[[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]]	14
6	4	5	6	[[2,6,6], [6,3,7], [4,6,7], [6,5,11], [2,5,12], [5,3,20], [2,4,8], [4,3,9]]	18
* */


import java.util.*;

public class DoitJava_Week16_1 {
    public static void main(String[] args) {
        int p1 = solution(6,4,6,2,new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
        System.out.println(p1);  // 82
        int p2 = solution(7,3,4,1,new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
        System.out.println(p2);  // 14
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int p1, p2, cost;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> connectedPath = new ArrayList<>(n+1);
        ArrayList<ArrayList<Integer>> connectedWeight = new ArrayList<>(n+1);
        for (int i = 0; i < n + 1; i++) {
            connectedPath.add(new ArrayList<>());
            connectedWeight.add(new ArrayList<>());
        }

        for (int i=0; i<fares.length; i++){
            p1 = fares[i][0];
            p2 = fares[i][1];
            cost = fares[i][2];
            connectedPath.get(p1).add(p2);
            connectedPath.get(p2).add(p1);
            connectedWeight.get(p1).add(cost);
            connectedWeight.get(p2).add(cost);
        }
        queue.add(s);
        boolean [] visited = new boolean[n+1];  // false 로 자동초기화

        // System.out.println(connectedPath.toString());

        while (visited[a] == false || visited[b] == false){
            p1 = queue.poll();
            visited[p1] = true;

            /*
            // 바로 갈 수 있는 노드 중 a, b 도착지가 있다면
            if (connectedPath.get(p1).contains(a) || connectedPath.get(p1).contains(b)) {
                if (connectedPath.get(p1).contains(a)) {
                    int idx = connectedPath.get(p1).indexOf(a);
                    visited[idx] = true;
                    answer += connectedWeight.get(p1).get(idx);
                    queue.add(a);
                }
                if (connectedPath.get(p1).contains(b)) {
                    int idx = connectedPath.get(p1).indexOf(b);
                    visited[idx] = true;
                    answer += connectedWeight.get(p1).get(idx);
                    queue.add(b);
                }
            }
            // 없다면 가장 최단거리 선택
            else {*/
                ArrayList<Integer> paths = connectedPath.get(p1);
                ArrayList<Integer> costs = connectedPath.get(p1);
                int idxMinCosts = -1;
                int minCosts = 100001;
                for (int i = 0; i < costs.size(); i++) {
                    if (costs.get(i) < minCosts) {
                        idxMinCosts = i;
                        minCosts = costs.get(i);
                    }
                }
                queue.add(paths.get(idxMinCosts));
                answer += minCosts;
            }
        return answer;
    }
}
