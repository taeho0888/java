package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17779 {
    static int N;
    static int[][] map;
    static int minGap = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bruteforce();
        System.out.println(minGap);
    }

    static void bruteforce() {
        // set values (x, y, d1, d2)
        for (int d1 = 1; d1 < N; d1++) {
            for (int d2 = 1; d2 < N; d2++) {
                for (int x = 1; x <= N; x++) {
                    for (int y = 1; y <= N; y++) {
                        // 성립 안하는 조건
                        if (x - d1 < 1 || x + d2 > N || y + d1 + d2 > N) continue;
                        divideArea(x, y, d1, d2);
                    }
                }
            }
        }

    }

    static void divideArea(int x, int y, int d1, int d2) {
        int[][] area = new int[N+1][N+1];

        for (int i = 0; i <= d1; i++) {
            area[x-i][y+i] = 5;
            area[x+d2-i][y+d2+i] = 5;
        }

        for (int i = 1; i < d2; i++) {
            area[x+i][y+i] = 5;
            area[x-d1+i][y+d1+i] = 5;
        }

        for (int cx = x-d1+1; cx < x+d2; cx++) {
            boolean flag = false;
            for (int cy = 1; cy < N; cy++) {
                if (!flag) {
                    if (area[cx][cy] == 5) {
                        flag = true;
                    }
                } else {
                    if (area[cx][cy] == 5) {
                        break;
                    } else {
                        area[cx][cy] = 5;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (area[i][j] == 5) {
                    continue;
                } else if (i < x && j <= y + d1) {
                    area[i][j] = 1;
                } else if (i <= x - d1 + d2 && j > y + d1) {
                    area[i][j] = 2;
                } else if (i >= x && j < y + d2) {
                    area[i][j] = 3;
                } else if (x - d1 + d2 < i && y + d2 <= j) {
                    area[i][j] = 4;
                }
            }
        }

        int[] vote = new int[6];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                vote[area[i][j]] += map[i][j];
            }
        }

        Arrays.sort(vote);
        minGap = Math.min(minGap, vote[5] - vote[1]);
    }
}
