package 구현;

import java.io.*;
import java.util.*;

public class boj_2174 {
    static int a, b, n, m;
    static int[] dx = {1, 0, -1, 0}; // 아래, 오른, 위, 왼
    static int[] dy = {0, 1, 0, -1}; //  S, E, N, W
    static Node[] robot;
    static int[][] board;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        board = new int[b][a];

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        robot = new Node[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Math.abs(b - Integer.parseInt(st.nextToken()));
            int d = getDir(st.nextToken());

            robot[i] = new Node(x, y, d);
            board[x][y] = i;
        }
        
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            int times = Integer.parseInt(st.nextToken());

            if (flag) continue;

            String error = move(num, command, times);

            if (error != null) {
                flag = true;
                bw.write(error);
            }
        }

        if (!flag) bw.write("OK\n");
        bw.close();
    }
    
    static String move(int num, String command, int times) {
        if (command.equals("L")) {
            robot[num].dir += times;
            robot[num].dir %= 4;
        } else if (command.equals("R")) {
            robot[num].dir -= times;
            while (robot[num].dir < 0) robot[num].dir += 4;
        } else {
            int x = robot[num].x;
            int y = robot[num].y;
            int d = robot[num].dir;
            int nx = x, ny = y;

            for (int t = 0; t < times; t++) {
                nx += dx[d];
                ny += dy[d];

                if (0 > nx || nx >= b || 0 > ny || ny >= a) {
                    return "Robot " + num + " crashes into the wall\n";
                }
    
                if (board[nx][ny] != 0) {
                    return "Robot " + num + " crashes into robot " + board[nx][ny] + "\n";
                }
            }

            board[x][y] = 0;
            board[nx][ny] = num;
            robot[num].x = nx;
            robot[num].y = ny;
        }

        return null;
    }

    static int getDir(String dir) {
        if (dir.equals("S")) return 0;
        else if (dir.equals("E")) return 1;
        else if (dir.equals("N")) return 2;
        else return 3;
    }

    static class Node {
        int x;
        int y;
        int dir;

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
