package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_2206 {
    static final int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = (int) line.charAt(j) - '0';
            }
        }

        bfs();
        if (answer == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(answer);
    }

    static void bfs() {
        visited = new boolean[N][M][2];
        visited[0][0][0] = true;

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == N-1 && now.y == M-1) {
                answer = Math.min(answer, now.dist);
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                // 다음으로 갈 수 있는 경우의 수
                // 1. 현재 벽을 부순 상태이고, (nx, ny)가 벽이 아닌 상태
                if (now.breakWall && map[nx][ny] == 0 && !visited[nx][ny][1]) {
                    queue.offer(new Node(nx, ny, now.dist+1, true));
                    visited[nx][ny][1] = true;
                }
                // 2. 현재 벽을 부수지 않은 상태이고, (nx, ny)가 벽인 상태
                else if (!now.breakWall && map[nx][ny] == 1 && !visited[nx][ny][1]) {
                    queue.offer(new Node(nx, ny, now.dist+1, true));
                    visited[nx][ny][1] = true;
                }
                // 3. 현재 벽을 부수지 않은 상태이고, (nx, ny)가 벽이 아닌 상태
                else if (!now.breakWall && map[nx][ny] == 0 && !visited[nx][ny][0]) {
                    queue.offer(new Node(nx, ny, now.dist+1, false));
                    visited[nx][ny][0] = true;
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int dist;
        boolean breakWall;

        Node(int x, int y, int dist, boolean breakWall) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.breakWall = breakWall;
        }
    }
}
