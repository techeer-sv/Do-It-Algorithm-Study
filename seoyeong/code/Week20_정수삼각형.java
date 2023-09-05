public class Week20_정수삼각형 {
    public static void main (String[]args){
        int p1 = solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
        System.out.println(p1);  // 30

        /*

        7
       3 8
      8 1 0
     2 7 4 4
    4 5 2 6 5

       */
    }


    public static int solution(int[][] triangle) {
        int answer = 0;
        int len = triangle.length;
        int l, r, c;

        for (int i=len-2; i>0; i--){
            for (int j=0; j<triangle[i].length; j++){
                c = triangle[i][j];
                // 아래 왼
                l = c + triangle[i+1][j];
                // 아래 오
                r = c + triangle[i+1][j+1];
                // 값 업데이트
                triangle[i][j] = Math.max(l,r);
            }
        }

        answer = Math.max(triangle[1][0],triangle[1][1]) + triangle[0][0];
        return answer;
    }
}
