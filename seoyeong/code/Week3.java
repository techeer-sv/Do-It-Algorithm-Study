// 재귀 알고리즘
// https://school.programmers.co.kr/learn/courses/30/lessons/84512

/*
781 = 1+5*(1+5*(1+5*(1+5)))

- -> 6
-- -> 31
--- -> 156
---- -> 156*5+1 = 781

AAAAE 1+1+1+1+(1*1+1) = 6
AAAE = 1+ 1+ 1+ (1*6) +1 = 10
I---- = 781*2 + 1 = 1563
EIO-- = (781*1)+1 + (2*156)+1 + 3*31 + 1 = 1189
 */

public class Week3 {
    public static void main(String[] args) {
        String s = "EIO";
        System.out.println(solution1(s)); // 재귀
        System.out.println(solution2(s)); // 반복문
    }

    public static int search(int [] idx, int p, int x){
        // 마지막 인덱스
        if (p == idx.length-1){
            return x * idx[p];
        }

        int pre = (idx[p]>0) ? (idx[p]-1) * x + 1 : 0;
        return pre + search(idx, ++p, (x-1)/5);
    }

    // 재귀
    public static int solution1(String word) {
        int answer = 0;
        char [] alpha = {'A', 'E', 'I', 'O', 'U'};  // 모음 알파벳
        char [] w = word.toCharArray(); // String -> Char 배열
        int [] idx = new int[]{0,0,0,0,0}; // 해당 알파벳 인덱스만 저장하는 배열
        int x = 1+5*(1+5*(1+5*(1+5)));

        for (int i=0; i < w.length; i++){
            for (int j=0; j < alpha.length; j++){
                if (w[i] == alpha[j]) {
                    idx[i] = j+1;
                    break;
                }
            }
        }
        return search(idx,0, x);  // 대상 단어의 알파벳 인덱스, 시작 인덱스, 계산
    }

    // 반복
    public static int solution2(String word) {
        int answer = 0;
        char [] alpha = {'A', 'E', 'I', 'O', 'U'};  // 모음 알파벳
        char [] w = word.toCharArray(); // String -> Char 배열
        int [] idx = new int[w.length]; // 해당 알파벳 인덱스만 저장하는 배열

        for (int i=0; i < w.length; i++){
            for (int j=0; j < alpha.length; j++){
                if (w[i] == alpha[j]) {
                    idx[i] = j;
                    break;
                }
            }
        }

        int x = 781; // 1+5*(1+5*(1+5*(1+5)))
        for (int i=0; i<w.length; i++){ //5번 반복
            if (idx[i] > 0){
                answer += x * idx[i];
            }
            answer += 1;
            x = (x-1)/5;
        }
        return answer;
    }
}
