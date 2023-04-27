// https://school.programmers.co.kr/learn/courses/30/lessons/42583
// 다리를 지나가는 트럭 
// 문제를 아직 해결하지 못했습니다. 아래 주석친 로직에서 자바 컬렉션에 알맞게 수정을 진행중인데 오류잡기가 쉽지가 않네요.. 
// 방향성은 아래 로직을 봐주시면 감사하겠습니다.
import java.util.*;

class Soltution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        int answer = 0;
        Integer sumBridge = 0;
        Queue<Integer> bridge = new LinkedList<>(); // 다리를 queue로 선언하고 다리길이만큼 0으로 초기화 
        for(int i = 1; i <= bridge_length; i++){  
            bridge.offer(0);
        }
        Queue<Integer> truckWeight = new LinkedList<>(); // truck_weights도 queue로 선언 
        for (int k:truck_weights){
            truckWeight.offer(k);
        }

        for(Integer l : bridge){
            answer += 1;
            bridge.poll();
            for(int j:truckWeight){
                Iterator<Integer> iterator = bridge.iterator();
                while (iterator.hasNext()) {
                sumBridge += iterator.next();
                }
                if(sumBridge + truckWeight.peek() <= weight){
                    Integer truck = truckWeight.poll();
                    bridge.add(truck);
                }
                else{
                    bridge.add(0);
                }
            }
        }
        return answer;
    }
}


/**
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>(); // 다리를 queue로 선언하고 다리길이만큼 0으로 초기화 
        for(int i = 1; i <= bridge_length; i++){  
            bridge.offer(0);
        }
        Queue<Integer> truckWeight = new LinkedList<>(); // truck_weights도 queue로 선언 
        for (int k:truck_weights){
            truckWeight.offer(k);
        }

        while(bridge){
            answer += 1;
            bridge.poll();
            for(int j:truckWeight){
                // 다리위의 모든 트럭의 무게 +  대기중인 첫번쨰 트럭 무게 <= 다리가 버틸 수 있는 하중  
                if(sum(bridge)+ truckWeight.peek() <= weight){ 
                    Integer truck = truckWeight.poll();
                    bridge.add(truck);
                }
                // 위의 조건에 부합하지 않으면 그냥 bridge에 0으로 공백을 채움 
                else{ 
                    bridge.add(0);
                }
            }
        }
        return answer;
    }
}
*/

/** 
 * queue.peek() 큐의 요소를 제거하지 않고 값을 가져옴
 * queue.poll() 큐의 요소를 제거하면서 값을 가져옴
 * queue.add() 큐에 값을 추가하는데 꽉차있을경우 IllegalStateException 발생
 * queue.offer() 큐에 값을 추가하는데 값이 꽉차있을 경우 false를 반환 
 */
