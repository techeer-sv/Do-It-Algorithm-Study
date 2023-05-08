import java.util.*;
class Solution {
    /*
        가장 큰 수를 만들 수 있도록 numbers를 오름차순으로 정렬하여 큰 수 만들기
        정렬 방법
        -> 두 원소의 첫 번째 인덱스의 값부터, 둘 중 길이가 큰 원소의 마지막 인덱스까지 비교하여 대소 비교
            1) 두 원소 중 길이가 적은 원소의 경우, 인덱스를 원소의 길이로 나머지 연산을 적용하여 비교
              ex) [12,123] 의 경우, 1과 3이 비교됨
            2) 모든 비교까지가 동일한 경우, 두 원소로 만들어지는 두 개의 수를 대소비교
              ex) [121, 1212] -> 1212121 와 1211212을 비교
    */
    public String solution(int[] numbers) {
        String answer = "";
        int numL = numbers.length;
        String[] numberI = new String[numL];

        Arrays.setAll(numberI, i -> String.valueOf(numbers[i]));

        Arrays.sort(numberI, (a, b) -> {
            if (a.length() == b.length())
                return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
            else
                return a.length() < b.length() ? compareTwoArray(a,b) : -compareTwoArray(b,a);
        });

        for (int i = numberI.length-1; i>=0; i--)
            answer += numberI[i];
        
        // 모든 원소가 0인 경우
        if (answer.charAt(0) == '0')
            return "0";
        else
            return answer;
    }

    public int compareTwoArray(String s1, String s2){
        int index = 0;
        int s1L = s1.length();
        int s2L = s2.length();
        
        while (index < s2L){
            if (Character.compare(s1.charAt(index % s1L), s2.charAt(index)) != 0)
                return Character.compare(s1.charAt(index % s1L), s2.charAt(index));
            index++;
        }
        return Integer.compare(Integer.parseInt(s1 + s2), Integer.parseInt(s2 + s1));
    }
}