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

        for(ArrayList<Integer> ls : list) {
            int result = ls.get(0);
            int max = 0;
            for(int j = 1; j < ls.size(); j++) {
                int bulid = ls.get(j);
                int te = arr[bulid-1];
                if(te > max) {
                    max = te;
                }
            }
            result += max;
            arr[index++] = result;
        }

        for(int c : arr) {
            System.out.println(c);
        }

    }
}
