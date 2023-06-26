import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Arrays;

class Solution {
    int qL;
    Long goal;
    public int solution(int[] queue1, int[] queue2) {
        /*
            1. 두 개의 int array를 Queue 자료구조에 담고, 각 Queue의 합을 구하기
            2. 두 Queue의 각 합이 동일할 때까지 count를 늘려가며 반복
                1) count가 queue의 크기의 4배가 되는 경우 -> 두 큐의 원소의 합이 같을 수 없으므로 -1
                2) 두 큐의 원소의 합 중, 큰 쪽의 큐 원소를 삭제해 다른 큐에 삽입
            
        */
        Long q1s = 0L, q2s = 0L;
        qL = queue1.length;
        Queue<Long> q1 = new LinkedList<Long>();
        Queue<Long> q2 = new LinkedList<Long>();
        
        for (int i = 0; i<qL; i++){
            q1s += (long) queue1[i];
            q1.add((long) queue1[i]);
            q2s += (long) queue2[i];
            q2.add((long) queue2[i]);
        }

        if ((q1s + q2s) % 2 == 1)
            return -1;
        else
            goal = (q1s + q2s)/2;
        
        return calculateCount(q1, q2, q1s, q2s);
    }
    
    public int calculateCount(Queue<Long> q1, Queue<Long> q2, Long q1s, Long q2s){
        int count = 0;
        
        while (true){
            if (count == qL*4){
                return -1;
            } else {
                if (q1s > q2s){
                    Long e = q1.poll();
                    q2.add(e);
                    q1s -= e;
                    q2s += e;
                } else if (q1s < q2s){
                    Long e = q2.poll();
                    q1.add(e);
                    q2s -= e;
                    q1s += e;
                } else {
                    return count;
                }
                count++;
            }
        }
    }
}