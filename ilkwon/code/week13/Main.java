/*public class Main {
    public static void main(String[] args) {
        int[] nums = {1,5,2};
        PredictTheWinner(nums);
    }

    public static boolean PredictTheWinner(int[] nums) {
        boolean answer = true;
        int end = nums.length-1; //nums 인덱스 마지막
        int start = 0; //nums 인덱스 처음
        int count =0; // 실행 횟수
        int p1 = 0; //player 1
        int p2 = 0; //player 2
        System.out.println(p1);

        if(end == 1){
            return answer;
        }

        while(count == nums.length){
            if(nums[start] < nums[end] && count%2==0 ){
                p1 += nums[end];
                end --;
                count ++;
                System.out.println(p1);
                continue;
            }else if(nums[start] >= nums[end] && count%2==0){
                p1 += nums[start];
                start++;
                count ++;
                System.out.println(p1);
                continue;
            }else if(nums[start] < nums[end] && count%2==1){
                p2 += nums[end];
                end --;
                count ++;
                System.out.println(p2);
                continue;
            }else if(nums[start] >= nums[end] && count%2==1){
                p2 += nums[start];
                start++;
                count ++;
                System.out.println(p2);
                continue;
            }
        }
        System.out.println(p1);
        System.out.println(p2);
        if(p1 < p2){
            answer = false;
        }

        return answer;
    }

}*/

class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return fun(nums,0,nums.length-1,0,0,1); // turn = 플레이어 차례
    }

    boolean fun(int[] arr,int start,int end,int sum1,int sum2,int turn){
        if(start>end){ //더 이상 선택할 숫자가 없을 때
            if(sum1 >= sum2) return true; //플레이어 1이 이긴 경우
            else return false; //플레이어 2가 이긴 경우
        }

        if(turn == 1) //플레이어 1 : 둘중 하나라도 성립하는 경우 (위 코드에서) true 반환
            return fun(arr,start+1,end,sum1+arr[start],sum2,1-turn) || //첫번째를 선택한 경우.
                    fun(arr,start,end-1,sum1+arr[end],sum2,1-turn); // 마지막을 선태택한 경우.
        else //플레이어 2 : 두 경우 모두 성립하는 경우 true 반환
            return fun(arr,start+1,end,sum1,sum2+arr[start],1-turn) &&
                    fun(arr,start,end-1,sum1,sum2+arr[end],1-turn);
    }
}