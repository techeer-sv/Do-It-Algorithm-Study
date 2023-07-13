package Yunho.code.week12;

import java.util.*;


class Solution {
    public boolean PredictTheWinner(int[] nums) {
        Deque<Integer> deque = new LinkedList<>();
        for (int num : nums) {
            deque.offerLast(num);
        }

        int player1 = 0;
        int player2 = 0;
        boolean isPlayer1Turn = true;

        while (!deque.isEmpty()) {
            int select;
            if (deque.getFirst() - deque.getLast() >= 0) {
                select = deque.pollFirst();
            } else {
                select = deque.pollLast();
            }

            if (isPlayer1Turn) {
                player1 += select;
            } else {
                player2 += select;
            }

            isPlayer1Turn = !isPlayer1Turn;
        }

        return player1 >= player2;
    }
}
