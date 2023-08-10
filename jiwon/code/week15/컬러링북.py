class Solution:
    def solution(self, m, n, picture):
        visited = [[False] * n for _ in range(m)]
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        def dfs(x, y):
            visited[x][y] = True
            size = 1
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if nx < 0 or nx >= m or ny < 0 or ny >= n:
                    continue
                if picture[nx][ny] == picture[x][y] and not visited[nx][ny]:
                    size += dfs(nx, ny)
            return size

        numberOfArea = 0
        maxSizeOfOneArea = 0
        for i in range(m):
            for j in range(n):
                if picture[i][j] != 0 and not visited[i][j]:
                    numberOfArea += 1
                    maxSizeOfOneArea = max(maxSizeOfOneArea, dfs(i, j))

        return [numberOfArea, maxSizeOfOneArea]