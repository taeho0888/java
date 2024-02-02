package 누적합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_13422 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] house = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                house[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(stealCount(N, M, K, house) + "\n");
        }
        bw.close();
    }

    public static int stealCount(int N, int M, int K, int[] house) {
        int count = 0;

        // 시작점이 index 0 인 경우
        int start = 0;
        int end = M;
        int curStealMoney = Arrays.stream(house, start, end).sum();
        if (curStealMoney < K) count++;
        if (N == M && count > 0) return 1;

        for (int i = 0; i < N - 1; i++) {
            curStealMoney = curStealMoney - house[start % N] + house[end % N];
            if (curStealMoney < K) count++;

            start++;
            end++;
        }

        return count;
    }
}
