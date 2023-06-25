// 게임 개발
// https://www.acmicpc.net/problem/1516

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoitJava_Week11 {
    /*
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
    */
    public static void main(String[]args) throws IOException {
        // 변수
        List<List<Integer>> buildings = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>(); // 정답용 (최단시간)

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] t1 = br.readLine().split(" ");
            Integer [] t2 = new Integer[t1.length];
            for (int j=0; j<t1.length; j++){
                t2[j] = Integer.parseInt(t1[j]);
            }
            buildings.add(Arrays.asList(t2));
            ans.add(buildings.get(i).get(0));
        }

        // 계산
        // while (buildings.add())


        // buildings
        for (int i=0; i<buildings.size(); i++)
            System.out.println(buildings.get(i).toString());

        // ans
        for (int i=0; i<buildings.size(); i++)
            System.out.println(ans.get(i).toString());

    }
}
