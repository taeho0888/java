package 구현;

import java.io.*;
import java.util.*;

public class boj_2636 {
    static final int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int[][] board;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        ArrayList<Node> curOutside = findOutside();
        int time = 0, lastOutside = curOutside.size();

        while (curOutside.size() > 0) {
            for (Node node : curOutside) {
                board[node.x][node.y] = 0;
            }

            lastOutside = curOutside.size();
            curOutside = findOutside();
            time++;
        }

        System.out.println(time + "\n" + lastOutside);
    }

    static ArrayList<Node> findOutside() {
        ArrayList<Node> outside = new ArrayList<>();
        ArrayDeque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        queue.push(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.pop();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (0 > nx || nx >= N || 0 > ny || ny >= M || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                if (board[nx][ny] == 1) outside.add(new Node(nx, ny));
                else queue.push(new Node(nx, ny));
            }
        }
        return outside;
    }

    static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
