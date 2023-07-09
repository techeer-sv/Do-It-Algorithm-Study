
class Solution {
    /*
        
    */
    ArrayList<Long> numList;
    public boolean PredictTheWinner(int[] nums) {
        numList = new ArrayList<Long>(nums.length);
        long sum = 0;

        for (long n : nums){
            numList.add(n);
            sum += n;
        }

        long play1MaxScore = playGame(0, nums.length-1, 0);
        System.out.println(play1MaxScore);

        return sum - play1MaxScore <= play1MaxScore;
    }

    public Long playGame(int left, int right, int index){
        if (left == right){
            return numList.get(left);
        }

        if (index % 2 == 1){
            return numList.get(left) > numList.get(right) 
            ? playGame(left+1, right, index+1) : playGame(left, right-1, index+1);
        } else {
            return Math.max(numList.get(left) + playGame(left+1, right, index+1),
            numList.get(right) + playGame(left, right-1, index+1));
        }
    }
}