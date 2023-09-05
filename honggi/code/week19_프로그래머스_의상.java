import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    int clothL, categoryL;
    Entry<String,Integer>[] cInfo;
    public int solution(String[][] clothes) {
        /*
            서로 다른 옷의 조합의 수 (옷의 종류가 n개, 총 의상의 수가 m개)
            -> 1~n까지 옷의 종류 k개를 뽑아 경우의 수 구하기
        */
        
        HashMap<String, Integer> categories = new HashMap<String, Integer>();
        clothL = clothes.length;
        
        for (int i = 0; i< clothL; i++){
            categories.put(clothes[i][1], categories.getOrDefault(clothes[i][1], 0) + 1);
        }
        
        categoryL = categories.keySet().size();
        cInfo = categories.entrySet().toArray(new Entry[categoryL]);
        int result = 1;
        for (int i= 0; i<categoryL; i++){
            result *= cInfo[i].getValue()+1;
        }
        return result -1;
        // return bitmasking();
    }
    
    public int bitmasking(){
        int result = 0;
        int maxBit = 1 << categoryL;
            
        for (int i = 1; i< maxBit; i++){
            result += combination(i);
        }
        return result;
    }
    
    public int combination(int bit){
        int result = 1;
        for (int i = 0; i< categoryL; i++){
            int b = 1 << i;
            if ((bit & b) > 0){
                result *= cInfo[i].getValue();
            }
        }
        return result;
    }
}