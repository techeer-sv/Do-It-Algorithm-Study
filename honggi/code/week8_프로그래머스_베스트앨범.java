import java.util.*;

class Solution {
    HashMap<String, Integer> sortedGenres = new HashMap<String, Integer>(100);
    HashMap<String, LinkedList<int[]>> sortedPlayCount = new HashMap<String, LinkedList<int[]>>(100);
    public int[] solution(String[] genres, int[] plays) {
        /*
            sortedGenres의 value - 장르 별 재생 횟수 정보
            sortedPlayCount의 value - 장르 별 노래 list
                int[] -> (재생 횟수, 고유 번호)

            풀이
            1. 장르 by 재생 횟수 count
            2. 장르 내 노래 by 재생 횟수 정렬 (재생 횟수가 같은 경우, 고유 번호가 낮은 순 정렬)

            3. 장르 by 재생 횟수 내림차순 정렬
            4. 정렬된 장르 by 노래 수록
                
        */
        LinkedList<Integer> result = new LinkedList<Integer>();
        
        for (int i = 0; i<genres.length; i++){
            String genre = genres[i];
            
            // 장르 by 재생 횟수 count
            sortedGenres.put(genre, sortedGenres.getOrDefault(genre, 0) + plays[i]);
            
            // 장르 by 노래 분류
            LinkedList<int[]> temp = sortedPlayCount.getOrDefault(genre, new LinkedList<int[]>());
            temp.add(new int[]{plays[i], i});
            
            // 장르 내 노래가 여러 개인 경우 -> 정렬
            if (temp.size() >= 2){
                // 재생 횟수 오름차순, 고유 번호 내림차순
                Collections.sort(temp, (int[] a, int[] b) -> 
                    a[0] != b[0] ? -Integer.compare(a[0],b[0]) :
                                Integer.compare(a[1],b[1]));

                // 최대 2개 수록곡 가능 -> 가장 적은 재생 횟수 노래 삭제
                if (temp.size() > 2)
                    temp.removeLast();
            }
            
            // 정렬된 장르 by 노래 list update
            sortedPlayCount.put(genre, temp);
        }
        
        // 장르 별 재생 횟수 정보 list
        List<Map.Entry<String,Integer>> list = 
            new LinkedList<Map.Entry<String,Integer>>(sortedGenres.entrySet());
        
        // 장르 by 재생 횟수 내림차순 정렬
        Collections.sort(list, (Map.Entry<String,Integer> a, Map.Entry<String,Integer> b) ->
            -a.getValue().compareTo(b.getValue()));
        
        // 장르 별 노래 list 수록
        for (Map.Entry<String,Integer> genre : list){
            String g = genre.getKey();
            LinkedList<int[]> play = sortedPlayCount.get(g);
            
            for (int[] p : play)
                result.add(p[1]);
        }
        
        int[] answer = new int[result.size()];
        Integer[] resultA = result.toArray(new Integer[result.size()]);
        for (int i = 0; i < result.size(); i++){
            answer[i] = resultA[i];
        }
        
        return answer;
    }
    
}