package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_15684 {
    static int N, M, H;
    static int[][] board;
    static Map<Integer, int[]> blank = new HashMap<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1;
        }

        int index = 0;
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j] != 1) blank.put(index++, new int[] {i, j});
            }
        }

        dfs(0, 0);
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static void dfs(int depth, int index) {
        if (depth > Math.min(blank.size(), Math.min(answer, 3))) {
            return;
        }

        if (isOkay()) {
            answer = Math.min(answer, depth);
        }

        for (int i = index; i < blank.size(); i++) {
            int[] now = blank.get(i);

            board[now[0]][now[1]] = 1;
            dfs(depth + 1, i + 1);
            board[now[0]][now[1]] = 0;
        }
    }

    static boolean isOkay() {
        for (int i = 1; i <= N; i++) {
            if (i != connected(i)) return false;
        }
        return true;
    }

    static int connected(int i) {
        for (int x = 1; x <= H; x++) {
            if (i-1 >= 1 && board[x][i-1] == 1) i--;
            else if (i < N && board[x][i] == 1) i++;
        }

        return i;
    }
}
