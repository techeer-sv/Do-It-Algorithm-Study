// 해시
// 의상
// https://school.programmers.co.kr/learn/courses/30/lessons/42578?language=java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Week8 {
    public static void main(String[]args){
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}})); // 5
        //System.out.println(solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}})); // -1
    }

    public static int solution(String[][] clothes) {
        int answer = 1;
        int items= clothes.length;
        HashMap<String,Integer> item = new HashMap<String,Integer>();
        for (int i=0; i<items; i++){
            String key = clothes[i][1];
            if (!item.containsKey(key))
                item.put(key,1);
            else {
                item.put(key,item.get(key)+1);
            }
        }

        List<Integer> nums = new ArrayList<>(item.values());
        for (int i=0; i<nums.size(); i++){
            answer *= (nums.get(i)+1);
        }

        return answer-1;
    }
}