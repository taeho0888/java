package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11049 {
    static int N;
    static int[][] matrices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrices = new int[N + 1][2];

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrices[i][0] = Integer.parseInt(st.nextToken());
            matrices[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][N + 1];

        for (int gap = 2; gap <= N; gap++) {
            for (int i = 1; i <= N - gap + 1; i++) {
                int j = i + gap - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = matrices[i][0] * matrices[k][1] * matrices[j][1];
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + cost);
                }
            }
        }
        System.out.println(dp[1][N]);
    }

}
