package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2533 {
    static int N;
    static int[][] dp;
    static Node[] tree;
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree[s] = new Node(e, tree[s]);
            tree[e] = new Node(s, tree[e]);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int num) {
        visited[num] = true;
        dp[num][0] = 0;
        dp[num][1] = 1;

        for (Node next = tree[num]; next != null; next = next.next) {
            if (!visited[next.n]) {
                dfs(next.n);
                dp[num][0] += dp[next.n][1];
                dp[num][1] += Math.min(dp[next.n][0], dp[next.n][1]);
            }
        }
    }

    static class Node {
        int n;
        Node next;

        public Node(int n, Node next) {
            this.n = n;
            this.next = next;
        }
    }
}
