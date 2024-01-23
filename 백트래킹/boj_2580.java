package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2580 {
    static int[][] board = new int[9][9];
    static ArrayList<int[]> blank = new ArrayList<>();
    static boolean flag = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blank.add(new int[] {i, j});
                }
            }
        }

        fillBoard(0);
    }

    static void fillBoard(int num) {
        if (num == blank.size()) {
            if (flag) {
                return;
            }
            printBoard();
            flag = true;
            return;
        }

        int x = blank.get(num)[0];
        int y = blank.get(num)[1];

        for (int i = 1; i <= 9; i++) {
            if (isPossible(x, y, i)) {
                board[x][y] = i;
                fillBoard(num + 1);
                board[x][y] = 0;
            }
        }
    }

    static boolean isPossible(int x, int y, int i) {
        // 가로줄 체크
        for (int k = 0; k < 9; k++) {
            if (board[x][k] == i) {
                return false;
            }
        }

        // 세로줄 체크
        for (int k = 0; k < 9; k++) {
            if (board[k][y] == i) {
                return false;
            }
        }

        // 사각형 체크
        // 012  345  678
        int n = x / 3;
        int m = y / 3;
        for (int a = n*3; a < (n+1)*3; a++) {
            for (int b = m*3; b < (m+1)*3; b++) {
                if (board[a][b] == i) {
                    return false;
                }
            }
        }

        return true;
    }

    static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
