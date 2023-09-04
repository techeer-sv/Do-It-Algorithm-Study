from collections import deque

n, m = map(int, input().split())
graph = []
for i in range(n):
    graph.append(list(int, input().split()))
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(x, y):
    queue = deque()
    queue.append((x, y))
    visited = [[False] * m for _ in range(n)]
    visited[x][y] = True
    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            if graph[nx][ny] == 0:
                pass
            if graph[nx][ny] == 1:
                pass

