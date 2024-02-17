package 그래프;

import java.io.*;
import java.util.*;

public class boj_1937 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        PriorityQueue<Node> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                queue.offer(new Node(i, j, board[i][j]));
            }
        }

        int[][] dp = new int[N][N];
        int answer = 0;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            dp[now.x][now.y] = 1;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && board[nx][ny] > board[now.x][now.y]) {
                    dp[now.x][now.y] = Math.max(dp[now.x][now.y], dp[nx][ny] + 1);
                }
            }

            answer = Math.max(answer, dp[now.x][now.y]);
        }

        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        
        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int value;

        Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
