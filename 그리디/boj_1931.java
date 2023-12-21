package 그리디;

import java.io.*;
import java.util.*;

public class boj_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] meet = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meet[i][0] = Integer.parseInt(st.nextToken());
            meet[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meet, Comparator.comparingInt((int[] meeting) -> meeting[0]));
        Arrays.sort(meet, Comparator.comparingInt((int[] meeting) -> meeting[1]));

        int curEndTime = meet[0][1];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (curEndTime <= meet[i][0]) {
                curEndTime = meet[i][1];
                count++;
            }
        }

        System.out.println(count);
    }
}
