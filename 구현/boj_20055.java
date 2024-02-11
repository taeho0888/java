package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20055 {
    static int N, K;
    static int[][] duration;
    static int[][] robot;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        duration = new int[2][N];
        robot = new int[2][N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) duration[0][i] = Integer.parseInt(st.nextToken());
        for (int i = N-1; i >= 0; i--) duration[1][i] = Integer.parseInt(st.nextToken());
        
        int answer = 0;
        int index = 1;
        while (canProcess()) {
            step1();
            step2();
            step3(index++);

            answer++;
        }
        
        System.out.println(answer);
    }
    
    static void step1() {
        int tmpDuration = duration[0][0];
        int tmpRobot = robot[0][0];

        duration[0][0] = duration[1][0];
        robot[0][0] = robot[1][0];

        for (int i = 0; i < N-1; i++) {
            duration[1][i] = duration[1][i+1];
            robot[1][i] = robot[1][i+1];
        }

        duration[1][N-1] = duration[0][N-1];
        robot[1][N-1] = robot[0][N-1];

        for (int i = N-1; i > 0; i--) {
            duration[0][i] = duration[0][i-1];
            robot[0][i] = robot[0][i-1];
        }

        duration[0][1] = tmpDuration;
        robot[0][1] = tmpRobot;

        if (robot[0][N-1] != 0) robot[0][N-1] = 0;
    }

    static void step2() {
        int x = 0;
        int y = 0;
        int minRobot = Integer.MAX_VALUE;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                if (robot[i][j] < minRobot) {
                    x = i;
                    y = j;
                    minRobot = robot[i][j];
                }
            }
        }

        for (int i = 0; i < 2*N; i++) {
            if (robot[x][y] != 0) {
                int[] next = forward(x, y);

                if (duration[next[0]][next[1]] >= 1 && robot[next[0]][next[1]] == 0) {
                    robot[next[0]][next[1]] = robot[x][y];
                    robot[x][y] = 0;
                    duration[next[0]][next[1]]--;
                }
            }

            int[] back = backward(x, y);
            x = back[0];
            y = back[1];
        }

        if (robot[0][N-1] != 0) robot[0][N-1] = 0;
    }

    static void step3(int index) {
        if (robot[0][0] == 0 && duration[0][0] > 0) {
            robot[0][0] = index;
            duration[0][0]--;
        }
    }

    static int[] forward(int x, int y) {
        if (x == 0 && y == N-1) {
            return new int[] {1, N-1};
        } else if (x == 1 && y == 0) {
            return new int[] {0, 0};
        } else if (x == 0) {
            return new int[] {x, y+1};
        } else {
            return new int[] {x, y-1};
        }
    }

    static int[] backward(int x, int y) {
        if (x == 0 && y == 0) {
            return new int[] {1, 0};
        } else if (x == 1 && y == N-1) {
            return new int[] {0, N-1};
        } else if (x == 0) {
            return new int[] {x, y-1};
        } else {
            return new int[] {x, y+1};
        }
    }

    static boolean canProcess() {
        int count = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                if (duration[i][j] == 0) count++;
            }
        }
        
        if (count >= K) return false;
        else return true;
    }
}
