import java.util.*;

//FIFO문제 => queue 사용
class Solution {
    public int solution(int bridge_length, int weightLimit, int[] truck_weights) {
        Queue<Integer> bridgeQueue = new LinkedList<>(); //적절한 작명이 떠오르지 못했다...
        int time = 0;
        int bridgeWeight = 0;

        for(int i=0; i<truck_weights.length; i++){

            int truckWeight = truck_weights[i]; // 적절한 작명이 떠오르지 못했다...2 하여 값은 값을 지닌 변수 2개를 선언할까도 생각했었다..(truck, truckWeight)

            while(true){

                if(bridgeQueue.isEmpty()){
                    bridgeQueue.add(truckWeight);
                    bridgeWeight += truckWeight;
                    time++;
                    break;
                }else if(bridgeQueue.size() == bridge_length){
                    bridgeWeight -= bridgeQueue.poll();
                }else{
                    if(truckWeight + bridgeWeight <= weightLimit){
                        bridgeQueue.add(truckWeight);
                        bridgeWeight += truckWeight;
                        time ++;
                        break;
                    }else{
                        bridgeQueue.add(0);
                        time++;
                    }
                }
            }
        }

        return bridge_length + time;
    }
}
