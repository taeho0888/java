package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17404 {
    static int N;
    static int[][] cost;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[N + 1][3][3];

        // dp[1]
        for (int i = 0; i < 3; i++) {
            dp[1][i][i] = cost[1][i];
            dp[1][i][(i+1)%3] = 1001;
            dp[1][i][(i+2)%3] = 1001;
        }

        // dp[i][j][k] = 1번 집을 k로 칠하고, i번 집을 j 색깔로 칠했을 때 최솟값
        // dp[i][j][k] = min(dp[i - 1][j + 1][k], dp[i - 1][j + 2][k]) + cost[i][j]
        // dp[2] ~ dp[N - 1]
        for (int i = 2; i < N; i++) {
            for (int k = 0; k < 3; k++) {
                dp[i][0][k] = Math.min(dp[i-1][1][k], dp[i-1][2][k]) + cost[i][0];
                dp[i][1][k] = Math.min(dp[i-1][0][k], dp[i-1][2][k]) + cost[i][1];
                dp[i][2][k] = Math.min(dp[i-1][0][k], dp[i-1][1][k]) + cost[i][2];
            }
        }

        // dp[N]
        int dp1 = cost[N][0] + Arrays.stream(new int[] {
            dp[N-1][1][1], dp[N-1][1][2], dp[N-1][2][1], dp[N-1][2][2]
        }).min().getAsInt();
        int dp2 = cost[N][1] + Arrays.stream(new int[] {
            dp[N-1][0][0], dp[N-1][0][2], dp[N-1][2][0], dp[N-1][2][2]
        }).min().getAsInt();
        int dp3 = cost[N][2] + Arrays.stream(new int[] {
            dp[N-1][0][0], dp[N-1][0][1], dp[N-1][1][0], dp[N-1][1][1]
        }).min().getAsInt();

        System.out.println(Arrays.stream(new int[] {dp1, dp2, dp3}).min().getAsInt());
    }
}
