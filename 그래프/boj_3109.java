package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3109 {
    static final int[] dx = {-1, 0, 1};
    static final int[] dy = {1, 1, 1};
    static boolean[][] visited;
    static boolean flag;
    static char[][] map;
    static int answer = 0;
    static int R, C;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        visited = new boolean[R][C];
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            flag = false;
            dfs(i, 0);
            printVisited();
        }
        System.out.println(answer);
    }

    static void dfs(int x, int y) {
        if (y == C-1) {
            answer++;
            flag = true;
            visited[x][y] = true;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isOkay(nx, ny) && !visited[nx][ny] && map[nx][ny] == '.') {
                dfs(nx, ny);
                if (flag) {
                    visited[x][y] = true;
                    return;
                }
                else {
                    visited[x][y] = true;
                }
            }
        }
    }

    static boolean isOkay(int x, int y) {
        return (0 <= x && x < R && 0 <= y && y < C);
    }

    static void printVisited() {
        for (int i = 0; i < R; i++) {
            System.out.println();
            for (int j = 0; j < C; j++) {
                if (visited[i][j]) System.out.print("1 ");
                else System.out.print("0 ");
            }
        }
        System.out.println();
    }
}
