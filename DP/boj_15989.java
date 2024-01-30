package DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj_15989 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[10_001][4];

        // dp 생성 로직
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int n = 4; n <= 10_000; n++) {
            dp[n][1] = dp[n-1][1];
            dp[n][2] = dp[n-2][1] + dp[n-2][2];
            dp[n][3] = dp[n-3][1] + dp[n-3][2] + dp[n-3][3];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int answer = dp[n][1] + dp[n][2] + dp[n][3];
            bw.write(answer + "\n");
        }

        bw.close();
    }
}
