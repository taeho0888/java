package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1103 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static boolean cycleExist = false;
    static boolean[][] visited;
    static char[][] board;
    static int[][] dp;
    static int n, m;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n][m];
        board = new char[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int answer = dfs(0, 0);
        if (cycleExist) System.out.println(-1);
        else System.out.println(answer);
    }

    static int dfs(int x, int y) {
        if (cycleExist) return -1;
        if (dp[x][y] != 0) return dp[x][y];

        boolean canGo = false;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * (board[x][y] - '0');
            int ny = y + dy[i] * (board[x][y] - '0');
            
            if (isOkay(nx, ny)) {
                canGo = true;

                if (visited[nx][ny]) {
                    cycleExist = true;
                    return -1;
                }

                visited[x][y] = true;
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
                visited[x][y] = false;
            }
        }
        
        if (!canGo) dp[x][y] = 1;
        return dp[x][y];
    }

    static boolean isOkay(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m && board[x][y] != 'H';
    }
}
