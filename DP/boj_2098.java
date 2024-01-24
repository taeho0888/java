package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2098 {
    static final int INF = 16_000_001;
    static int[][] cost;
    static int[][] dp;
    static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        cost = new int[N][N];
        dp = new int[N][(1 << N) - 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        int answer = tsp(0, 1);
        System.out.println(answer);
    }

    static int tsp(int current, int visited) {
        // 모든 도시를 방문한 경우
        if (visited == (1 << N) - 1) {
            if (cost[current][0] == 0) {
                return INF;
            }
            return cost[current][0];
        }

        // 이미 계산된 경우
        if (dp[current][visited] != -1) {
            return dp[current][visited];
        }
        dp[current][visited] = INF;

        for (int i = 0; i < N; i++) {
            int next = 1 << i; // 다음 도시
            if ((visited & next) == 0 && cost[current][i] != 0) { // 아직 방문하지 않았고, 방문할 수 있는 도시인 경우
                dp[current][visited] = Math.min(dp[current][visited], tsp(i, visited | next) + cost[current][i]);
            }
        }
        return dp[current][visited];
    }
}
