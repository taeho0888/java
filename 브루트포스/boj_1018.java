package 브루트포스;
import java.io.*;
import java.util.*;

public class boj_1018 {
    static int N, M, answer;
    static char[][] board;

    public static void main(String args[]) throws IOException {
        // 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // N, M 입력
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        // board 입력
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 다 해보기
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                answer = Math.min(answer, colorCount(i, j));
                if (answer == 0) break;
            }
        }

        // 정답 출력
        bw.write(answer + "\n");
        br.close();
        bw.close();
    }

    static int colorCount(int iStart, int jStart) {
        char[] type1 = {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'};
        char[] type2 = {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'};

        int tmpCount1 = 0;
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (board[iStart + i][jStart + j] != type1[j]) tmpCount1++;
                }
            }
            else {
                for (int j = 0; j < 8; j++) {
                    if (board[iStart + i][jStart + j] != type2[j]) tmpCount1++;
                }
            }
        }

        int tmpCount2 = 0;
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (board[iStart + i][jStart + j] != type2[j]) tmpCount2++;
                }
            }
            else {
                for (int j = 0; j < 8; j++) {
                    if (board[iStart + i][jStart + j] != type1[j]) tmpCount2++;
                }
            }
        }

        return Math.min(tmpCount1, tmpCount2);
    }
}
