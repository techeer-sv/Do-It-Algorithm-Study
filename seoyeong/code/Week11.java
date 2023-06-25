// 게임 개발
// https://www.acmicpc.net/problem/1516

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoitJava_Week11 {
    /*
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
    */

    // 10 20 14 18 17
    public static void main(String[]args) throws IOException {
        // 변수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> buildings = new ArrayList<>();
        int [] ans = new int[n]; // 정답용 (최단시간)
        Queue<Integer> queue = new LinkedList<>(); // 시간 계산용 큐

        // 입력
        for (int i = 0; i < n; i++) {
            String[] t1 = br.readLine().split(" ");
            Integer [] t2 = new Integer[t1.length];
            for (int j=0; j<t1.length; j++){
                t2[j] = Integer.parseInt(t1[j]);
            }
            buildings.add(Arrays.asList(t2));
            ans[i] = (buildings.get(i).get(0));
            queue.add(i);
        }

        // 계산
        // queue = {0,1,2,3,4}
        while(queue.size()>0){
            int x = queue.poll(); // idx pop
            int cnt = 0;
            for (int i=1; i < buildings.get(x).size(); i++){
                Integer a = buildings.get(x).get(i) - 1; // 선행되어야 하는 인덱스
                if (a == -2) {
                    cnt = 0;
                    break;
                }
                if (!queue.contains(a)){
                    ans[x] = ans[a] + ans[x];
                    cnt ++;
                    continue;
                }
                else{
                    queue.add(x);
                    break;
                }
            }
            for (int k=0; k<cnt; k++)
                buildings.get(x).remove(1);



            // ans
            for (int a : ans){
                System.out.print(a+" ");
            }
            System.out.println();
        }
    }
}
