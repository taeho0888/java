package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2638 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] board;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (countCheeze() > 0) {
            time++;

            for (int[] node : findContact()) {
                int x = node[0];
                int y = node[1];

                board[x][y] = 0;
            }
        }

        System.out.println(time);
    }

    static ArrayList<int[]> findContact() {
        ArrayList<int[]> contactList = new ArrayList<>();

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});

        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        int[][] contacted = new int[N][M];

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                    if (board[nx][ny] == 1) {
                        contacted[nx][ny]++;
                    } else {
                        queue.offer(new int[] {nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (contacted[i][j] >= 2) {
                    contactList.add(new int[] {i, j});
                }
            }
        }

        return contactList;
    }

    static int countCheeze() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
