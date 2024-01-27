package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1261 {
    static final int INF = 100_000;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int N, M;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        System.out.println(getMin());
    }

    static int getMin() {
        int[][] distances = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(distances[i], INF);
        distances[0][0] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (distances[now.x][now.y] < now.dist) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    int dist = now.dist + board[nx][ny];
                    if (dist < distances[nx][ny]) {
                        distances[nx][ny] = dist;
                        queue.offer(new Node(nx, ny, dist));
                    }
                }
            }
        }
        
        return distances[N-1][M-1];
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
