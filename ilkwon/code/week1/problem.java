// 프로그래머스_완주하지 못한 선수
//https://school.programmers.co.kr/learn/courses/30/lessons/42576


//------------------------------배열 풀이------------------------------
import java.util.Arrays;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion); //배열 정렬

        for(int i=0; i<completion.length; i++){
            if(participant[i].equals(completion[i])){
                continue;
            }else{
                return participant[i];
            }
        }

        return participant[participant.length-1];
    }
}

//------------------------------해쉬 풀이------------------------------
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String,Integer> hash= new HashMap<>();

        //hash에 각 문자열 배열 값 넣기
        for (String part : participant) {
            hash.put(part, hash.getOrDefault(part, 0)+1);
        }

        for (String comp : completion) {
            hash.put(comp, hash.get(comp) - 1);
        }

        // 검색
        for (String key : hash.keySet()) {
            if (hash.get(key) != 0) {
                answer = key;
            }
        }

        return answer;
    }
}