# 45656 가장 중요 인접한 모든 자리의 차이가 1이어야함
# 조건> N이 주어질 때 길이가 N. 0~9까지 모든 숫자가 등장해야함.
# 위 조건들을 충족시키는 계단 수가 몇 개 있는지 구하시오. 단 0으로 시작하지 않음
# input > 1 <= N <= 100, 자연수
# output> 정답 % 1,000,000,000
# hint> N이 1~40까지 답을 전부 더하면 126,461,847,755
# 블로그 참고하였습니다.


n = int(input())
MOD = 1000000000

# dp[i][j][k]: 길이가 i이고 마지막 숫자가 j이며, 등장한 숫자를 나타내는 비트마스크가 k인 계단 수의 개수
dp = [[[0] * (1 << 10) for _ in range(10)] for _ in range(n+1)]

# 초기 상태 설정
for i in range(1, 10):
    dp[1][i][1 << i] = 1

# 길이가 2부터 n까지 반복
for i in range(2, n+1):
    for j in range(10):
        for k in range(1 << 10):
            if j > 0:
                dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i-1][j-1][k]) % MOD
            if j < 9:
                dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i-1][j+1][k]) % MOD

# 0부터 9까지 숫자가 모두 등장하는 계단 수의 개수 계산
answer = 0
for j in range(10):
    answer = (answer + dp[n][j][(1 << 10) - 1]) % MOD

print(answer)
