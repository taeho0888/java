package DP;

import java.io.*;
import java.util.*;

public class boj_2240 {
    static int T, W;
    static int answer;
    static int[] plumAt;
    static ArrayDeque<Integer> selected = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        plumAt = new int[T + 1];
        for (int t = 1; t < T + 1; t++) {
            plumAt[t] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[T + 1][W + 1];

        if (plumAt[1] == 1) dp[1][0] = 1;
        else dp[1][1] = 1;

        for (int t = 2; t < T + 1; t++) {
            // w = 0 )
            if (plumAt[t] == 1) {
                dp[t][0] = dp[t-1][0] + 1; 
            }
            else {
                dp[t][0] = dp[t-1][0];
            }

            // 1 <= w <= W )
            for (int w = 1; w < W + 1; w++) {
                // 자리를 바꿔야 자두를 먹는 경우
                if (getPlumWhenSwitch(t, w)) {
                    dp[t][w] = Math.max(dp[t-1][w-1] + 1, dp[t-1][w]);
                }
                // 자리를 바꾸지 않아야 자두를 먹는 경우
                else {
                    dp[t][w] = Math.max(dp[t-1][w] + 1, dp[t-1][w-1]);
                }
            }
        }
        System.out.println(Arrays.stream(dp[T]).max().getAsInt());
    }

    static boolean getPlumWhenSwitch(int t, int w) {
        // 현재 위치가 1일 때, w가 홀수면 1, 짝수면 2
        // 현재 위치가 2일 때, w가 홀수면 2, 짝수면 1
        if (plumAt[t] == 1) return (w % 2 == 1);
        else return (w % 2 == 0);
    }
}

