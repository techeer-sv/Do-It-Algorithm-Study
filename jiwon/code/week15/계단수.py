
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