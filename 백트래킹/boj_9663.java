package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9663 {
    static int N;
    static int answer;
    static int[] queen;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        queen = new int[N];

        nQueen(0);
        System.out.println(answer);
    }

    static void nQueen(int x) {
        if (x == N) {
            answer++;
            return;
        }

        for (int y = 0; y < N; y++) {
            if (possible(x, y)) {
                queen[x] = y;
                nQueen(x + 1);
            }
        }
    }

    static boolean possible(int x, int y) {
        for (int i = 0; i < x; i++) {
            if (queen[i] == y || Math.abs(x - i) == Math.abs(y - queen[i])) {
                return false;
            }
        }
        return true;
    }
}
