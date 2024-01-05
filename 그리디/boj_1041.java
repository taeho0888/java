package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1041 {
    static long N;
    static int[] dice;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        dice = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) dice[i] = Integer.parseInt(st.nextToken());

        long answer = 0;

        if (N > 1) {
            // 주사위 중 최솟값이 들어가는 횟수 = (N - 2)^2 * 5 + (N - 2) * 4
            int n1 = Arrays.stream(dice).min().getAsInt();
            answer += (5 * N * N - 16 * N + 12) * n1;

            // 주사위 중 2개의 최솟값이 들어가는 횟수 = (N - 2) * 8
            int[] n2Array = new int[] {
                dice[0] + dice[1],
                dice[0] + dice[2],
                dice[0] + dice[3],
                dice[0] + dice[4],
                dice[1] + dice[2],
                dice[1] + dice[3],
                dice[1] + dice[5],
                dice[2] + dice[4],
                dice[2] + dice[5],
                dice[3] + dice[4],
                dice[3] + dice[5],
                dice[4] + dice[5]
            };
            int n2 = Arrays.stream(n2Array).min().getAsInt();
            answer += (8 * N - 12) * n2;

            // 주사위 중 3개의 최솟값이 들어가는 횟수 = 4
            int[] n3Array = new int[] {
                dice[0] + dice[1] + dice[2],
                dice[0] + dice[1] + dice[3],
                dice[0] + dice[2] + dice[4],
                dice[0] + dice[3] + dice[4],
                dice[5] + dice[1] + dice[2],
                dice[5] + dice[1] + dice[3],
                dice[5] + dice[2] + dice[4],
                dice[5] + dice[3] + dice[4]
            };
            int n3 = Arrays.stream(n3Array).min().getAsInt();
            answer += 4 * n3;
        }
        else {
            answer = Arrays.stream(dice).sum() - Arrays.stream(dice).max().getAsInt();
        }
        System.out.println(answer);
    }
}
