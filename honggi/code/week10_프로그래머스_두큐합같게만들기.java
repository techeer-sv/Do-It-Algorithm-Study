import java.util.Queue;
import java.util.LinkedList;

class Solution {
    int min = Integer.MAX_VALUE;
    int qL;
    public int solution(int[] queue1, int[] queue2) {
        /*
            url - https://school.programmers.co.kr/learn/courses/30/lessons/118667
            
            1. 두 큐의 합 구하기.
            2. 둘 중 하나의 큐에서 pop, 다른 queue에 insert (dfs)
            3. 두 과정을 반복, 합이 같으면 종료
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
        int answer = dfs(q1, q2, q1s, q2s, 0);
        return (answer != Integer.MAX_VALUE) ? answer : -1;
    }
    
    public int dfs(Queue<Long> q1, Queue<Long> q2, Long q1s, Long q2s, int count){
        if (q1s == q2s){
            System.out.println("q1S:" + q1s+ ", q2S:" + q2s);
            min = Math.min(min, count);
            return min;
        } else if (count == qL*4){
            return min;
        } else {
            return Math.min(pop(q1, q2, q1s, q2s, count), 
                                pop(q2, q1, q2s, q1s, count));
        }
    }
    public int pop(Queue<Long> q1, Queue<Long> q2, Long q1s, Long q2s, int count){
        if (q1.isEmpty())
            return Integer.MAX_VALUE;
        else{
            Long q1e = q1.remove();
            q2.add(q1e);
            return dfs(q1, q2, q1s - q1e, q2s + q1e, count+1);
        }
    }    
    
    public String print(Queue<Long> q){
        while (!q.isEmpty()){
            System.out.print(q.poll() +" ");
        }
        return "\n";
    }
    
}