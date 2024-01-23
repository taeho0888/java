package 분할정복;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class boj_2447 {
    static int N;
    static char[][] board;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();

        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = '*';
            }
        }

        fillBoard(0, 0, N);
        printBoard();
    }

    static void fillBoard(int x, int y, int width) {
        if (width == 1) {
            return;
        }

        int unit = width / 3;

        // 가운데 뚫기
        for (int i = x + unit; i < x + unit * 2; i++) {
            for (int j = y + unit; j < y + unit * 2; j++) {
                board[i][j] = ' ';
            }
        }

        // 나머지 넘기기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;
                else fillBoard(x + i * unit, y + j * unit, unit);
            }
        }
    }

    static void printBoard() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(board[i][j]);
            }
            bw.write('\n');
        }
        bw.close();
    }
}
