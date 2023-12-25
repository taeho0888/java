package 그리디;

import java.io.*;
import java.util.*;

public class boj_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        long[][] classes = new long[N][2];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken());
            long t = Long.parseLong(st.nextToken());
            
            classes[i] = new long[] {s, t};
        }
        Arrays.sort(classes, Comparator.comparingLong(o -> o[0]));
        
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(classes[0][1]);

        for (int i = 1; i < N; i++) {
            if (queue.peek() > classes[i][0]) {
                queue.offer(classes[i][1]);
            }
            else {
                queue.poll();
                queue.offer(classes[i][1]);
            }
        }
        System.out.println(queue.size());
    }
}
