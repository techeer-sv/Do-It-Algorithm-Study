// 1학년
// https://www.acmicpc.net/problem/5557

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class DoitJava_Week16_2 {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String [] orgNums = br.readLine().split(" ");
        int [] nums = new int[orgNums.length];

        int x, plusX, minusX;
        int idx = 1;
        for (int i=0; i<orgNums.length; i++){
            nums[i] = Integer.parseInt(orgNums[i]);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(nums[0]);

        while (idx < orgNums.length-1){
            Queue<Integer> tempQueue = new LinkedList<>();
            while (queue.size() > 0) {
                x = queue.poll();
                plusX = x + nums[idx];
                minusX = x - nums[idx];
                if (plusX >= 0 && plusX <= 20) tempQueue.add(plusX);
                if (minusX >= 0 && minusX <= 20) tempQueue.add(minusX);
            }
            queue.addAll(tempQueue);
            idx++;
        }

        System.out.println(queue.toString());

        while (queue.size()>0){
            if (nums[nums.length-1] == queue.poll())
                answer++;
        }

        System.out.println(answer);
    }
}
