package 백트래킹;

import java.io.*;
import java.util.*;

public class boj_17070 {
    static int N;
    static int[][] board;
    static int answer = 0;
    static Pipe pipe = new Pipe(0, 1, 0);

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs();
        System.out.println(answer);
    }

    public static void dfs() {
        if (pipe.x == N - 1 && pipe.y == N - 1) {
            answer++;
            return;
        }

        if (pipe.direction == 0) { // 가로
            if (canRight()) {
                pipe.y++;
                dfs();
                pipe.y--;
            }

            if (canCross()) {
                pipe.x++;
                pipe.y++;
                pipe.direction = 1;
                dfs();
                pipe.x--;
                pipe.y--;
                pipe.direction = 0;
            }
        } else if (pipe.direction == 1) { // 대각선
            if (canRight()) {
                pipe.y++;
                pipe.direction = 0;
                dfs();
                pipe.y--;
                pipe.direction = 1;
            }

            if (canCross()) {
                pipe.x++;
                pipe.y++;
                dfs();
                pipe.x--;
                pipe.y--;
            }

            if (canDown()) {
                pipe.x++;
                pipe.direction = 2;
                dfs();
                pipe.x--;
                pipe.direction = 1;
            }

        } else { //세로
            if (canCross()) {
                pipe.x++;
                pipe.y++;
                pipe.direction = 1;
                dfs();
                pipe.x--;
                pipe.y--;
                pipe.direction = 2;
            }

            if (canDown()) {
                pipe.x++;
                dfs();
                pipe.x--;
            }
        }
    }

    public static boolean canRight() {
        if (pipe.y + 1 >= N || board[pipe.x][pipe.y+1] != 0) return false;
        return true;
    }

    public static boolean canDown() {
        if (pipe.x + 1 >= N || board[pipe.x+1][pipe.y] != 0) return false;
        return true;
    }

    public static boolean canCross() {
        if (pipe.y + 1 >= N || board[pipe.x][pipe.y+1] != 0) return false;
        if (pipe.x + 1 >= N || board[pipe.x+1][pipe.y] != 0) return false;
        if (board[pipe.x+1][pipe.y+1] != 0) return false;
        return true;
    }

    public static class Pipe {
        int x;
        int y;
        int direction;

        public Pipe(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    } 
}
