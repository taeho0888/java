package DP;

import java.io.*;
import java.util.*;

public class boj_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][3];

        // 입력 받음
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
            board[i][2] = Integer.parseInt(st.nextToken());
        }

        // 최댓값과 최솟값 저장하는 2차원 배열 선언
        int[][] max_dp = new int[N][3];
        int[][] min_dp = new int[N][3];

        // 첫째 줄은 board랑 같음
        max_dp[0] = min_dp[0] = board[0].clone();

        // dp 로직
        for (int i = 1; i < N; i++) {
            max_dp[i][0] = board[i][0] + Arrays.stream(max_dp[i-1], 0, 2).max().getAsInt();
            max_dp[i][1] = board[i][1] + Arrays.stream(max_dp[i-1], 0, 3).max().getAsInt();
            max_dp[i][2] = board[i][2] + Arrays.stream(max_dp[i-1], 1, 3).max().getAsInt();

            min_dp[i][0] = board[i][0] + Arrays.stream(min_dp[i-1], 0, 2).min().getAsInt();
            min_dp[i][1] = board[i][1] + Arrays.stream(min_dp[i-1], 0, 3).min().getAsInt();
            min_dp[i][2] = board[i][2] + Arrays.stream(min_dp[i-1], 1, 3).min().getAsInt();
        }

        int max = Arrays.stream(max_dp[N - 1]).max().getAsInt();
        int min = Arrays.stream(min_dp[N - 1]).min().getAsInt();

        System.out.println(max + " " + min);
    }
}
