// 경우의 수,,,
// A, AA, AAA, AAAA, AAAAA, AAAAE, AAAAI, AAAAO, AAAAU 1씩증가
// AAAE, AAAEA, AAAEE, AAAEI, AAAEO, AAAEU 6씩증가 -> AAAI,,,,
// 위 경우를 5번씩하고, 6*5 -> 30번 + 1 (해당 자릿수가 없는경우)
// 5번째 자리 +1, 4번쨰-> +6, 3번째-> +(6*5+1), 2번째-> (31*5+1), 1번째 -> (156*5+1)
// 패턴찾기 노가다 


class Solution {
    public int solution(String word) {
        int answer = word.length();
        int[] numCountArr = {781, 156, 31, 6, 1};

        String str = "AEIOU";
        int idx = word.length();

        for(int i=0; i<word.length(); i++){
            idx = str.indexOf(word.charAt(i));
            answer += numCountArr[i]*idx;
        }

        return answer;
    }
}

// 재귀로 풀기....