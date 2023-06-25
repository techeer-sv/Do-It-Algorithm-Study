import java.util.*;

class Main {

    public static void main(String[] args){
        int[] answer;
        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        ArrayList<Integer> list[] = new ArrayList[N];

        //입력
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<Integer>();
            while(flag){
                int n = scanner.nextInt();
                if(n == -1){
                    flag = false;
                    continue;
                }
                list[i].add(n);
            }
        }


    }
}