// Predict the Winner
// https://leetcode.com/problems/predict-the-winner/
// 두 player는 모두 최적의 결과를 생각하며 대결

public class DoitJava_Week13 {
    public static void main(String[] args) {
        System.out.println(PredictTheWinner(new int[]{1,5,2})); // false
        System.out.println(PredictTheWinner(new int[]{1,5,233,7})); // true
        System.out.println(PredictTheWinner(new int[]{1,2,99})); // true
        System.out.println(PredictTheWinner(new int[]{2,4,55,6,8})); // false
    }

    public static boolean PredictTheWinner(int [] nums){
        return getWinner(nums, 0, nums.length-1) >= 0;
    }

    // start와 end는 인덱스
    public static int getWinner(int [] nums, int start, int end) {
        if (start == end)
            return nums[end];   // start == end == 0, length == 1일 때

        // 둘 다 음수 값인데 그 중에서도 최댓값으로
        int fromFirst = nums[start] - getWinner(nums, start+1, end);
        int fromSecond = nums[end] - getWinner(nums, start, end-1);
        return Math.max(fromFirst, fromSecond);
    }
}
