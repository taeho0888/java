package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1461 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> plusQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusQ = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp > 0) plusQ.offer(tmp);
            else minusQ.offer(-1 * tmp);
        }

        int answer = 0;
        boolean flag = true;

        if (!plusQ.isEmpty() && minusQ.isEmpty()) {
            while (!plusQ.isEmpty()) {
                int tmp = plusQ.peek();

                for (int i = 0; i < M; i++) {
                    plusQ.poll();
                    if (plusQ.isEmpty()) break;
                }

                if (flag) {
                    answer += tmp;
                    flag = false;
                }
                else {
                    answer += tmp * 2;
                }
            }
        } else if (plusQ.isEmpty() && !minusQ.isEmpty()) {
            while (!minusQ.isEmpty()) {
                int tmp = minusQ.peek();

                for (int i = 0; i < M; i++) {
                    minusQ.poll();
                    if (minusQ.isEmpty()) break;
                }

                if (flag) {
                    answer += tmp;
                    flag = false;
                }
                else {
                    answer += tmp * 2;
                }
            }
        } else if (plusQ.peek() < minusQ.peek()) {
            while (!minusQ.isEmpty()) {
                int tmp = minusQ.peek();

                for (int i = 0; i < M; i++) {
                    minusQ.poll();
                    if (minusQ.isEmpty()) break;
                }

                if (flag) {
                    answer += tmp;
                    flag = false;
                }
                else {
                    answer += tmp * 2;
                }
            }
            while (!plusQ.isEmpty()) {
                int tmp = plusQ.peek();

                for (int i = 0; i < M; i++) {
                    plusQ.poll();
                    if (plusQ.isEmpty()) break;
                }

                answer += tmp * 2;
            }
        } else {
            while (!plusQ.isEmpty()) {
                int tmp = plusQ.peek();

                for (int i = 0; i < M; i++) {
                    plusQ.poll();
                    if (plusQ.isEmpty()) break;
                }

                if (flag) {
                    answer += tmp;
                    flag = false;
                }
                else {
                    answer += tmp * 2;
                }
            }
            while (!minusQ.isEmpty()) {
                int tmp = minusQ.peek();

                for (int i = 0; i < M; i++) {
                    minusQ.poll();
                    if (minusQ.isEmpty()) break;
                }

                answer += tmp * 2;
            }
        }
        System.out.println(answer);
    }
}
