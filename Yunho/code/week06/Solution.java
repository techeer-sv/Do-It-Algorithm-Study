// https://school.programmers.co.kr/learn/courses/30/lessons/60057
// 문자열 압축 
// 일일히 문자열을 앞에서 부터 압축하는식으로 구현을 했습니다.

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int len = 1; len < s.length() / 2 + 1; len++) {
            int sameWordCound = 1;
            StringBuilder builder = new StringBuilder();

            String prev = s.substring(0, len);
            int i = len;

            // 문자열을 일정한 길이로 자름
            while (i + len <= s.length()) {
                String substring = s.substring(i, i + len);

                if (prev.equals(substring)) {
                    sameWordCound++;
                } else {
                    if (sameWordCound > 1) {
                        builder.append(sameWordCound);
                    }
                    builder.append(prev);
                    prev = substring;
                    sameWordCound = 1;
                }

                i += len;
            }

            // 나머지 문자열을 처리
            if (sameWordCound > 1) {
                builder.append(sameWordCound);
            }
            builder.append(prev);
            int remain = s.length() - i; 

            if (builder.length() + remain < answer) {
                answer = builder.length() + remain;
            }
        }
        
        return answer;
    }
}