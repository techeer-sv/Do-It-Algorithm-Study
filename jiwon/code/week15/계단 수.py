# 45656 가장 중요 인접한 모든 자리의 차이가 1이어야함
# 조건> N이 주어질 때 길이가 N. 0~9까지 모든 숫자가 등장해야함.
# 위 조건들을 충족시키는 계단 수가 몇 개 있는지 구하시오. 단 0으로 시작하지 않음
# input > 1 <= N <= 100, 자연수
# output> 정답 % 1,000,000,000
# hint> N이 1~40까지 답을 전부 더하면 126,461,847,755

n = int(input())
d = [0] * 100