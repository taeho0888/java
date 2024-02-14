package DP;

import java.io.*;
import java.util.*;

public class boj_1256 {
    static double[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        double K = Integer.parseInt(st.nextToken());

        dp = new double[N+1][M+1];
        getResult(N, M, K);

        if (check(N, M) < K) System.out.println(-1);
        else System.out.println(sb);
    }

    static void getResult(int a, int z, double k) {
        if (a == 0) {
            for (int i = 0; i < z; i++) sb.append("z");
            return;
        }

        if (z == 0) {
            for (int i = 0; i < a; i++) sb.append("a");
            return;
        }

        double check = check(a-1, z);
        if (k > check) {
            sb.append("z");
            getResult(a, z-1, k - check);
        } else {
            sb.append("a");
            getResult(a-1, z, k);
        }
    }

    static double check(int a, int z) {
        if (a == 0 || z == 0) return 1;
        if (dp[a][z] != 0) return dp[a][z];
        return dp[a][z] = Double.min(check(a-1, z) + check(a, z-1), 1_000_000_001);
    }
}
