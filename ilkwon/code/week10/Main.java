import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Queue<Integer>q1 = new LinkedList<>();
        Queue<Integer>q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        int len1 = queue1.length;
        int len2 = queue2.length;

        for(int i=0; i<len; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }

        while(sum1 != sum2){
            answer++;

            if(sum1 > sum2){
                int q1value = q1.poll();
                sum1 -= q1value;
                sum2 += q1value;
                q2.add(q1value);
            }else{
                int q2value = q2.poll();
                sum1 += q2value;
                sum2 -= q2value;
                q1.add(q2value);
            }

            if(answer > (len1 + len2) * 2) {
                return -1;
            }
        }
        return answer;
    }
}