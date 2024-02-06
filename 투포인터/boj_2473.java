package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2473 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int ansLeft = 0;
        int ansMid = 0;
        int ansRight = 0;
        long ansSum = Long.MAX_VALUE;

        for (int left = 0; left < N - 2; left++) {
            int mid = left + 1;
            int right = N - 1;

            while (mid < right) {
                long curSum = (long) arr[left] + arr[mid] + arr[right];

                if (Math.abs(curSum) < Math.abs(ansSum)) {
                    ansSum = curSum;
                    ansLeft = arr[left];
                    ansMid = arr[mid];
                    ansRight = arr[right];
                }

                if (curSum < 0) {
                    mid++;
                } else {
                    right--;
                }
            }
        }

        System.out.printf("%d %d %d\n", ansLeft, ansMid, ansRight);
    }
}
