// 검색 알고리즘
// https://school.programmers.co.kr/learn/courses/30/lessons/72412?language=java
// 정확성 100, 효율성 0 (왜 이진탐색을 썼는데도 안 나오지...?)

import java.util.*;

public class Week1 {
    public static void main(String[]args){
        int [] ans = solution(new String[]{ "java backend junior pizza 150","python frontend senior chicken 210",
                "python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80",
                "python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100",
                        "- and - and - and - 150"});
            for (int i=0; i<6; i++){
                System.out.println(ans[i]);
            }
        }


    public static int[] solution(String[] info, String[] query) {
        // answer initialize
        int i = 0;
        int res = 0;
        int [] answer = new int[query.length];

        // Set people's info
        // {('Java','Backend','Junior','Pizza','150'), ('Python', ...)}
        ArrayList<String[]> peopleInfo = new ArrayList<String[]>();
        for (String s : info){
            String [] t = s.split(" ");
            peopleInfo.add(t);
        }

        // Sort by Score asc
        Collections.sort(peopleInfo, (a, b) -> Integer.parseInt(a[4]) - Integer.parseInt(b[4]));

        // Search the number of people who satisfies requirements
        for (String u : query){
            res = 0;
            // standard = {"Java", "Backend", "Junior", "Pizza", "100"}
            String [] st = u.split(" ");
            ArrayList <String> standard = new ArrayList<>();
            for (String r : st){
                if (r.equals("and")){
                    continue;
                }
                standard.add(r);
            }

            // binary searching
            int idx = binarySearch(peopleInfo, info.length, Integer.parseInt(standard.get(4)));
            System.out.println(idx);

            for (int j=idx; j<info.length; j++){
                if ((peopleInfo.get(j)[0].equals(standard.get(0)) || standard.get(0).equals("-"))
                        && (peopleInfo.get(j)[1].equals(standard.get(1)) || standard.get(1).equals("-"))
                        && (peopleInfo.get(j)[2].equals(standard.get(2)) || standard.get(2).equals("-"))
                        && (peopleInfo.get(j)[3].equals(standard.get(3)) || standard.get(3).equals("-"))
                        //&& Integer.parseInt(peopleInfo.get(j)[4]) >= Integer.parseInt(standard.get(4))
                ){
                    res++;
                }
            }
            answer[i++] = res;
        }
        return answer;
    }

    // binary searching
    public static int binarySearch(ArrayList<String[]> s, int n, int key){
        int pl = 0;
        int pr = n;

        while(pl < pr){
            int pc = (pl+pr) / 2;
            int l = Integer.parseInt(s.get(pc)[4]);
            //System.out.println(key+" "+l);
            if (key <= l){
                pr = pc;
            }
            else{
                pl = pc+1;
            }
        }

        return pl;
    }
}



/*
import java.util.*;
class Solution {
    public int[] solution(String[] info, String[] query) {
        // answer initialize
        int i = 0;
        int res = 0;
        int [] answer = new int[query.length];

        // Set people's info
        // {('Java','Backend','Junior','Pizza','150'), ('Python', ...)}
        ArrayList<String[]> peopleInfo = new ArrayList<String[]>();
        for (String s : info){
            String [] t = s.split(" ");
            peopleInfo.add(t);
        }

        // Sort by Score asc
        Collections.sort(peopleInfo, (a, b) -> Integer.parseInt(a[4]) - Integer.parseInt(b[4]));

        // binary searching
        // Search the number of people who satisfies requirements
        for (String u : query){
            res = 0;
            // standard = {"Java", "Backend", "Junior", "Pizza", "100"}
            String [] st = u.split(" ");
            ArrayList <String> standard = new ArrayList<>();
            for (String r : st){
                if (r.equals("and")){
                    continue;
                }
                standard.add(r);
            }


            for (int j=0; j<peopleInfo.size(); j++){
                if ((peopleInfo.get(j)[0].equals(standard.get(0)) || standard.get(0).equals("-"))
                        && (peopleInfo.get(j)[1].equals(standard.get(1)) || standard.get(1).equals("-"))
                        && (peopleInfo.get(j)[2].equals(standard.get(2)) || standard.get(2).equals("-"))
                        && (peopleInfo.get(j)[3].equals(standard.get(3)) || standard.get(3).equals("-"))
                        && Integer.parseInt(peopleInfo.get(j)[4]) >= Integer.parseInt(standard.get(4))){
                    res++;
                }
            }
            answer[i++] = res;
        }

        return answer;
    }
}
 */