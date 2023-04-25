import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        /*
            모든 트럭은 다리 길이만큼 건너야 한다는 것 = 다리에 올라갈 수 있는 트럭의 최대 개수

            1. 다리에 트럭이 올라갈 수 없는 경우
                1) 다리에 올라갈 수 있는 트럭의 개수가 최대인 경우
                2) 다음 트럭이 올라탈 경우 다리의 무게가 견디지 못하는 경우
                = 다리에 올라간 트럭 중 맨 앞 트럭을 삭제 및 건너는 시간 감소
            2. 다리에 트럭이 올라갈 수 있는 경우 (위 조건과 반대)
                - 대기 트럭의 첫 번째 트럭을 queue에 삽입 및 
        */
        int answer = 1;
        ArrayDeque<Integer> bg = new ArrayDeque<Integer>(); // 다리를 지나는 중인 트럭
        // 첫 번째 트럭은 다리를 건너기 시작했다고 가정
        int bgW = truck_weights[0], index = 1, twL = truck_weights.length;
        int[] times = new int[twL];             // 다리를 건너는 경과 시간을 측정하는 배열
        Arrays.fill(times, bridge_length+1);    // (+1은 다리를 건너기 시작하면서 들어가는 시간)
        bg.add(0);
        times[0]--;
        
        while(index < twL){
            // 다리에 트럭이 올라갈 수 없는 경우
            if (bg.size() == bridge_length || 
                (bgW + truck_weights[index] > weight)){
                int firstTruckI = bg.remove();
                int time = times[firstTruckI];
                
                // 맨 앞 트럭이 건너는 소요시간 계산
                for (int i = firstTruckI; i< index; i++){
                    times[i] -= time;
                }
                bgW -= truck_weights[firstTruckI];
                
                // 트럭이 다리를 지나면서 다리에 트럭이 올라갈 수 있는지 여부 체크
                if (bgW + truck_weights[index] <= weight){
                    bg.add(index);
                    times[index]--;
                    bgW += truck_weights[index++];
                }
                answer+= time;
                
            } else {
                bg.add(index);
                bgW += truck_weights[index++];
                
                // 대기중인 트럭이 다리에 오르면서 걸리는 시간
                for (int i = bg.getFirst(); i<index; i++){
                    times[i]--;
                }
                
                // 맨 앞 트럭이 다리의 길이를 모두 지난 경우
                if (times[bg.getFirst()] == 0){
                    bgW -= truck_weights[bg.remove()];
                }
                answer++;
            }
        }
        
        // 다리를 건너는 트럭들에 대한 시간 계산
        while (!bg.isEmpty()){
            int firstTI = bg.remove();
            int time = times[firstTI];
            
            for (int i = firstTI; i<twL; i++)
                times[i] -= time;
            answer += time;
        }

        return answer;
    }
}