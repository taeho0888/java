package 투포인터;

import java.io.*;
import java.util.*;

public class boj_2470 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        int left = 0;
        int right = N - 1;

        int ansLeft = 0;
        int ansRight = 0;
        int ansSum = Integer.MAX_VALUE;

        while (left < right) {
            int curSum = arr[left] + arr[right];

            if (Math.abs(curSum) < Math.abs(ansSum)) {
                ansSum = curSum;
                ansLeft = arr[left];
                ansRight = arr[right];
            }

            if (curSum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.printf("%d %d\n", ansLeft, ansRight);
    }
}
