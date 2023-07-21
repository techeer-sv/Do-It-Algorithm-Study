str1 = input()
str2 = input()

def LCS(str1, str2):
    n = len(str1)
    m = len(str2)

    # dp 테이블 초기화
    dp = [[0] * (m + 1) for _ in range(n + 1)]


    for i in range(1, n + 1):
        for j in range(1, m + 1):
            # 문자가 같을때
            if str1[i-1] == str2[j-1]:
                # 대각선 위의 값 (즉, 이전 위치의 LCS 길이)에 1을 더한 값을 현재 위치에 저장
                dp[i][j] = dp[i - 1][j - 1] + 1
            # 문자가 다를때
            elif str1[i-1] != str2[j-1]:
                # 이전 행 또는 이전 열의 값 중 더 큰 값(즉, 더 긴 LCS)을 현재 위치에 저장
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])

    return dp[n][m]


print(LCS(str1, str2))