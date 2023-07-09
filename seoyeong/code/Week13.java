// Predict the Winner
// https://leetcode.com/problems/predict-the-winner/

// 두 player는 모두 최적의 결과를 얻기 위해 대결
// player 2명이 nums 배열에서 원하는 인덱스(0, 앞)(n-1, 뒤)를 하나씩 뽑아 각 본인의 숫자에 더하는 것
// player 1이 이길 수 있거나 비길 수 있으면 return true

class Solution {
    public boolean calculateNum(int p1, int p2, List<Integer>n){
switch (n.size()){
            case 0:
                if (p1>=p2) return true;
                return false;
            case 1:
                if (p1+n.get(0)>=p2) return true;
                return false;
            case 2:
                if (p1+n.get(0)>=p2+n.get(1) || p1+n.get(1)>=p2+n.get(0)) return true;
                return false;
            case 3:
                int x = n.get(0);
                int y = n.get(1);
                int z = n.get(2);
                if (x>=y){
                    if (y>=z){
                        n.remove(0); n.remove(0);
                        return calculateNum(p1+x,p2+y,n);
                    }
                    n.remove(0); n.remove(1);
                    return calculateNum(p1+x,p2+z,n);
                }
                else{
                    if (x>=y){
                        n.remove(0); n.remove(1);
                        return calculateNum(p1+z,p2+x,n);
                    }
                    n.remove(1); n.remove(1);
                    return calculateNum(p1+z,p2+y,n);
                }
        }


        int front1 = n.get(0);
        int front2 = n.get(1);
        int back1 = n.get(n.size()-1);
        int back2 = n.get(n.size()-1);

        // player1이 앞 + player2가 앞
        n.remove(0); n.remove(0);
        boolean chooseFrontFront = calculateNum(p1+front1,p2+front2,n);
        // player1이 앞 + player2가 뒤
        n.add(0,front2); n.remove(n.size()-1);
        boolean chooseFrontBack = calculateNum(p1+front1,p2+back1,n);
        // player1이 뒤 + player2가 앞
        boolean chooseBackFront = calculateNum(p1+back1, p2+front1, n);
        // player1이 뒤 + player2가 뒤
        n.add(0,front1); n.remove(n.size()-1);
        boolean chooseBackBack = calculateNum(p1+back1, p2+back2, n);

        if (chooseFrontFront == true || chooseFrontBack == true || chooseBackFront == true || chooseBackBack == true) return true;
        return false;
    }

    public boolean PredictTheWinner(int[] nums) {
        int player1 = 0;
        int player2 = 0;
        List<Integer> n = new ArrayList<>();
        for (int x : nums) n.add(x);

        if (calculateNum(player1,player2,n) == true) return true;
        return false;
    }
}
