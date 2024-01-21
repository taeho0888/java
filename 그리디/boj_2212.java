package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_2212 {
    static int N, K;
    static int[] sensor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        sensor = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensor);

        PriorityQueue<Integer> gaps = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < N - 1; i++) {
            gaps.offer(sensor[i+1] - sensor[i]);
        }
        
        int answer = sensor[N - 1] - sensor[0];
        for (int i = 0; i < K-1; i++) {
            if (!gaps.isEmpty()){
                answer -= gaps.poll();
            }
        }

        System.out.println(answer);
    }
}
