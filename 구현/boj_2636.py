import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def find_outside():
    outside = []
    visited = [[False]*M for _ in range(N)]
    visited[0][0] = True
    q = deque([(0, 0)])

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if (0 <= nx < N) and (0 <= ny < M) and not visited[nx][ny]:
                visited[nx][ny] = True
                if board[nx][ny] == 1:
                    outside.append((nx, ny))
                else:
                    q.append((nx, ny))

    return outside


outside = find_outside()
time, last_outside = 0, len(outside)
while (len(outside) > 0):
    for x, y in outside:
        board[x][y] = 0

    last_outside = len(outside)
    outside = find_outside()
    time += 1

print(time)
print(last_outside)