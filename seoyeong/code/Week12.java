// 동굴 탐험
// https://school.programmers.co.kr/learn/courses/30/lessons/67260

import java.util.*;

public class DoitJava_Week12 {
    /*
    9	[[8,1],[0,1],[1,2],[0,7],[4,7],[0,3],[7,5],[3,6]]	[[4,1],[5,2]]	        true
    9	[[0,1],[0,3],[0,7],[8,1],[3,6],[1,2],[4,7],[7,5]]	[[4,1],[8,7],[6,5]]     false
    */


    // 시작 노드에서 도착 노드까지 가는 동안,
    // 다른 pre 노드보다 next 노드를 방문하면 안됨


    public static void main(String[] args) {
        boolean ans1 = solution(9, new int[][]{{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}}, new int[][]{{8, 5}, {6, 7}, {4, 1}}); // true
        System.out.println(ans1);
        /*
        int ans2 = solution(new int[]{1,2,1,2},new int[]{1,10,1,2}); // 7
        System.out.println(ans2);
        int ans3 = solution(new int[]{1,4},new int[]{3,4}); // -1
        System.out.println(ans3);
        */
    }

    public static int getAnIndegree (List<List<Integer>> r, Queue<Integer> q, int in){
        int res = 999999;

        while (!q.isEmpty()){
            int x = q.poll();

            if (r.get(x).contains(0)){
                res = in + 1;
                break;
            }

            for (int i=0; i<r.get(x).size(); i++){
                q.add(r.get(x).get(i));
                int y = getAnIndegree(r,q,in+1);
                if (res > y) res = y;
            }
        }
        return res;
    }

    public static boolean solution(int n, int[][] path, int[][] order) {
        // 초기화
        int [] visited = new int[n];
        int [] indegree = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        List<List<Integer>> rooms = new ArrayList<>();
        for (int i=0; i<n; i++) rooms.add(new ArrayList<>());
        for (int i=0; i<path.length; i++){
            int a = path[i][0];
            int b = path[i][1];
            rooms.get(a).add(b);
            rooms.get(b).add(a);
            }

        // 리스트 출력
        System.out.println("============Rooms============");
        for (int i=0; i<n; i++) System.out.println(i + " : " +rooms.get(i).toString());

        // 진입 차수 구하기 (원하는 노드 -> 0번 노드까지 최단기간)
        System.out.println("============Indegree============");
        System.out.println(0 + " : " +indegree[0]);
        for (int i=1; i<n; i++){
            if (rooms.get(i).contains(0)) indegree[i] = 1;
            else{
                queue.add(i);
                indegree[i] = getAnIndegree(rooms, queue, 0);
            }
            System.out.println(i + " : " +indegree[i]);
        }


        // 경로 탐색
        queue.clear();
        ArrayList<Integer> pre = new ArrayList<>(order.length);
        ArrayList<Integer> next = new ArrayList<>(order.length);
        for (int i=0; i<order.length; i++){
            pre.add(order[i][0]);
            next.add(order[i][1]);
            // queue.add(order[i][0]);
        }

        for (int i=0; i< order.length; i++){
            queue.add(pre.get(i));

            while (!queue.isEmpty()){
                int x = queue.poll();
                visited[x] = 1;

                if (next.contains(x)){
                    int idx = next.indexOf(x);
                    if (idx == i) break;
                    if (visited[pre.get(idx)] == 0) return false;
                }

                for (int j=0; j<rooms.get(j).size(); j++){
                    queue.add(rooms.get(i).get(j));
                }
            }
        }

        return true;
    }
}
