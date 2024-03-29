import java.util.*;

public class Week18_섬연결하기 {
    static int[][] dist;

    public static void main(String[] args) {
        int p1 = solution(4,new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}});
        System.out.println(p1);  // 4
        int p2 = solution(7,new int[][]{{2, 3, 7}, {3, 6, 13}, {3, 5, 23}, {5, 6, 25}, {0, 1, 29}, {1, 5, 34}, {1, 2, 35}, {4, 5, 53}, {0, 4, 75}});
        System.out.println(p2);  // 159
        int p3 = solution(5,new int[][]{{0,1,5},{1,2,3},{2,3,3},{3,1,2},{3,0,4}, {2,4,6}, {4,0,7}});
        System.out.println(p3);  // 15
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        dist = new int[n+1][n+1];
        HashSet<String> hashSet = new HashSet<>();

        // 1. 플로이드 초기 거리 테이블 초기화
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                // 자기 자신으로 가는 길은 최소 비용이 0이다.
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }
                // 자기 자신으로 가는 경우를 제외하고는 매우 큰 값(N개의 노드를 모두 거쳐서 가더라도 더 큰 값).
                dist[i][j] = 100000000;
            }
        }
        for (int i = 0; i < n+1; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int cost = costs[i][2];
            dist[start][end] = Math.min(dist[start][end], cost);
            dist[end][start] = Math.min(dist[end][start], cost);
        }

        // 2. 플로이드 워셜 알고리즘 (각 노드에서 최단 거리)
        for (int k = 0; k < n+1; k++) {
            for (int i = 0; i < n+1; i++) {
                for (int j = 0; j < n+1; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 3. 각 노드의 최단거리인 노드들을 한 세트로 묶고, 그 거리를 카운트해둔다.
        int mini, idx, val;
        int [] cNode = new int[2];
        for (int i=0; i<n; i++){
            mini = 100000000;
            idx = 0;
            for (int j=0; j<n; j++){
                if (i == j) continue;
                // 각 노드 최단 거리인 노드 뽑기
                mini = Math.min(mini, dist[i][j]);
                if (mini == dist[i][j]) idx = j;
            }

            if (i < idx) cNode = new int[]{i, idx};
            else cNode = new int[]{idx, i};

            if (!hashSet.contains(cNode[0]+"-"+cNode[1])) answer += mini;
            hashSet.add(cNode[0]+"-"+cNode[1]);
        }




        // 출력
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (dist[i][j] == 100_000_000) {
//                    System.out.print("INF ");
//                } else {
//                    System.out.print(dist[i][j] + " ");
//                }
//            }
//            System.out.println();
//        }
//        System.out.println(hashSet);



        // 4. 이어지지 않은 노드가 있다면, 그에 맞는 최단 거리를 구한다.
        ArrayList<Set<Integer>> connected = new ArrayList<>();
        for (String c : hashSet) {
            String[] ca = c.split("-");
            int a = Integer.parseInt(ca[0]);
            int b = Integer.parseInt(ca[1]);

            boolean flag = false;
            for (int i = 0; i < connected.size(); i++) {
                Set<Integer> bp = connected.get(i);
                for (int j = 0; j < bp.size(); j++) {
                    if (bp.contains(a) || bp.contains(b)) {
                        bp.add(a);
                        bp.add(b);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                Set<Integer> ap = new HashSet<>();
                ap.add(a); ap.add(b);
                connected.add(ap);
            }
        }
        // System.out.println(connected);


        ArrayList<ArrayList<Integer>> arraylist = new ArrayList<>();
        for (int i=0; i<connected.size(); i++){
            arraylist.add(new ArrayList<Integer>(connected.get(i)));
        }





        if (connected.size() == 1) return answer;


        while (arraylist.size() > 1){
            int m = 9999999;
            for (int i=0; i<arraylist.get(0).size(); i++){
                int x = arraylist.get(0).get(i);
                int y;
                for (int j=0; j<arraylist.get(1).size(); j++){
                    y = arraylist.get(1).get(j);
                    if (!arraylist.get(0).contains(j) && dist[x][y] < m){
                        m = dist[x][y];
                    }
                }
            }
            answer += m;
            ArrayList<Integer> s = arraylist.get(1);
            arraylist.remove(1);
            arraylist.get(0).addAll(s);
        }
        return answer;
    }
}
