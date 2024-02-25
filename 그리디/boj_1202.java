package 그리디;

import java.io.*;
import java.util.*;

public class boj_1202 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] gems = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            gems[i] = new int[] {m, v};
        }
        Arrays.sort(gems, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o2[1], o1[1]);
            }
        });

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) bags[i] = Integer.parseInt(br.readLine());
        Arrays.sort(bags);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        long answer = 0;
        for (int gemId = 0, bagId = 0; bagId < k; bagId++) {
            while (gemId < n && gems[gemId][0] <= bags[bagId]) {
                queue.offer(gems[gemId++][1]);
            }

            if (!queue.isEmpty()) {
                answer += queue.poll();
            }
        }

        System.out.println(answer);
    }
}
