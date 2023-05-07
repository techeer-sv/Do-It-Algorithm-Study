// 정렬 알고리즘
// https://school.programmers.co.kr/learn/courses/30/lessons/42746

import java.util.ArrayList;
import java.util.Collections;

public class Week4 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{6,10,2,0}).equals("62100")); // "62100"
        System.out.println(solution(new int[]{3, 30, 34, 5, 9}).equals("9534330")); // "9534330"
        System.out.println(solution(new int[]{4,43,40,2,100}).equals("443402100")); // "443402100"
        System.out.println(solution(new int[]{6,6,6,6}).equals("6666")); // "6666"
        System.out.println(solution(new int[]{99,998,999,0,0}).equals("9999999800")); // "9999999800"
        System.out.println(solution(new int[]{45, 454}).equals("45454")); // "45 454"  "454 45"
        System.out.println(solution(new int[]{1,10,100,1000}).equals("1101001000")); // "1101001000"
    }

    public static ArrayList<Integer> maxString2 (ArrayList<Integer> n, ArrayList<Integer> realN, int a){
        // n = [0,1,2]
        // realN = [6,6,67]
        ArrayList<Integer> x = new ArrayList<>();
        // (2~4) 자릿수 비교
        int max = 0;
        int m = 0;
        for (int i=0; i<n.size(); i++){
            m = realN.get(n.get(i));
            if (m==1000) m=0;
            else if (m>=100 && a%2==0) m = m%100/10;     // 100 <= m < 1000, 첫째 자릿수 m/100
            else if (m>=100 && a%2==1) m = m%100%10;
            else if (m>=10 && a%2==0) m = m%10;       // 10 <= m < 100,  첫째 자릿수 m/10
            else if (m>=10 && a%2==1) m = m/10;
            // 둘째 자릿수를 비교했을 때 가장 큰 값 인덱스만 모으기
            if (max <= m){
                if (max < m) x.clear(); // 임시 배열 삭제
                max = m;  // 최댓값 변수 수정
                x.add(n.get(i)); // 임시 배열에 인덱스 추가
            }
        }

        //System.out.println("X:"+x + " 횟수:"+a);
        if (x.size()==1) {
            return x;
        }

        if (a>8) {return x;
        }
        x = maxString2(x,realN,++a);
        return x;
    }

    // 앞에 있는 자릿수들이 가장 큰 값 인덱스 반환
    // 1. 첫째 자릿수가 최댓값인 값만 뽑기 => 1개만 나올 수도, 여러개가 나올수도 있음 -> 4 43 40
    // 2. 1에서 뽑은 값을 다시 정렬 (한 자릿수가 우선순위가 제일 높음 + 둘째 자릿수 비교 등)
    public static ArrayList<Integer> maxString (ArrayList<Integer> n){
        ArrayList<Integer> x = new ArrayList<>();
        int max = 0;
        int m = 0;

        // 첫째 자릿수 비교
        for (int i=0; i<n.size(); i++){
            m = n.get(i);

            if (m == 1000) m = 1;           // m = 1000, 첫째 자릿수 1
            else if (m>=100) m = m/100;     // 100 <= m < 1000, 첫째 자릿수 m/100
            else if (m>=10) m = m/10;       // 10 <= m < 100,  첫째 자릿수 m/10

            // 첫째 자릿수를 비교했을 때 가장 큰 값 인덱스만 모으기
            if (max <= m){
                if (max < m) x.clear(); // 임시 배열 삭제
                max = m;  // 최댓값 변수 수정
                x.add(i); // 임시 배열에 인덱스 추가
            }
        }

        if (x.size()==1) return x;

        ArrayList <Integer> y = new ArrayList<>();
        for (int i=0; i<x.size(); i++){
            y.add(x.get(i));
        }
        return maxString2(y,n,2);
    }


    public static String solution(int[] numbers) {
        String answer = "";

        // int 배열을 ArrayList 형식으로 옮기기
        ArrayList <Integer> nums = new ArrayList<>();
        for (int n : numbers){
            nums.add(n);
        }

        // 만약 값이 '0'인 게 있다면, 여기서 0 개수 모아놨다가 맨 마지막에 붙여주기
        int numberZero =0;
        Collections.sort(nums);   // 6,10,2,0 -> 0,2,6,10

        for (int i=0; i<nums.size(); i++){
            if (nums.get(i)!=0) break;
            numberZero++;
        }

        // 만약 배열이 전부 "0"으로 이루어져있으면 그냥 0 반환
        if (numberZero==numbers.length) return "0";

        for (int i=0; i<numberZero; i++){
            nums.remove(0);
        }

        // 0을 제외한 배열 정렬 (해당 자릿수가 높은 것부터 뽑아서 문자열로 반환)
        while (nums.size()>0){
            ArrayList<Integer> x = maxString(nums);
            for (int j=0; j<x.size(); j++) {
                answer += nums.get(x.get(j)).toString();
            }
            for (int j=x.size()-1; j>=0; j--) {
                int idx = x.get(j);
                nums.remove(idx);
            }
            //System.out.println("중간 점검:" +answer);
        }

        // 아까 모아둔 '0'들을 answer 마지막에 붙여주기
        for (int i=0; i<numberZero; i++){
            answer += "0";
        }
        return answer;
    }
}
