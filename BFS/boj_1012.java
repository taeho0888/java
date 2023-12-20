package BFS;

import java.io.*;
import java.util.*;

public class boj_1012 {
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int M, N, K;
    static int[][] map;
    static ArrayDeque<Node> queue = new ArrayDeque<>();
    static boolean[][] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            // 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // map 입력 받기
            map = new int[N][M];
            visited = new boolean[N][M];
            for (int i = 0; i < K; i++) {
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st1.nextToken());
                int x = Integer.parseInt(st1.nextToken());

                map[x][y] = 1;
            }

            // bfs 돌리기
            int answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j]) {
                        if (map[i][j] == 1) {
                            bfs(new Node(i, j));
                            answer++;
                        }
                        else {
                            visited[i][j] = true;
                        }
                    }
                }
            }
            bw.write(answer + "\n");
        }
        bw.close();
    }

    static void bfs(Node start) {
        queue.offer(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny] == 1) {
                    queue.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
