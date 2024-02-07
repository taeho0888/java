import sys
input = sys.stdin.readline

dx, dy = [0, 0, -1, 1], [1, -1, 0, 0]
n, k = map(int, input().split())
color = [list(map(int, input().split())) for _ in range(n)]
board = [[[] for _ in range(n)]  for _ in range(n)]
chess = [[]]

for i in range(1, k+1):
    x, y, d = map(int, input().split())
    chess.append([x-1, y-1, d-1])
    board[x-1][y-1].append(i)


def change_dir(i):
    if chess[i][2] in [0, 2]:
        chess[i][2] += 1
    else:
        chess[i][2] -= 1


def move(i):
    x, y, d = chess[i]
    nx, ny = x + dx[d], y + dy[d]

    if 0 > nx or nx >= n or 0 > ny or ny >= n or color[nx][ny] == 2:
        change_dir(i)
        d = chess[i][2]
        nx, ny = x + dx[d], y + dy[d]
        if 0 > nx or nx >= n or 0 > ny or ny >= n or color[nx][ny] == 2:
            return False

    chess_up = []
    for id, num in enumerate(board[x][y]):
        if num == i:
            chess_up.extend(board[x][y][id:])
            board[x][y] = board[x][y][:id]
            break

    if color[nx][ny] == 1:
        chess_up = chess_up[::-1]

    for cur in chess_up:
        chess[cur][0], chess[cur][1] = nx, ny
        board[nx][ny].append(cur)

    if len(board[nx][ny]) >= 4:
        return True
    return False


time = 0
while True:
    is_end = False
    time += 1

    for i in range(1, k+1):
        is_end = move(i)
        if is_end:
            print(time)
            break
    if is_end:
        break
    
    if time >= 1000:
        print(-1)
        break
