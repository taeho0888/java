package 구현;

import java.io.*;
import java.util.*;

public class boj_17825 {
    static int[] turn = new int[10];
    static int[] piece = new int[] {-1, 0, 0, 0, 0};
    static int maxScore = -1;
    static final int[] score = {
        // 0 ~ 9
        0, 2, 4, 6, 8, 10, 13, 16, 19, 25,
        // 10 ~ 19
        12, 14, 16, 18, 20, 22, 24, 22, 24, 26,
        // 20 ~ 29
        28, 30, 28, 27, 26, 30, 35, 40, 32, 34,
        // 30 ~ 32
        36, 38, 0
    };
    static final int[][] board = {
        {0,  1,  2,  3,  4,  5}, // 0
        {0,  2,  3,  4,  5, 10}, // 1
        {0,  3,  4,  5, 10, 11}, // 2
        {0,  4,  5, 10, 11, 12}, // 3
        {0,  5, 10, 11, 12, 13}, // 4
        {0,  6,  7,  8,  9, 25}, // 5
        {0,  7,  8,  9, 25, 26}, // 6
        {0,  8,  9, 25, 26, 27}, // 7
        {0,  9, 25, 26, 27, 32}, // 8
        {0, 25, 26, 27, 32, 32}, // 9
        {0, 11, 12, 13, 14, 17}, // 10
        {0, 12, 13, 14, 17, 18}, // 11
        {0, 13, 14, 17, 18, 19}, // 12
        {0, 14, 17, 18, 19, 20}, // 13
        {0, 15, 16,  9, 25, 26}, // 14
        {0, 16,  9, 25, 26, 27}, // 15
        {0,  9, 25, 26, 27, 32}, // 16
        {0, 18, 19, 20, 21, 28}, // 17
        {0, 19, 20, 21, 28, 29}, // 18
        {0, 20, 21, 28, 29, 30}, // 19
        {0, 21, 28, 29, 30, 31}, // 20
        {0, 22, 23, 24,  9, 25}, // 21
        {0, 23, 24,  9, 25, 26}, // 22
        {0, 24,  9, 25, 26, 27}, // 23
        {0,  9, 25, 26, 27, 32}, // 24
        {0, 26, 27, 32, 32, 32}, // 25
        {0, 27, 32, 32, 32, 32}, // 26
        {0, 32, 32, 32, 32, 32}, // 27
        {0, 29, 30, 31, 27, 32}, // 28
        {0, 30, 31, 27, 32, 32}, // 29
        {0, 31, 27, 32, 32, 32}, // 30
        {0, 27, 32, 32, 32, 32}, // 31
        {0, 32, 32, 32, 32, 32}  // 32
    };

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) turn[i] = Integer.parseInt(st.nextToken());

        dfs(0, 0);
        System.out.println(maxScore);
    }

    static void dfs(int depth, int sum) {
        if (depth == 10) {
            maxScore = Math.max(maxScore, sum);
            return;
        }

        int dice = turn[depth];

        if (piece[1] != 32) {
            int tmp1 = piece[1];
            int next1 = board[tmp1][dice];
            if (!contains(next1)) {
                piece[1] = next1;
                dfs(depth + 1, sum + score[next1]);
                piece[1] = tmp1;
            }
        }

        if (piece[2] != 32) {
            int tmp2 = piece[2];
            int next2 = board[tmp2][dice];
            if (!contains(next2)) {
                piece[2] = next2;
                dfs(depth + 1, sum + score[next2]);
                piece[2] = tmp2;
            }
        }

        if (piece[3] != 32) {
            int tmp3 = piece[3];
            int next3 = board[tmp3][dice];
            if (!contains(next3)) {
                piece[3] = next3;
                dfs(depth + 1, sum + score[next3]);
                piece[3] = tmp3;
            }
        }

        if (piece[4] != 32) {
            int tmp4 = piece[4];
            int next4 = board[tmp4][dice];
            if (!contains(next4)) {
                piece[4] = next4;
                dfs(depth + 1, sum + score[next4]);
                piece[4] = tmp4;
            }
        }
    }

    static boolean contains(int key) {
        for (int i = 1; i <= 4; i++) {
            if (piece[i] == 32) continue;
            else if (piece[i] == key) return true;
        }
        return false;
    }
}
