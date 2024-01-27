package 그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_4485 {
    static final int INF = 1_000_000_000;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int testCase = 0;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            
            if (N == 0) break;
            else testCase++;

            int[][] board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int minRoot = findMinRoot(N, board);
            bw.write("Problem " + testCase + ": " + minRoot + '\n');
        }
        bw.flush();
        bw.close();
    }

    static int findMinRoot(int N, int[][] board) {
        int[][] distances = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distances[i], INF);
        }
        distances[0][0] = board[0][0];

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 0, board[0][0]));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (board[now.x][now.y] > distances[now.x][now.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                int dist = distances[now.x][now.y] + board[nx][ny];

                if (dist < distances[nx][ny]) {
                    distances[nx][ny] = dist;
                    queue.offer(new Node(nx, ny, dist));
                }
            }
        }

        return distances[N-1][N-1];
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
