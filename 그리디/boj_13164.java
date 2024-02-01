package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_13164 {
    static int N, K;
    static int[] arr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1 - 3 - 5 - 6 - 10
        //   2   2   1   4
        // 2, 4 빼면 9 - 4 - 2 = 3

        // 간격을 내림차순 우선순위 큐로 저장
        // arr[0] + arr[N-1] 에서 K - 1 번 만큼 큐에서 빼줌

        PriorityQueue<Integer> gaps = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < N - 1; i++) {
            gaps.offer(arr[i+1] - arr[i]);
        }

        int answer = arr[N - 1] - arr[0];
        for (int i = 0; i < K - 1; i++) {
            answer -= gaps.poll();
        }

        System.out.println(answer);
    }
}
