// 두 큐 합 같게 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/118667

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DoitJava_Week10 {
    public static void main(String[]args){
        int ans1 = solution(new int[]{3,2,7,2},new int[]{4,6,5,1}); // 2
        System.out.println(ans1);
        int ans2 = solution(new int[]{1,2,1,2},new int[]{1,10,1,2}); // 7
        System.out.println(ans2);
        int ans3 = solution(new int[]{1,4},new int[]{3,4}); // -1
        System.out.println(ans3);
    }


    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int maxMove = queue1.length*4;  // 최대 이동 횟수를 정해줘야함, 안그러면 무한루프트 걸릴 수도...
        long q1_sum = Arrays.stream(queue1).sum();
        long q2_sum = Arrays.stream(queue2).sum();
        long mid = (q1_sum + q2_sum)/2;

        if ((q1_sum+q2_sum)%2!=0) return -1; // 중간값이 홀수이면, 불가능

        // int [] => Integer [] => Queue 로 전환
        Queue <Integer> q1 = new LinkedList<>(Arrays.asList(Arrays.stream(queue1).boxed().toArray(Integer[]::new)));
        Queue <Integer> q2 = new LinkedList<>(Arrays.asList(Arrays.stream(queue2).boxed().toArray(Integer[]::new)));
        int e = 0;

        while (q1_sum != mid){
            if (q1_sum <= 0 || q2_sum <= 0)
                return -1;
            if (answer >= maxMove) 
                return -1;
            // 두 큐 중에 값이 더 부족한 곳으로 이동
            if (q1_sum < mid){
                e = q2.poll();
                q1.add(e);
                q1_sum += e;
                q2_sum -= e;
            }
            else if (q2_sum < mid) {
                e = q1.poll();
                q2.add(e);
                q1_sum -= e;
                q2_sum += e;
            }
            answer ++;
        }
        return answer;
    }
}
