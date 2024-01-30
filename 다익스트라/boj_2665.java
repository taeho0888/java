package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj_2665 {
    static final int INF = Integer.MAX_VALUE;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        // distance[x][y] = 해당 노드에 갈 수 있는 최소한의 벽 변경 횟수
        int[][] distances = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(distances[i], INF);
        distances[0][0] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 0, 0));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.dist > distances[now.x][now.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    int dist = now.dist + ((board[now.x][now.y] == 1) ? 0 : 1);

                    if (dist < distances[nx][ny]) {
                        queue.offer(new Node(nx, ny, dist));
                        distances[nx][ny] = dist;
                    }
                }
            }
        }
        
        System.out.println(distances[N-1][N-1]);
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
