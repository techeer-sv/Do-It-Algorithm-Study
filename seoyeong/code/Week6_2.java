// 표 편집
// https://school.programmers.co.kr/learn/courses/30/lessons/81303

import java.util.ArrayList;

public class Week6_2 {
    static int removed_cnt = 0;
    public static void main(String[]args){
        System.out.println(solution(8,2,new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));  // "OOOOXOOO"
        System.out.println(solution(8,2,new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));   // "OOXOXOOO"
    }
    public static int movePointer(ArrayList<Integer> m, int p, int x, String y){
        switch (y){
            case "U":
                p -= x;
                break;
            case "D":
                p += x;
                break;
            default:
                break;
        }

        if (p < 0) p = 0;
        if (p > m.size()-1) p = m.size()-1;

        System.out.println("\n"+x+" "+y+" "+p);
        return p;
    }

    public static String solution(int n, int k, String[] cmd) {
        char [] answer = new char[n];
        for (int i=0; i<n; i++) answer[i]='X';
        // m.get(i)[0] 은 인덱스값, m.get(i)[1] 은 삭제유무 (1은 존재/0은 삭제)
        // m = {{0,1}, {1,1}, {2,1}, {3,1}, {4,1}, {5,1},...}
        ArrayList <Integer> m = new ArrayList<>(n);
        ArrayList <Integer> r = new ArrayList<>(n);
        for (int i=0; i<n; i++) m.add(i);

        int pointer = k;

        for (int i=0; i<cmd.length; i++){
            String demand = cmd[i];
            int tmp = -1;
            switch (demand){
                case "C":  // 삭제 명령
                    tmp = m.remove(pointer);
                    r.add(tmp);
                    pointer = (pointer > m.size()-1) ? m.size()-1 : pointer;
                    break;
                case "Z":  // 복구 명령
                    tmp = r.remove(r.size()-1);
                    if (tmp>m.size()-1) m.add(tmp);
                    else m.add(tmp,tmp);
                    pointer = (tmp > pointer) ? pointer : pointer+1;
                    break;
                default:   // 이동 명령
                    String [] move = demand.split(" ");
                    pointer = movePointer(m,pointer,Integer.parseInt(move[1]),move[0]);
                    break;
            }
            System.out.print(m.toString() + "\t"+ r.toString());
            System.out.println("\nresult pointer: "+pointer);
        }

        for (int i=0; i < m.size(); i++) answer[m.get(i)] = 'O';
        String ans = "";
        for (int i=0; i < n; i++) ans += answer[i];
        return ans;
    }

    /*
    public static int movePointer(ArrayList<Integer[]>m, int p, int x, String y){
        switch (y){
            case "U":
                p -= x;
                break;
            case "D":
                p += x;
                break;
            default:
                break;
        }
        System.out.println("\n"+x+" "+y+" "+p);

        return p;
    }

    public static String solution(int n, int k, String[] cmd) {
        String answer = "";
        // m.get(i)[0] 은 인덱스값, m.get(i)[1] 은 삭제유무 (1은 존재/0은 삭제)
        // m = {{0,1}, {1,1}, {2,1}, {3,1}, {4,1}, {5,1},...}
        ArrayList <Integer[]> m = new ArrayList<>(n);
        for (int i=0; i<n; i++) m.add(new Integer[]{i,1});

        int pointer = k;

        for (int i=0; i<cmd.length; i++){
            String demand = cmd[i];
            switch (demand){
                case "C":  // 삭제 명령
                    m.get(pointer)[1] = 0;
                    movePointer(m,pointer,1,"D");
                    removed_cnt++;
                    break;
                case "Z":  // 복구 명령
                    m.get(pointer)[1] = 1;
                    removed_cnt--;
                    // 포인터 처리 => 그대로
                    break;
                default:   // 이동 명령
                    String [] move = demand.split(" ");
                    pointer = movePointer(m,pointer,Integer.parseInt(move[1]),move[0]);
                    break;
            }
            for (int j=0; j<m.size(); j++){
                System.out.print(m.get(j)[0]+""+m.get(j)[1]+"\t");
            }
            System.out.println("\nresult pointer: "+pointer);
        }
        return answer;
    }

     */
}
