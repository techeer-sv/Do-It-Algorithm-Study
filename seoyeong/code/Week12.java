// 동굴 탐험
// https://school.programmers.co.kr/learn/courses/30/lessons/67260

// 아이디어
// 1. 양방형으로 연결된 통로를 모두 저장한다.
// 2. 0번 노드를 시작으로, 탐색한다. 어차피 모든 노드들은 트리형태로 완전히 연결되어 있다. 다만 아래 주의사항 조건을 확인한다.
//    2-1. orders에 있는 첫번째 노드는 pre, 두번째 노드는 next이다.
//    2-2. 어떠한 경우에도 next가 pre보다 먼저 방문되면 안된다.
//    2-3. 만약 탐색 도중 next 속 노드가 있을 경우, 이를 무시한다. (큐의 완전 뒷 순서로 이동 후, 나중에 탐색되도록 한다.)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DoitJava_Week12 {
    public static void main(String[] args) {
        boolean ans1 = solution(9, new int[][]{{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}}, new int[][]{{8, 5}, {6, 7}, {4, 1}}); // true
        System.out.println(ans1);
        boolean ans2 = solution(9, new int[][]{{8, 1}, {0, 1}, {1, 2}, {0, 7}, {4, 7}, {0, 3}, {7, 5}, {3, 6}}, new int[][]{{4, 1}, {5, 2}}); // true
        System.out.println(ans2);
        boolean ans3 = solution(9, new int[][]{{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}}, new int[][]{{4, 1}, {8, 7}, {6, 5}}); // false
        System.out.println(ans3);
    }

    public static boolean solution(int n, int[][] path, int[][] order) {
        // 초기화
        int[] visited = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> waitingRoom = new LinkedList<>();
        List<List<Integer>> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) rooms.add(new ArrayList<>());
        for (int i = 0; i < path.length; i++) {
            int a = path[i][0];
            int b = path[i][1];
            rooms.get(a).add(b);
            rooms.get(b).add(a);
        }

        // 경로 탐색
        int cnt = n;
        queue.clear();
        ArrayList<Integer> pre = new ArrayList<>(order.length);
        ArrayList<Integer> next = new ArrayList<>(order.length);
        for (int i = 0; i < order.length; i++) {
            pre.add(order[i][0]);
            next.add(order[i][1]);
        }

        queue.add(0);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (next.contains(x)) {
                if (x == 0) return false;
                int idx = next.indexOf(x);
                if (visited[pre.get(idx)] == 0) {
                    waitingRoom.add(x);
                    continue;
                }
            }
            else if (pre.contains(x)){
                int idx = pre.indexOf(x);
                if (waitingRoom.size() > 0 && waitingRoom.contains(next.get(idx))){
                    int nextt = next.get(idx);
                    waitingRoom.remove(nextt);
                    queue.add(nextt);
                }
            }

            visited[x] = 1;
            cnt--;
            for (int i = 0; i < rooms.get(x).size(); i++) {
                int k = rooms.get(x).get(i);
                if (visited[k] == 0) queue.add(k);
            }
        }

        if (cnt != 0) return false;
        return true;
    }
}

