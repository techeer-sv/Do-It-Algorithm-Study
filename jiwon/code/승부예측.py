from typing import List


class Solution:
    def PredictTheWinner(self, nums: List[int]) -> bool:
        d = [0] * len(nums)
        i=0
        while len(nums) > 1:
            d[i] = max(nums[0], nums[len(nums) - 1])
            if d[i] == nums[0]:
                nums.pop(0)
                # print(nums)
            else:
                nums.pop(len(nums) - 1)
                # print(nums)
            i += 1
        d_odd = sum(d[0:i:2])
        d_even = sum(d[1:i:2])

        if d_odd + nums[0] > d_even:
            return True
        else:
            return False