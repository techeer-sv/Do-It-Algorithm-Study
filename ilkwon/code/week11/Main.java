import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        ArrayList<Integer>[] list = new ArrayList[num];
        int[] arr = new int[num];
        int index = 0;


        // input 값 저장
        for(int i = 0; i<num; i++) {
            list[i] = new ArrayList<Integer>();
            boolean flag = true;
            while(flag) {
                int n = scanner.nextInt();
                if(n == -1) {
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