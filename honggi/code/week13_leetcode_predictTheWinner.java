class Solution {
    ArrayList<Long> numList;
    int numL;
    public boolean PredictTheWinner(int[] nums) {
        numL = nums.length;
        numList = new ArrayList<Long>(numL);
        long sum = 0;

        for (long n : nums){
            numList.add(n);
            sum += n;
        }

        if (numL == 1)
            return true;

        long p1S = playGame(0, nums.length-1);
        
        return p1S >= sum-p1S;
    }

    public long playGame(int left, int right){
        if (left > right){
            return 0;
        }
        
        long leftChoice = numList.get(left) + Math.min(playGame(left+2, right),
            playGame(left+1, right-1));
        long rightChoice = numList.get(right) + Math.min(playGame(left+1, right-1),
            playGame(left, right-2));
        return Math.max(leftChoice, rightChoice);
    }

}
