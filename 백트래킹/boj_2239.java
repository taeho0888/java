package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj_2239 {
    static final int N = 9;
    static int[][] board = new int[N][N];
    static ArrayList<int[]> zeroList = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(line.substring(j, j + 1));

                if (board[i][j] == 0) {
                    zeroList.add(new int[] {i, j});
                }
            }
        }

        dfs(0);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static boolean dfs(int index) {
        if (index == zeroList.size()) {
            return true;
        }

        int x = zeroList.get(index)[0];
        int y = zeroList.get(index)[1];

        for (int num = 1; num <= N; num++) {
            if (possible(x, y, num)) {
                board[x][y] = num;
                if (dfs(index + 1)) {
                    return true;
                }
                board[x][y] = 0;
            }
        }

        return false;
    }

    static boolean possible(int x, int y, int num) {
        for (int i = 0; i < N; i++) {
            // 가로 확인
            if (board[x][i] == num) return false;

            // 세로 확인
            if (board[i][y] == num) return false;
        }

        // 3x3 확인
        int sx = x / 3 * 3;
        int sy = y / 3 * 3;

        for (int i = sx; i < sx + 3; i++) {
            for (int j = sy; j < sy + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        
        return true;
    }
}
