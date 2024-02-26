package 구현;

import java.io.*;
import java.util.*;

public class boj_18808 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static Sticker[] stickers;
    private static boolean sticked;
    private static int[][] board;
    private static int n, m, k;

    public static void main(String args[]) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        stickers = new Sticker[k];
        
        for (int s = 0; s < k; s++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            int[][] shape = new int[r][c];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    shape[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            stickers[s] = new Sticker(r, c, shape);
        }

        for (Sticker sticker : stickers) {
            // 붙음 붙이구 아님 말구~
            sticked = false;

            int r, c;
            for (int dir = 0; dir < 4; dir++) {
                if (dir % 2 == 0) {
                    r = sticker.r;
                    c = sticker.c;
                } else {
                    r = sticker.c;
                    c = sticker.r;
                }

                int[][] curSticker = sticker.getSticker(dir);
                int[] loc = canStick(curSticker, r, c);

                if (sticked) {
                    doStick(curSticker, r, c, loc[0], loc[1]);
                    break;
                }
            }
        }

        // count answer
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) answer++;
            }
        }

        System.out.println(answer);
    }

    private static int[] canStick(int[][] sticker, int r, int c) {
        for (int i = 0; i <= n-r; i++) {
            Loop: 
            for (int j = 0; j <= m-c; j++) {
                for (int x = 0; x < r; x++) {
                    for (int y = 0; y < c; y++) {
                        if (sticker[x][y] == 1 && board[x+i][y+j] == 1) {
                            continue Loop;
                        }
                    }
                }

                sticked = true;
                return new int[] {i, j};
            }
        }
        
        return new int[] {-1, -1};
    }

    private static void doStick(int[][] sticker, int r, int c, int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j] == 1) board[i+x][j+y] = 1;
            }
        }
    }

    public static class Sticker {
        int r;
        int c;
        int[][] sticker;

        Sticker(int r, int c, int[][] sticker) {
            this.r = r;
            this.c = c;
            this.sticker = sticker;
        }

        public int[][] getSticker(int dir) {
            if (dir == 0) {
                return this.sticker;
            } else if (dir == 1) {
                int[][] newSticker = new int[c][r];
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        newSticker[j][r-i-1] = sticker[i][j];
                    }
                }
                return newSticker;
            } else if (dir == 2) {
                int[][] newSticker = new int[r][c];
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        newSticker[r-i-1][c-j-1] = sticker[i][j];
                    }
                }
                return newSticker;
            } else {
                int[][] newSticker = new int[c][r];
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        newSticker[c-j-1][i] = sticker[i][j];
                    }
                }
                return newSticker;
            }
        }
    }
}
