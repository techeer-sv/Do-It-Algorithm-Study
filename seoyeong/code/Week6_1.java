public class Week6_1 {
    // 문자열 압축
    // https://school.programmers.co.kr/learn/courses/30/lessons/60057

    public static void main(String[] args) {
        /*
        "aabbaccc"	7
        "ababcdcdababcdcd"	9
        "abcabcdede"	8
        "abcabcabcabcdededededede"	14
        "xababcdcdababcdcd"	17
        */
        System.out.println(solution("aabbaccc"));  // 7 -> 2a2ba3c
        System.out.println(solution("ababcdcdababcdcd"));  // 9 -> 2ababcdcd
        System.out.println(solution("abcabcdede"));  // 8 -> 2abcdede
        System.out.println(solution("abcabcabcabcdededededede"));  // 14
        System.out.println(solution("xababcdcdababcdcd"));  // 17
    }

    /*
     * 주어진 문자열 S : abcabcdede  (총길이 10)
     * 쪼개야할 길이 : 3 만큼씩
     * 10/3 = 3.XXX  => block = 4   (3개씩 쪼개면 네 덩이가 나온다)
     * abc abc ded e
     */
    public static int splitString (String s, int len){
        String res = "";  // 결과로 나올 문자열
        int block = (s.length()%len == 0) ? s.length()/len : s.length()/len+1;  // chunk해서 나오는 덩어리 개수
        String [] word = new String[block];

        // 문자열 주어진 길이만큼 쪼개기
        for (int i=0; i<block-1; i++){
            word[i] = s.substring(i*len,i*len+len);
        }
        word[block-1] = s.substring((block-1)*len);

        // 앞 덩어리 문자열부터 순차적으로 문자열 비교하기
        String n = word[0];
        int count = 1;
        for (int i=1; i<block; i++){
            if (n.equals(word[i])){
                count++;
                continue;
            }
            res += (count==1) ? n : count+n;
            count = 1;
            n = word[i];
        }
        // 마지막 인덱스 값 남은 부분 처리
        if (count>1){
            res += count+n;
        }
        else{
            res+=n;
        }

        //System.out.println("chunk할 길이: "+len+"\t"+res + "\t totalLength: "+res.length());
        return res.length();
    }

    public static int solution(String s) {
        int answer = 9999;
        int i = s.length();

        // 주어진 문자열의 길이를 일정한 길이만큼 쪼개가며, 최단 길이를 구해보자
        while (i > 0){ // 주어진 문자열의 길이가 8이라면, 8,7,6,5,4,3,2,1 만큼 쪼개본다
            int splitStringLength = splitString(s,i);
            if (splitStringLength < answer) answer = splitStringLength;
            i--;
        }
        return answer;
    }
}
