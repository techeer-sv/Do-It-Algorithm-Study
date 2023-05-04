// https://www.acmicpc.net/problem/1920
// 수찾기
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int[] NList = new int[N];
    for (int i = 0; i < N; i++) {
      NList[i] = sc.nextInt();
    }
    Arrays.sort(NList);
    int m = sc.nextInt();
    int[] MList = new int[m];
    for (int i = 0; i < m; i++) {
      MList[i] = sc.nextInt();
      System.out.println(binarySearch(NList, MList[i]));
    }

  }

  // 이분 탐색
  public static int binarySearch(int[] list, int num) {
    int min = 0;
    int mid = 0;
    int max = list.length - 1;
    while (min <= max) {
      mid = (min + max) / 2;
      if (list[mid] == num) {
        return 1;
      } else if (list[mid] < num) {
        min = mid + 1;
      } else if (list[mid] > num) {
        max = mid - 1;
      }
    }
    return 0;
  }

}
