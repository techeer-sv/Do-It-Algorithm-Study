public class Main {
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

}