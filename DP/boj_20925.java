package DP;

import java.io.*;
import java.util.*;

public class boj_20925 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        
        int[] reqExp = new int[n+1];
        int[] addExp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            reqExp[i] = Integer.parseInt(st.nextToken());
            addExp[i] = Integer.parseInt(st.nextToken());
        }

        int[][] cost = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n+1][t+1]; // 사냥터 n에서 t분에 최대의 경험치

        for (int i = 1; i <= n; i++) {
            if (reqExp[i] == 0) dp[i][1] = addExp[i];
        }

        for (int time = 2; time <= t; time++) {
            for (int node = 1; node <= n; node++) {
                // 전과 동일한 사냥터에서 사냥할 경우
                if (dp[node][time-1] >= reqExp[node]) {
                    dp[node][time] = dp[node][time-1] + addExp[node];
                }

                // 사냥터를 이동했을 경우
                for (int next = 1; next <= n; next++) {
                    if (node == next) continue;

                    if (time > cost[next][node] && dp[next][time-cost[next][node]] >= reqExp[node]) {
                        dp[node][time] = Math.max(dp[node][time], dp[next][time-cost[next][node]]);
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) answer = Math.max(answer, dp[i][t]);
        System.out.println(answer);
    }
}
