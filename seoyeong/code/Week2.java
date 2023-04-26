// 스택과 큐
// https://school.programmers.co.kr/learn/courses/30/lessons/42583

// 2	10	[7,4,5,6]	8
// 100	100	[10]	101
// 100	100	[10,10,10,10,10,10,10,10,10,10]	110


import java.util.LinkedList;
import java.util.Queue;

public class Week2 {
    public static void main(String[] args) {
        int ans1 = solution(2,10,new int[]{7,4,5,6});
        System.out.println(ans1);
        int ans2 = solution(100,100,new int[]{10});
        System.out.println(ans2);
        int ans3 = solution(100,100,new int[]{10,10,10,10,10,10,10,10,10,10});
        System.out.println(ans3);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int t = 0; // 총 걸리는 시간 == 정답
        int p = 0; // 지나간 트럭 개수
        int next = 0; // 지나가야 할 트럭을 가리키는 포인터 (다음 순서 트럭)
        int total_weights = 0; // 현재 다리 위에 있는 트럭의 무게 총합
        Queue<Integer> bridge = new LinkedList<>(); // 다리 위에 올라간 각 트럭 위치 추적 (트럭이 지나가야할 거리)
        Queue<Integer> bridge_weights = new LinkedList<>(); // 다리 위에 올라간 각 트럭의 무게

        // 모든 트럭이 도착할 때까지 반복, 즉 시간을 잰다.
        while (p < truck_weights.length){
            //System.out.println(t+"초");

            // 이동중인 트럭들
            // 트럭의 위치를 추적하기 위해서 bridge queue에 남은 다리의 길이를 재계산
            for (int i=0; i<bridge.size();i++){
                int w = bridge_weights.poll();
                bridge.add(bridge.poll() - w);
                bridge_weights.add(w);
            }

            // 다리 위를 지나간 트럭 제거
            // 트럭이 지나가야 할 거리가 0일 때, 즉 다리를 모두 지났을 때
            if (bridge.peek() != null && bridge.peek() == 0){
                // 다리에서 트럭 제거
                bridge.poll();
                // 다리에서 트럭 무게 제거
                int arrivedTruck = bridge_weights.poll();
                total_weights -= arrivedTruck;

                //System.out.println("도착한 트럭: "+arrivedTruck);

                // 도착한 트럭 갯수 증가
                p++;
            }

            // 다리 위를 지나갈 트럭 추가 (다리가 감당할 수 있는 무게여야 하고, 지나가야할 트럭이 남아있어야 한다)
            if ((next <= truck_weights.length-1) && ( total_weights + truck_weights[next] <= weight)){
                //System.out.println("출발한 트럭: "+ truck_weights[next]);

                // 다리에 트럭 무게 추가
                total_weights += truck_weights[next];
                bridge_weights.add(truck_weights[next]);
                // 다리에 트럭(이 지나가야 할 거리) 추가
                bridge.add(truck_weights[next]*bridge_length); // 거리는 (트럭무게 * 다리 길이) 로 계산함.

                // 다음 트럭 순서
                next++;
            }

            t++; // 시간 추가
        }
        return t;
    }
}
