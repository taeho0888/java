package 백트래킹;

import java.io.*;
import java.util.*;

public class boj_1987 {
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int R, C;
    static int answer = 1;
    static char[][] board;
    static Set<Character> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st1.nextToken());
        C = Integer.parseInt(st1.nextToken());

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        visited.add(board[0][0]);
        dfs(1, 0, 0);
        System.out.println(answer);
    }

    static void dfs(int depth, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }

            char curChar = board[nx][ny];

            // 탈출 조건
            if (visited.contains(curChar)) {
                answer = Math.max(answer, depth);
                continue;
            }

            // 백트래킹
            visited.add(curChar);
            dfs(depth + 1, nx, ny);
            visited.remove(curChar);
        }
    }
}

