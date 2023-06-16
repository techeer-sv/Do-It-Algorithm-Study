//https://school.programmers.co.kr/learn/courses/30/lessons/42578
//의상
/*
 * hashmap에 같은 카테고리별로 의상을 저장한 다음 
 * 나올 수 있는 조합의 개수를 구하고 의상을 전부 입지 않는 경우를 제외해주었습니다.
 */
import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        int clothesLen = clothes.length; 
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0; i<clothesLen; i++){
            String kind = clothes[i][1];
            int num = 1;
            if (map.containsKey(kind)){
               num = map.get(kind)+1;
            } 
            map.put(kind,num);
        }

        for (int value : map.values()) {
            answer *= (value + 1);
        }
        answer = answer - 1; 

        return answer;
    }
}