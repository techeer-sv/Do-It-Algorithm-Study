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

/*ex : [1,5,233,7]
(가나다라로 표기해보겠습니다.)

1. 처음 호출: fun(nums, 0, 3, 0, 0, 1) // 플레이어 1
플레이어 1이 1을 선택: fun(nums, 1, 3, 1, 0, 0) // 가
플레이어 1이 7을 선택: fun(nums, 0, 2, 7, 0, 0) // 나

2. 첫 번째 선택: fun(nums, 1, 3, 1, 0, 0) // 플레이어2 : 가
플레이어 2가 5를 선택: fun(nums, 2, 3, 1, 5, 1) // 가-1
플레이어 2가 7을 선택: fun(nums, 1, 2, 1, 7, 1) // 가-2

3. 첫 번째 선택의 첫 번째 경우: fun(nums, 2, 3, 1, 5, 1) // 가-1의 결과
start와 end가 같기 때문에 더 이상 선택할 숫자가 없습니다.
sum1 >= sum2이기 때문에, 이 경우는 플레이어 1이 이길 수 있으므로 true를 반환

4. 첫 번째 선택의 두 번째 경우: fun(nums, 1, 2, 1, 7, 1) // 가-2
플레이어 1이 233을 선택: fun(nums, 2, 2, 234, 7, 0) // 가-2-1
플레이어 1이 5를 선택: fun(nums, 1, 1, 6, 7, 0) //가-2-2

5. 첫 번째 선택의 두 번째 경우의 첫 번째 경우: fun(nums, 2, 2, 234, 7, 0) // 가-2-1의 결과
start와 end가 같기 때문에 더 이상 선택할 숫자가 없습니다.
sum1 >= sum2이기 때문에, 이 경우는 플레이어 1이 이길 수 있으므로 true를 반환

6. 첫 번째 선택의 두 번째 경우의 두 번째 경우: fun(nums, 1, 1, 6, 7, 0) // 가-2-2의 결과
start와 end가 같기 때문에 더 이상 선택할 숫자가 없습니다.
sum1 >= sum2이 아니기 때문에, 이 경우는 플레이어 1이 이길 수 없으므로 false를 반환

7. 두 번째 선택: fun(nums, 0, 2, 7, 0, 0) // 나
플레이어 2가 5를 선택: fun(nums, 1, 2, 7, 5, 1) // 나-1
플레이어 2가 233을 선택: fun(nums, 0, 1, 7, 233, 1) // 나-2


8. 두 번째 선택의 첫 번째 경우: fun(nums, 1, 2, 7, 5, 1) //나-1
플레이어 1이 233을 선택: fun(nums, 2, 2, 240, 5, 0) // 나-1-1
플레이어 1이 5를 선택: fun(nums, 1, 1, 12, 5, 0) //나-1-2

9. 두 번째 선택의 첫 번째 경우의 첫 번째 경우: fun(nums, 2, 2, 240, 5, 0) // 나-1-1의 결과
start와 end가 같기 때문에 더 이상 선택할 숫자가 없습니다.
sum1 >= sum2이기 때문에, 이 경우는 플레이어 1이 이길 수 있으므로 true를 반환

10. 두 번째 선택의 첫 번째 경우의 두 번째 경우: fun(nums, 1, 1, 12, 5, 0) // 나-1-2의 결과
start와 end가 같기 때문에 더 이상 선택할 숫자가 없습니다.
sum1 >= sum2이기 때문에, 이 경우는 플레이어 1이 이길 수 있으므로 true를 반환


모든 재귀 호출이 끝나고, 최종적으로 PredictTheWinner 함수는 true를 반환합니다.
(6번이 false를 반환하지만 or조건에 의해 5번과 6번중 5번이 true이므로 true를 반환합니다.)*/