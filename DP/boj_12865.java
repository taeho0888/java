package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_12865 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] weight = new int[N+1];
        int[] value = new int[N+1];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp = new int[N+1][K+1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= K; w++) {
                if (weight[i] <= w) {
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i]);
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        System.out.println(dp[N][K]);
        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
