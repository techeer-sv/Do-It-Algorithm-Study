package Yunho.code.week12;

import java.util.*;
//https://leetcode.com/problems/predict-the-winner/
//prdict the winner 


class Solution {
    /**
     * 
     * 음 어려움.. 해설보고 이해한 바를 작성해봄
     * 굳이 플레이어 1,2의 값을 다 계산 안해도 상관없음
     * 플레이어 1이 가질 수 있는 값에서 플레이어 2가 가질 수 있는 값을 빼서 그 수가 음 양을 따지면 됨
     * 배열을 두개 선언해서 각 플레이어가 가져가는 경우를 고려
     * 결국 dp 배열의 마지막 원소에 값이 들어감
     */

  public boolean PredictTheWinner(int[] nums) {
    int length = nums.length;
    int[] dp = new int[length];
    for (int i = length - 1; i >= 0; i--) {
      for (int j = i; j < length; j++) {
        if (i == j) {
          dp[i] = nums[i];
        } else {
          dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
        }
      }
    }
    return dp[length - 1] >= 0;
  }

}
