package 기초;
import java.io.*;
import java.util.*;

public class boj_1546 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] score = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) score[i] = Integer.parseInt(st.nextToken());

        double m = Arrays.stream(score).max().orElse(0);
        double[] newScore = new double[n];
        for (int i = 0; i < n; i++) {
            newScore[i] = (double) ((score[i]/m)*100);
        }

        bw.write(String.format("%.5f\n", Arrays.stream(newScore).average().orElse(0)));

        br.close();
        bw.close();
    }
}
