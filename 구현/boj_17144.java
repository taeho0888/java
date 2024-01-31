package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17144 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int upCleaner = -1;
    static int downCleaner = -1;
    static int[][] board;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == -1) {
                    if (upCleaner == -1) {
                        upCleaner = i;
                    } else {
                        downCleaner = i;
                    }
                }
            }
        }

        for (int T = 0; T < t; T++) {
            spreadDust();
            clearDust();
        }

        System.out.println(getDust());
    }

    static void spreadDust() {
        int[][] newBoard = new int[N][M];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (board[x][y] == -1) {
                    continue;
                }

                int ogDust = board[x][y];
                int movingDust = board[x][y] / 5;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (0 <= nx && nx < N && 0 <= ny && ny < M && board[nx][ny] != -1) {
                        newBoard[nx][ny] += movingDust;
                        ogDust -= movingDust;
                    }
                }

                newBoard[x][y] += ogDust;
            }
        }

        newBoard[upCleaner][0] = -1;
        newBoard[downCleaner][0] = -1;
        board = newBoard;
    }
    
    static void clearDust() {
        // 위에꺼 먼저 왼 -> 위 -> 오 -> 아래
        for (int i = upCleaner; i >= 1; i--) {
            board[i][0] = board[i-1][0];
        }
        board[upCleaner][0] = 0;
        
        for (int i = 0; i < M - 1; i++) {
            board[0][i] = board[0][i+1];
        }
        
        for (int i = 0; i < upCleaner; i++) {
            board[i][M-1] = board[i+1][M-1];
        }
        
        for (int i = M - 1; i >= 1; i--) {
            board[upCleaner][i] = board[upCleaner][i-1];
        }
        
        
        // 아래꺼 왼 -> 아래 -> 오 -> 위
        for (int i = downCleaner; i < N - 1; i++) {
            board[i][0] = board[i+1][0];
        }
        board[downCleaner][0] = 0;
        
        for (int i = 0; i < M - 1; i++) {
            board[N-1][i] = board[N-1][i+1];
        }
        
        for (int i = N - 1; i > downCleaner; i--) {
            board[i][M-1] = board[i-1][M-1];
        }
        
        for (int i = M - 1; i >= 1; i--) {
            board[downCleaner][i] = board[downCleaner][i-1];
        }
        
        board[upCleaner][0] = -1;
        board[downCleaner][0] = -1;
    }

    static int getDust() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == -1) {
                    continue;
                }
                count += board[i][j];
            }
        }

        return count;
    }
}
