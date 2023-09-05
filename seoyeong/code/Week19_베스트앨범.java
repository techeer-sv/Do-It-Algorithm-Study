import java.util.*;

public class Week18_베스트앨범 {

    public static void main(String[] args) {
        // int [] p1 = solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500});
        // int [] p1 = solution(new String[]{"classic", "Newage", "pop", "classic", "classic", "pop", "Newage"}, new int[]{500, 1700, 600, 150, 800, 2500, 1500});
        int [] p1 = solution(new String[]{"A", "B", "A", "B", "A", "C"}, new int[]{500, 600, 150, 800, 2500, 5000});
        for (int a : p1){
            // System.out.println(a);  // [4, 1, 3, 0]
            // System.out.println(a);  // 1, 6, 5, 2, 4, 0
            System.out.println(a);     // [5, 4, 0, 3, 1]
        }
    }

    public static int[] solution(String[] genres, int[] plays) {
        int[] ans = {};
        HashMap<String,Integer> totalPlayed = new HashMap<String,Integer>(); // 총 재생된 음악의 수 => {pop,3100}{classic,1450}
        HashMap<String,Integer> mostPlayed = new HashMap<String,Integer>();  // 가장 많이 재생된 음악의 수 => {pop_1,600}{pop_2,2500}


        for (int i=0; i<genres.length; i++){
            String g = genres[i];
            int p = plays[i];

            // 장르 별 재생된 음악 횟수 값 업데이트
            if (totalPlayed.containsKey(g)){
                int updatePlayed = totalPlayed.get(g) + p;
                totalPlayed.replace(g,updatePlayed);

                // 장르 별 가장 많이 재생된 음악 인덱스 저장
                int c1, c2;
                if (mostPlayed.containsKey(g+"_1")){
                    if (mostPlayed.containsKey(g+"_2")){
                        c1 = plays[mostPlayed.get(g+"_1")];
                        c2 = plays[mostPlayed.get(g+"_2")];
                        // c1, c2 중 재생횟수가 더 작은 값과 비교
                        if (c1 > c2 && p > c2){
                            // c2 값 p 인덱스값으로 업데이트
                            mostPlayed.replace(g+"_2",i);
                        }
                        else if (c2 > c1 && p > c1){
                            // c1 값 p 인덱스값으로 업데이트
                            mostPlayed.replace(g+"_1",i);
                        }
                    }
                    else{
                        mostPlayed.put(g+"_2",i);
                    }
                }
            }

            else{
                totalPlayed.put(g,p);
                mostPlayed.put(g+"_1",i); // 장르 별 가장 많이 재생된 음악 인덱스 저장
            }
        }

        // return value
        int n = totalPlayed.size();
        ArrayList<Integer> answer = new ArrayList<>();
        List<Map.Entry<String,Integer>> entryList = new LinkedList<>(totalPlayed.entrySet());
        entryList.sort(((o1,o2)-> totalPlayed.get(o2.getKey()) - totalPlayed.get(o1.getKey())));
        for ( Map.Entry<String,Integer> k : entryList){
            String g1 = k.getKey()+"_1";
            String g2 = k.getKey()+"_2";


            if (mostPlayed.containsKey(g1)){
                if (mostPlayed.containsKey(g2)){
                    int m1, m2;
                    m1 = plays[mostPlayed.get(g1)];
                    m2 = plays[mostPlayed.get(g2)];
                    if (m1 > m2 || (m1 == m2 && mostPlayed.get(g1) < mostPlayed.get(g2))){
                        answer.add(mostPlayed.get(g1));
                        answer.add(mostPlayed.get(g2));
                    }
                    else if (m2 > m1 || (m1 == m2 && mostPlayed.get(g2) < mostPlayed.get(g1))){
                        answer.add(mostPlayed.get(g2));
                        answer.add(mostPlayed.get(g1));
                    }
                }
                else {
                    answer.add(mostPlayed.get(g1));
                }
            }
        }

        ans = new int[answer.size()];
        for (int i=0; i<answer.size(); i++){
            ans[i] = answer.get(i);
        }
        return ans;
    }
}
