package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1477 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        ArrayList<Integer> rest = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        rest.add(0);
        rest.add(L);
        for (int i = 0; i < N; i++) {
            rest.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(rest);

        PriorityQueue<Gap> queue = new PriorityQueue<>();
        for (int i = 0; i <= N; i++) {
            int start = rest.get(i);
            int end = rest.get(i+1);
            queue.offer(new Gap(end - start, end - start, 1));
        }

        for (int i = 0; i < M; i++) {
            if (!queue.isEmpty()) {
                Gap cur = queue.poll();
                int ogGap = cur.ogGap;
                int newDivide = cur.divide + 1;
                int newGap = (ogGap % newDivide == 0) ? ogGap / newDivide: ogGap / newDivide + 1;

                queue.offer(new Gap(ogGap, newGap, newDivide));
            }
        }

        System.out.println(queue.peek().curGap);
    }

    static class Gap implements Comparable<Gap> {
        int divide;
        int curGap;
        int ogGap;

        public Gap(int ogGap, int curGap, int divide) {
            this.curGap = curGap;
            this.ogGap = ogGap;
            this.divide = divide;
        }

        @Override
        public int compareTo(Gap o) {
            return Integer.compare(o.curGap, this.curGap);
        }
    }
}
