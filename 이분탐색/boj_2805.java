package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2805 {
    static int[] woods;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        woods = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) woods[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = Arrays.stream(woods).max().getAsInt();

        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long curWood = calc(mid);

            if (curWood < M) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static long calc(int height) {
        long count = 0;

        for (int wood : woods) {
            if (wood > height) count += (wood - height);
        }

        return count;
    }
}
