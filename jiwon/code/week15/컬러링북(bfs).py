from collections import deque


class Solution:
    def solution(self, m, n, picture):
        visited = [[False] * (n) for _ in range(m)]
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        def bfs(x, y):
            color = picture[x][y]
            visited[x][y] = True
            size = 1

            queue = deque()
            queue.append((x, y))
            while queue:
                x, y = queue.popleft()
                # 현재 위치에서 네 방향으로의 위치 확인
                for i in range(4):
                    nx = x + dx[i]
                    ny = y + dy[i]
                    if nx < 0 or ny < 0 or nx >= m or ny >= n:
                        continue
                    if :
                        pass
            return size

        numberOfArea = 0
        maxSizeOfOneArea = 0
        for i in range(m):
            for j in range(n):
                if picture[i][j] != 0 and not visited[i][j]:
                    pass

        answer = [numberOfArea, maxSizeOfOneArea]
        return answer

print(Solution().solution(6, 4, [[1, 1, 1, 0], [1, 2, 2, 0], [1, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 3], [0, 0, 0, 3]]))
