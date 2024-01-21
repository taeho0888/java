package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] NGE = new int[N + 1];
        Arrays.fill(NGE, -1);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        
        for (int i = 1; i <= N; i++) {
            while (!queue.isEmpty() && queue.peek().value < arr[i]) {
                Node cur = queue.poll();
                NGE[cur.index] = arr[i];
            }
            queue.offer(new Node(i, arr[i]));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(NGE[i] + " ");
        System.out.println(sb);
    }

    public static class Node implements Comparable<Node> {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
