package BFS;

import java.io.*;
import java.util.*;

public class boj_2178 {
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int N, M;
    static int answer = Integer.MAX_VALUE;
    static int[][] maze;
    static boolean[][] visited;
    static ArrayDeque<Node> queue = new ArrayDeque<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        maze = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0'; // 문자를 숫자로 변환하여 저장
            }
        }
        bfs(new Node(0, 0, 1));
        System.out.println(answer);
    }

    static void bfs(Node start) {
        visited[start.x][start.y] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.x == (N - 1) && node.y == (M - 1)) {
                answer = Math.min(answer, node.distance);
                continue;
            }

            for (int i = 0; i < 4; i ++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (maze[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new Node(nx, ny, node.distance + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int distance;
        Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
