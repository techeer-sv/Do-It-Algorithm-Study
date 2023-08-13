def solution(n, costs):
    answer = 0
    INF = int(1e9)
    graph = [[INF] * (n) for _ in range(n)]

    for i in range(n):
        graph[i][i] = 0

    for cost in costs:
        a, b, c = cost
        graph[a][b] = c
        graph[b][a] = c

    for k in range(n):
        for i in range(n):
            for j in range(n):
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

    for a in range(n):
        print()
        for b in range(n):
            print(graph[a][b], end=' ')

    for i in range(n):
        for j in range(n):
            if graph[i][j] != INF:
                answer += graph[i][j]

    return answer

print(solution(4, [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]))
