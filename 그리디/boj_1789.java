package 그리디;

import java.io.*;

public class boj_1789 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        int i;
        long curSum = 0;

        for (i = 1; curSum < n; i++) {
            curSum += i;
        }

        if (curSum == n) System.out.println(i - 1);
        else System.out.println(i - 2);
    }
}
