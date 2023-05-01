// https://school.programmers.co.kr/learn/courses/30/lessons/84512?
// 모음사전 

class Solution {
    public int solution(String word) {
        int answer = 0;
        String alpha[] = new String[]{"A","E","I","O","U"};
        for(int i = 0 ; i < alpha.length; i++){
            String word1 = alpha[i];
            answer++;
            if(word1.equals(word)) return answer;
            for(int j = 0 ; j < alpha.length; j++){
                String word2 = word1 + alpha[j];
                answer++;
                if(word2.equals(word)) return answer;
                for(int k = 0 ; k < alpha.length; k++){
                    String word3 = word2 + alpha[k];
                    answer++;
                    if(word3.equals(word)) return answer;
                    for(int n = 0 ; n < alpha.length; n++){
                        String word4 = word3 + alpha[n];
                        answer++;
                        if(word4.equals(word)) return answer;
                        for(int m = 0 ; m < alpha.length; m++){
                            String word5 = word4 + alpha[m];
                            answer++;
                            if(word5.equals(word)){
                                return answer;
                            } ;
                        }
                    }
                }
            }
        }
        return answer;
    }
}