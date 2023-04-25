import java.util.*;

class Solution {
    Integer[] scoreList;
    String key;
    int index;
    Map<String, ArrayList<Integer>> infoM;
    String[] infoS = new String[4];
    public int[] solution(String[] info, String[] query) {
        /*
            (5 * 10^4) * 10^5 -> 시간초과 (각 query 마다 info 배열 전체 탐색 x)
            
            info의 한 번 탐색 후, 가공된 정보에 query가 접근하는 방식을 통해 시간복잡도 개선하기.
            1. info를 score기준으로 정렬
            2. info의 조건에 맞는 key 생성 및 value를 score 저장 (map 활용)
                - key의 경우, 조건을 모두 이어붙인 문자열을 사용
                - score의 경우, LinkedList를 생성하여 score를 저장
            3. 매 query에 대해서, 알맞은 key를 탐색하여 해당하는 인원 체크
                - "-"에 해당하는 조건의 경우, 정규표현식을 통해 탐색            
        */

        int queryL = query.length, infoL = info.length;
        int[] answer = new int[queryL];
        int score;
        
        // 코테 점수 기준 오름차순 정렬 (람다식으로 변경) nlogn 
        Arrays.sort(info, (a, b) ->
             Integer.valueOf(a.split(" ")[4]).compareTo(Integer.valueOf(b.split(" ")[4]))
        );
        
        infoM = new HashMap<String, ArrayList<Integer>>(110);
        
        for (int i = 0; i< infoL; i++){
            dfs(infoS, 0, info[i].split(" "));
        }
        
        for (int i = 0; i< queryL; i++){
            String[] split = query[i].split(" and | ");
            key = "";
            Arrays.stream(Arrays.copyOf(split,split.length-1)).forEach(s -> key += (sliceOne(s)));
            score = Integer.parseInt(split[split.length-1]);
            answer[i] = countUpperScore(infoM.get(key), score);
        }
        
        return answer;
    }
        
    public void dfs(String[] keys, int length, String[] conditions){
        if (length == 4){
            String key = String.join("", keys);
            ArrayList<Integer> list = infoM.get(key);
            
            if (list != null)
                list.add(Integer.parseInt(conditions[4]));
            else
                infoM.put(key, new ArrayList<Integer>(Arrays.asList(Integer.valueOf(conditions[4]))));
        }
        else {
            keys[length] = sliceOne(conditions[length]);
            dfs(keys, length+1, conditions);
            keys[length] = "-";
            dfs(keys, length+1, conditions);
        }
    }

    // 언어의 앞글자 사용 (map의 key 탐색 시간 단축)
    public String sliceOne(String s){
        return s.substring(0,1);
    }
    
    
    // list중 score보다 큰 점수 count
    public int countUpperScore(ArrayList<Integer> list, int s) {
        if (list != null){
            return list.size() - bs(list, s);
        } else
            return 0;
    }
    
    public int bs(ArrayList<Integer> list, int s){
        int left = 0; int right = list.size()-1;
        
        while (left <= right){
            int mid = (right + left)/2;
            
            if (list.get(mid) < s)
                left = mid + 1; 
            else{
                right = mid - 1;
            }
        }
        
        return left;
    }
    
}