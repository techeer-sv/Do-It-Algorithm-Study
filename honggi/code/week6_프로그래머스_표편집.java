import java.util.*;
class Solution {
    /*
        n - 행의 개수, k - 처음 선택된 행의 위치, cmd - 수행한 명령어들 (U X, D X, C, Z)
        
        유의사항
        1. U & D의 경우, 행의 범위를 넘어가게 되면 circle과 같이 반대쪽 끝으로 돌아옴
        2. C의 경우, 행 삭제 시 바로 아래 행이 선택됨
            1) 삭제된 행이 맨 밑인 경우, 바로 윗 행을 선택
        3. 가장 최근에 삭제된 행을 복구하며, 이 때 현재 선택된 행은 바뀌지 않음
        
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
        return movedI >= 0 ? movedI : tableL-movedI;
    }
    
    public int processDown(int index, int move){
        return (index + move) % tableL;
    }
    
    public int processCut(int index){
        int removedI = table.remove(index);
        reset.add(removedI);
        tableL--;
        if (index == tableL)
            return index-1;
        else
            return index;
    }
    
    public int processReset(int index){
        int removedI = reset.pop();
        int insertIndex = binarysearch(0, tableL-1, removedI);
        table.add(insertIndex, removedI);

        if (insertIndex <= index)
            index++;

        tableL++;
        return index;
    }
    
    public int binarysearch(int l, int r, int value){
        while (l <= r){
            int mid = (l+r)/2;
            if (table.get(mid) < value)
                l = mid+1;
            else if (table.get(mid) > value)
                r = mid-1;
            else
                return mid;
        }
        return l;
    }
    

}