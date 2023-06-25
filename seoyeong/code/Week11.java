// 게임 개발
// https://www.acmicpc.net/problem/1516

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoitJava_Week11 {
    /*
10 20 14 18 17

5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
    */

    public static void main(String[]args) throws IOException {
        // 변수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> buildings = new ArrayList<>(); // 빌딩 짓는 데 걸리는 시간 및 선행되어야 하는 건물
        List<List<Integer>> orders = new ArrayList<>(); // 선행되어야 하는 건물
        int [] ans = new int[n]; // 건물 당 최단시간

        // 입력
        for (int i = 0; i < n; i++) {
            String[] t1 = br.readLine().split(" ");
            Integer [] t2 = new Integer[t1.length];
            for (int j=0; j<t1.length; j++){
                t2[j] = Integer.parseInt(t1[j]);
            }
            buildings.add(Arrays.asList(t2));
            ans[i] = (buildings.get(i).get(0));
        }

        // orders (선행되어야 하는 건물 순서) 정리하기
        for (int i=0; i<n; i++){
            if (buildings.get(i).size() == 2){
                orders.add(new ArrayList<>());
                continue;
            }
            ArrayList<Integer> o = new ArrayList<>();
            for (int j=1; j<buildings.get(i).size()-1; j++)
                o.add(buildings.get(i).get(j)-1);
            orders.add(o);
        }

        for (int i=0; i<n; i++){
            if (orders.get(i).size()>1){
                List<Integer> o = orders.get(i);  // [2,0]
                for (int j=0; j<o.size(); j++){
                    int f = o.get(j);
                    for (int k=o.size()-1; k>j; k--){
                        if (orders.get(f).contains(o.get(k)))
                            orders.get(i).remove(k);
                    }
                }
            }
        }

        // 최단 시간 계산 (orders를 중심으로)
        int idx = 0;
        while (idx < n){
            for (int i=0; i<orders.get(idx).size(); i++){
                ans[idx] += ans[orders.get(idx).get(i)];
            }
            idx++;
        }

        
        // 정답 출력
        for (int a : ans){
            System.out.println(a);
        }
    }
}
