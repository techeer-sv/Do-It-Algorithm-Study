import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        int size = n;
        for(int i=0; i< cmd.length; i++){
            String[] split = cmd[i].split(" ");
            if(split[0].equals("U")){
                k -= Integer.parseInt(split[1]);
                if(k < 0){
                    k = 0;
                }
            }
            else{ //'d'
                k += Integer.parseInt(split[1]);
                if(k >= size){
                    k = size-1;
                }
            }
            if(cmd[i].equals("C")){
                stack.push(k);
                size--;
                if(k == size) k -= 1;
            } 

        }
                // 0과1로 바꾸기
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < size; i++)
                    sb.append("O");
                while(!stack.isEmpty()){
                    sb.insert(stack.pop().intValue(),'X');
                }
                return answer = sb.toString();
        }
        

    }
