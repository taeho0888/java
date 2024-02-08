package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14500 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int answer = -1;
    static int[][][] tShape = {
        {{0, 0}, {1, 0}, {2, 0}, {1, 1}}, // ㅏ
        {{0, 0}, {0, 1}, {0, 2}, {1, 1}}, // ㅜ
        {{0, 0}, {-1, 0}, {1, 0}, {0, -1}}, // ㅓ
        {{0, 0}, {0, 1}, {0, 2}, {-1, 1}} // ㅗ
    };

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;
            }
        }

        // ㅗ 모양
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                for (int[][] shape : tShape) {
                    int curSum = 0;
                    boolean flag = false;

                    for (int[] n : shape) {
                        int nx = x + n[0];
                        int ny = y + n[1];

                        if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                            curSum += board[nx][ny];
                        } else {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) continue;
                    else {
                        answer = Math.max(answer, curSum);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + board[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }
}
