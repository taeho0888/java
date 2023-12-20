package BFS;

import java.io.*;
import java.util.*;

public class boj_4963 {
    static int w, h;
    static int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
    static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};
    static int[][] map;
    static boolean[][] visited;
    static ArrayDeque<Node> queue = new ArrayDeque<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;
            map = new int[h][w];
            visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for (int j = 0; j < w;j++) {
                    map[i][j] = Integer.parseInt(st2.nextToken());
                }
            }
            
            int islandCount = 0;
            for (int x = 0; x < h; x++) {
                for (int y = 0; y < w; y++) {
                    if (visited[x][y]) continue;

                    if (map[x][y] == 0) {
                        visited[x][y] = true;
                        continue;
                    } else if (map[x][y] == 1) {
                        islandCount++;
                        bfs(new Node(x, y));
                    }

                }
            }
            bw.write(islandCount + "\n");
        }

        bw.close();
    }

    static void bfs(Node start) {
        queue.clear();
        queue.offer(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int k = 0; k < 8; k++) {
                int nx = node.x + dx[k];
                int ny = node.y + dy[k];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    continue;
                }
                if (map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Node(nx, ny));
                }
            }
        }
    }

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
