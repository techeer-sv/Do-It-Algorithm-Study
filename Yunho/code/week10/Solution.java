// https://school.programmers.co.kr/learn/courses/30/lessons/118667
// 두 큐의 원소를 같게하기

import java.util.*;


class Solution {
    /*
        목표: 각 queue가 (모든 원소의 합)/2가 되도록 하는 최소 횟수를 구해야함
        1. 모든 큐의 원소의 합을 구한다 
        2. 모든 원소의 합이 홀수이면 -1을 return
        3. queue1과 queue2를 비교해서 
            3-1. queue1 > queue2이면 queue1의 원소를 poll해서 queue2의 원소에 offer
            3-2. 위의 케이스와 반대로 
        4.
    */
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sumQ1 = 0;
        long sumQ2 = 0;
        long halfSum = 0;
        int len1 = queue1.length;
        int len2 = queue2.length;
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();

        for (int i : queue1) {
            q1.offer(i);
            sumQ1 += i;
        }
        for (int j : queue2) {
            q2.offer(j);
            sumQ2 += j;
        }

        if ((sumQ1 + sumQ2) % 2 == 1) {
            return -1;
        }
        

        halfSum =(long) (sumQ1 + sumQ2) / 2;

        while (sumQ1 != sumQ2) {
            if (sumQ1 > sumQ2) {
                int x = q1.poll();
                q2.offer(x);
                sumQ1 -= x;
                sumQ2 += x;
                answer++;
            } else {
                int x = q2.poll();
                q1.offer(x);
                sumQ1 += x;
                sumQ2 -= x;
                answer++;
            }
        if(answer > (len1 + len2) * 2) {
        return -1;
        }
        }

        return answer;
    }

}