package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17135 {
    static int N, M, D;
    static int[][] map;
    static int[] archer;
    static int answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 배치하는 경우의 수
        archer = new int[3];
        putArcher(0);
        System.out.println(answer);
    }

    static void putArcher(int depth) {
        if (depth == 3) {
            play();
            return;
        }

        int archerID = depth - 1;
        int start = (archerID == -1) ? 0 : archer[archerID] + 1;
        int end = M - 2 + depth;
        for (int i = start; i < end; i++) {
            archer[depth] = i;
            putArcher(depth + 1);
        }
    }

    static void play() {
        int kill = 0;
        int[][] curMap = new int[N][M];
        for (int id = 0; id < N; id++) curMap[id] = map[id].clone();

        for (int i = 0; i < N; i++) {
            // 궁수가 적 찾아
            int[][] toKill = new int[3][2];
            for (int id = 0; id < 3; id++) {
                toKill[id] = findEnemy(archer[id], curMap);
            }

            // 궁수가 공격해
            for (int[] curEnemy : toKill) {
                int x = curEnemy[0], y = curEnemy[1];
                if (x == -1 && y == -1) continue;
                else if (curMap[x][y] == 1) {
                    curMap[x][y] = 0;
                    kill++;
                }
            }

            // 적 이동해
            for (int id = N - 1; id >= 1; id--) {
                curMap[id] = curMap[id-1].clone();
            }
            Arrays.fill(curMap[0], 0);
        }

        answer = Math.max(answer, kill);
    }

    static int[] findEnemy(int archer, int[][] map) {
        // 왼 위 오
        int[] dx = {0, -1, 0};
        int[] dy = {-1, 0, 1};
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new int[] {N, archer, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int dist = now[2];

            if (x != N && map[x][y] == 1) {
                return new int[] {x, y};
            }

            for (int dir = 0; dir < 3; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || dist >= D || visited[nx][ny]) {
                    continue;
                }

                queue.offer(new int[] {nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }
        return new int[] {-1, -1};
    }
}
