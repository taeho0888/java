package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_7576 {
    static final int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static LinkedList<int[]> tomato = new LinkedList<>();
    static int days = 0, maturedTomato = 0, target = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    tomato.add(new int[] {i, j});
                    maturedTomato++;
                    target++;
                }
                else if (map[i][j] == 0) {
                    target++;
                }
            }
        }

        while (!tomato.isEmpty()) {
            days++;
            bfs();
        }

        if (maturedTomato == target) System.out.println(--days);
        else System.out.println(-1);
    }

    static void bfs() {
        LinkedList<int[]> newList = new LinkedList<>();

        for (int[] now : tomato) {
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (isOkay(nx, ny) && map[nx][ny] == 0) {
                    maturedTomato++;
                    map[nx][ny] = 1;
                    newList.add(new int[] {nx, ny});
                }
            }
        }

        tomato = newList;
    }

    static boolean isOkay(int x, int y) {
        return (0 <= x && x < N && 0 <= y && y < M);
    }
}
