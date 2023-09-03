package Yunho.code.week17;
// https://school.programmers.co.kr/learn/courses/30/lessons/42861
// 섬 얀결하기

import java.util.*;
/**
 * (@@ 일단 방향성을 완전 잘못 잡아서 틀렸습니다..@@)
 *  n 만큼의 2차원 배열을 선언 후 costs의 길이의 최대값으로 각 노드를 초기화 
 *  가로줄의 원소중 가장 작은 값을 누적해서 더함 
 *  
 * 
 */
class Solution {
    public int solution(int n, int[][] costs) {
        
        int answer = 0;
        int costsLengths = (n * (n - 1))/2;
        int[][] arrCost = new int[n][n];

        // Arrays.fill(arrCost, 0);
        for (int[] i : arrCost) Arrays.fill(i, costsLengths);

        for(int[] temp : costs){
            arrCost[temp[0]][temp[1]] = temp[2];
        }

        for (int col = 1; col < arrCost[0].length; col++) {
            int minInColumn = arrCost[0][col];
            for (int row = 1; row < arrCost.length; row++) {
                if (arrCost[row][col] < minInColumn) {
                    minInColumn = arrCost[row][col];
                }
            }
            answer += minInColumn;
        }

        return answer;
    }
}