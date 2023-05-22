import java.util.*;
class Solution {
    /*
        n - 행의 개수, k - 처음 선택된 행의 위치, cmd - 수행한 명령어들 (U X, D X, C, Z)
                
        모든 명령어를 수행한 후, 표의 상태와 처음 주어진 표의 상태를 비교하여
        삭제된 행은 X, 삭제되지 않은 행은 O로 표시한 문자열 반환
    */
    ArrayList<Integer> table;
    Stack<Integer> reset;
    int tableL;
    public String solution(int n, int k, String[] cmd) {
        char[] isRemoved = new char[n];
        Arrays.fill(isRemoved, 'X');

        table = new ArrayList<Integer>(n);
        reset = new Stack<Integer>();
        
        tableL = n;
        
        for (int i = 0; i<n; i++)
            table.add(i);

        for (String c : cmd){
            k = processCmd(c, k);
        }
        
        Integer[] result = table.toArray(new Integer[table.size()]);
        
        for (int i = 0; i<result.length; i++)
            isRemoved[result[i]] = 'O';
        
        return String.valueOf(isRemoved);
    }
    
    public int processCmd(String c, int index){
        String[] split = c.split(" ");
        switch(split[0]){
            case "U":
                return processUp(index, Integer.parseInt(split[1]));
            case "D":
                return processDown(index, Integer.parseInt(split[1]));
            case "C":
                return processCut(index);
            default:
                return processReset(index);
        }
    }
    
    public int processUp(int index, int move){
        move %= tableL;
        int movedI = index - move;
        System.out.println("nextIndex: "+ (movedI >= 0 ? movedI : tableL-movedI));
        return movedI >= 0 ? movedI : tableL-movedI;
    }
    
    public int processDown(int index, int move){
        System.out.println("nextIndex: "+ (index + move) % tableL);
        return (index + move) % tableL;
    }
    
    public int processCut(int index){
        int removedI = table.remove(index);
        reset.add(removedI);
        tableL--;
        printtable();
        if (index == tableL)
            return index-1;
        else
            return index;
    }
    
    public int processReset(int index){
        int removedI = reset.pop();
        if (removedI > tableL)
            table.add(removedI);
        else
            table.add(removedI, removedI);
        tableL++;
        printtable();
        return index;
    }
    
    public void printtable(){
        for (int i = 0; i<tableL; i++){
            System.out.println(i+": " + table.get(i));
        }
        System.out.println();
    }


}