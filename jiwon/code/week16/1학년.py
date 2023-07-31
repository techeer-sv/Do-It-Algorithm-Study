n = int(input())

d = [[0, 0] for _ in range(n)]
array = list(map(int, input().split()))

count = 0
d[0][0] = array[0]
d[0][1] = array[0]

for i in range(n-1):
    for j in range(2):
        if j == 0:
            d[i + 1][j] = d[i][j] - array[i]
        elif j == 1:
            d[i + 1][j] = d[i][j] + array[i]
        if array[-1] == d[-2][0] or array[-1] == d[-2][1]:
            count += 1

print(count)
