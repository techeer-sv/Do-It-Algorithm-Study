n = int(input())
array = list(map(int, input().split()))

d = [[0] * 21 for _ in range(n)]
d[0][array[0]] = 1

for i in range(1, n-1):
    for j in range(21):
        if d[i-1][j] > 0:
            if j + array[i] <= 20:
                d[i][j+array[i]] += d[i-1][j]
            if j - array[i] >= 0:
                d[i][j-array[i]] += d[i-1][j]




print(d[n-2][array[-1]])
