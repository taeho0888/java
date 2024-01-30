import heapq
import sys
input = sys.stdin.readline

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

N = int(input())
board = [list(map(int, list(input().strip()))) for _ in range(N)]

queue = [[0, 0, 0]] # [거리, x, y]
heapq.heapify(queue)

distances = [[float('inf') for _ in range(N)] for _ in range(N)]
distances[0][0] = 0

while queue:
    cur_dist, x, y = heapq.heappop(queue)

    if cur_dist > distances[x][y]:
        continue

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]

        if (0 <= nx < N) and (0 <= ny < N):
            dist = cur_dist + (1 if board[nx][ny] == 0 else 0)

            if dist < distances[nx][ny]:
                heapq.heappush(queue, [dist, nx, ny])
                distances[nx][ny] = dist

print(distances[N-1][N-1])
