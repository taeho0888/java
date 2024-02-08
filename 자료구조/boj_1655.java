package 자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class boj_1655 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();
        int num = Integer.parseInt(br.readLine());
        left.offer(num);
        bw.write(num + "\n");

        for (int i = 0; i < N - 1; i++) {
            int cur = Integer.parseInt(br.readLine());

            if (cur > left.peek()) {
                if (left.size() > right.size()) {
                    right.offer(cur);
                } else if (left.size() == right.size()) {
                    if (cur <= right.peek()) {
                        left.offer(cur);
                    } else {
                        left.offer(right.poll());
                        right.offer(cur);
                    }
                }
            } else if (cur < left.peek()) {
                if (left.size() > right.size()) {
                    right.offer(left.poll());
                    left.offer(cur);
                } else if (left.size() == right.size()) {
                    left.offer(cur);
                }
            } else {
                if (left.size() > right.size()) {
                    right.offer(cur);
                } else if (left.size() == right.size()) {
                    left.offer(cur);
                }
            }

            bw.write(left.peek() + "\n");
        }
        
        bw.close();
    }
}
