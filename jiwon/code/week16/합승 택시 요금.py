def solution(n, s, a, b, fares):
    INF = int(1e9)
    answer = 0
    graph = [[INF] * (n+1) for _ in range(n+1)]
    for i in range(1, n+1):
        graph[i][i] = 0
    for fare in fares:
        x, y, z = fare
        graph[x][y] = z
        graph[y][x] = z


    return answer

solution(6,4,6,2,[[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]])