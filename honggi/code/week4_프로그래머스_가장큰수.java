import java.util.*;
class Solution {
    
    /*
     * 1. 원소의 앞자리 수끼리 비교하여, 앞자리가 큰 원소 순으로 정렬 후 전체를 더해 가장 큰 수 구하기 -> 몇몇 테스트케이스 틀림..
        1) 같은 경우, 다음 자리 수를 비교
     * 2. 원소로 가능한 모든 수를 조합한 후, 정렬하여 가장 큰 수 구하기 -> 절반 이상 시간초과
    */
    // int minL;
    boolean[] visited;
    int numL;
    LinkedList<String> numList;
    String[] numberI;
    public String solution(int[] numbers) {
        String answer = "";
        numL = numbers.length;
        visited = new boolean[numL];
        numberI = new String[numL];
        numList = new LinkedList<String>();
        
        Arrays.setAll(numberI, i -> String.valueOf(numbers[i]));
        
        for (int i = 0; i< numL; i++)
            if (!numberI[i].equals("0"))
                maxNum(0,i,"");
        
        String[] allnum = numList.toArray(new String[numList.size()]);
        
        Arrays.sort(allnum, (a,b) -> {
            if (Integer.compare(a.length(), b.length()) != 0)
                return Integer.compare(a.length(), b.length());
            else{
                int index = 0;
                while (index < a.length()){
                    if (Character.compare(a.charAt(index), b.charAt(index)) != 0)
                        return Character.compare(a.charAt(index), b.charAt(index));
                    index++;
                }
                return 0;
            }
        });
        
        return allnum[allnum.length-1];
    }
    
    public void maxNum(int depth, int index, String nums){
        if (depth == numL){
            numList.add(nums);
        } else if (!visited[index]){
            visited[index] = true;
            
            for (int i = 0; i<numL; i++){
                maxNum(depth+1, i, nums + numberI[index]);
            }
            visited[index] = false;
        }
    }
        
//         Arrays.setAll(numberI, i -> numbers[i]);
        
//         Arrays.sort(numberI, (a, b) -> {
//             String[] s1 = String.valueOf(a).split("");
//             String[] s2 = String.valueOf(b).split("");
//             minL = Math.min(s1.length, s2.length);
//             int index = 0;
            
//             while (index < minL){
//                 if (compareStringToInt(s1[index], s2[index]) != 0){
//                     return compareStringToInt(s1[index], s2[index]);
//                 } else {
//                     index++;
//                 }
//             }
            
//             return s1.length < s2.length ?
//                 compareTwoArray(s1,s2,index) : compareTwoArray(s2,s1,index);
//         });

        
//         for (int i = numberI.length-1; i>=0; i--)
//             answer += numberI[i];
        
//         return answer;
//     }
    
//     public int compareTwoArray(String[] s1, String[] s2, int index){

//         while (index < s2.length){
//             if (compareStringToInt(s1[minL-1], s2[index]) != 0){
//                 System.out.println(s1[minL-1]+" "+ s2[index]+"="+compareStringToInt(s1[minL-1], s2[index]));
//                 return compareStringToInt(s1[minL-1], s2[index]);
//             } else{
//                 index++;
//             }
//         }
//         return 0;
//     }
    
//     public int compareStringToInt(String a, String b){
//         return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
//     }
}