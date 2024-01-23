import sys
input = sys.stdin.readline

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

def move_fireballs(board):
    new_board = [[[] for __ in range(N)] for _ in range(N)]

    # 이동시키기
    for x in range(N):
        for y in range(N):
            if len(board[x][y]) == 0:
                continue
            
            for fb in board[x][y]:
                nx = (x + dx[fb[2]]*fb[1]) % N
                ny = (y + dy[fb[2]]*fb[1]) % N
                new_board[nx][ny].append(fb)

    # 합칠 거 합치기
    for x in range(N):
        for y in range(N):
            if len(new_board[x][y]) < 2:
                continue

            m_sum = sum([fb[0] for fb in new_board[x][y]])
            new_m = m_sum // 5

            if new_m == 0:
                new_board[x][y] = []
                continue
            
            s_sum = sum([fb[1] for fb in new_board[x][y]])
            new_s = s_sum // len(new_board[x][y])

            all_even = False
            if sum([1 for fb in new_board[x][y] if fb[2] % 2 == 0]) == len(new_board[x][y]):
                all_even = True

            all_odd = False
            if sum([1 for fb in new_board[x][y] if fb[2] % 2 != 0]) == len(new_board[x][y]):
                all_odd = True

            new_board[x][y] = []
            if all_even or all_odd:
                for new_d in [0, 2, 4, 6]:
                    new_board[x][y].append([new_m, new_s, new_d])
            else:
                for new_d in [1, 3, 5, 7]:
                    new_board[x][y].append([new_m, new_s, new_d])

    # 보드 갱신
    return new_board


def count_fireballs(board):
    count = 0;
    for x in range(N):
        for y in range(N):
            if len(board[x][y]) == 0:
                continue

            for fb in board[x][y]:
                count += fb[0]

    return count


N, M, K = map(int, input().split())
board = [[[] for __ in range(N)] for _ in range(N)]
for _ in range(M):
    r, c, m, s, d = map(int, input().split())
    board[r-1][c-1].append([m, s, d])

for _ in range(K): 
    board = move_fireballs(board)

print(count_fireballs(board))
