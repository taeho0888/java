package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class boj_11559 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static char[][] board = new char[12][6];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int answer = 0;
        while (true) {
            ArrayList<int[]> toBombList = findToBomb();

            if (toBombList.isEmpty()) {
                break;
            } else {
                answer++;
            }

            for (int[] toBomb : toBombList) {
                int x = toBomb[0];
                int y = toBomb[1];

                board[x][y] = '.';
            }

            fillBlank();
        }

        System.out.println(answer);
    }

    static ArrayList<int[]> findToBomb() {
        ArrayList<int[]> toBombList = new ArrayList<>();

        boolean[][] visited = new boolean[12][6];
        Queue<int[]> queue = new ArrayDeque<>();
        Queue<int[]> cache = new ArrayDeque<>();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (visited[i][j] || board[i][j] == '.') {
                    continue;
                }

                char curColor = board[i][j];
                visited[i][j] = true;

                queue.clear();
                cache.clear();
                queue.offer(new int[] {i, j});
                cache.offer(new int[] {i, j});

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = now[0] + dx[k];
                        int ny = now[1] + dy[k];

                        if (0 <= nx && nx < 12 && 0 <= ny && ny < 6 && !visited[nx][ny] && board[nx][ny] == curColor) {
                            visited[nx][ny] = true;
                            queue.offer(new int[] {nx, ny});
                            cache.offer(new int[] {nx, ny});
                        }
                    }
                }

                if (cache.size() >= 4) {
                    toBombList.addAll(cache);
                }
            }
        }

        return toBombList;
    }

    static void fillBlank() {
        for (int i = 0; i < 6; i++) {
            Queue<Character> queue = new ArrayDeque<>();
            for (int j = 11; j >= 0; j--) {
                if (board[j][i] != '.') {
                    queue.offer(board[j][i]);
                }
            }

            for (int j = 11; j >= 0; j--) {
                if (!queue.isEmpty()) {
                    board[j][i] = queue.poll();
                } else {
                    board[j][i] = '.';
                }
            }
        }
    }
}
