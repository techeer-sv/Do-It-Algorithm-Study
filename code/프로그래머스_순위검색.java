import java.util.*;

class Solution {
    Integer[] scoreList;
    String key;
    int index;
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
        
        // 코테 점수 기준 오름차순 정렬
        Arrays.sort(info, new Comparator<String>(){            
            @Override
            public int compare(String a, String b){
                int s1 = Integer.parseInt(a.split(" ")[4]);
                int s2 = Integer.parseInt(b.split(" ")[4]);
                if (s1 > s2)
                    return 1;
                else if (s1 < s2)
                    return -1;
                else
                    return 0;
            }
        });
                
        Map<String, LinkedList<Integer>> infoM = new HashMap<String, LinkedList<Integer>>();
        
        for (int i = 0; i< infoL; i++){
            String[] split = info[i].split(" ");
            key = sliceTwo(split[0])+sliceTwo(split[1])+
                sliceTwo(split[2])+sliceTwo(split[3]);
            score = Integer.parseInt(split[4]);

            LinkedList<Integer> list = infoM.get(key);
            if (list != null){
                list.add(score);
            } else{
                infoM.put(key, new LinkedList<Integer>(Arrays.asList(score)));
            }
        }
        
        Set<String> keyset = infoM.keySet();             
        
        for (int i = 0; i< queryL; i++){
            String[] split = query[i].split(" ");
            key = "";                
            key += queryParsing(split);
            score = Integer.parseInt(split[split.length-1]);
                        
            if (!key.contains(".*")){
                answer[i] = countUpperScore(infoM.get(key), score);
            } else {
                // query의 "-" 조건이 들어가있는 경우
                for (String k : keyset){
                    if (k.matches(key)){
                        answer[i] += countUpperScore(infoM.get(k), score);
                    }                    
                }    
                System.out.println();
            }
        }
        
        return answer;
    }
    
    // 언어의 앞 두글자 사용 (map의 key 탐색 시간 단축)
    public String sliceTwo(String s){
        return s.substring(0,2);
    }
    
    // 추후 정규표현식 문자열 검색
    public String queryParsing(String[] tokens){
        String temp = "";
        for (int i = 0; i<tokens.length-1; i++){
            if (!tokens[i].equals("and")){
                if (i > 1 && tokens[i-2].equals(tokens[i])){
                    continue;
                } else
                    temp += tokens[i].equals("-") ? ".*" : sliceTwo(tokens[i]);               
            }
        }
        return temp;
    }
    
    // list중 score보다 큰 점수 count
    public int countUpperScore(LinkedList<Integer> list, int s) {
        if (list != null){
            scoreList = list.toArray(new Integer[list.size()]);

            index = Arrays.binarySearch(scoreList, s);            
            index = index >= 0 ? index : -(index+1);
            
            // java의 이진 탐색 구현 중, 같은 원소가 여러개일 때 index를 무작위로 반환하는 경우 방지
            while (index >= 1 && index < scoreList.length)
                if (scoreList[index-1] >= s)
                    index--;
                else
                    break;
            
            return scoreList.length - index;
        } else
            return 0;
    }
    
}