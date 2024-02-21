package 다익스트라;

import java.io.*;
import java.util.*;

public class boj_1162 {
    static final long INF = Long.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w, 0));
            graph.get(v).add(new Node(u, w, 0));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0, 0));

        long[][] distances = new long[n+1][k+1];
        for (int i = 0; i <= n; i++) Arrays.fill(distances[i], INF);
        distances[1][0] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.cost > distances[now.node][now.count]) continue;

            for (Node next : graph.get(now.node)) {
                long nextCost = next.cost + now.cost;

                // 다음 도로를 포장하지 않는 경우
                if (nextCost < distances[next.node][now.count]) {
                    distances[next.node][now.count] = nextCost;
                    queue.offer(new Node(next.node, nextCost, now.count));
                }

                // 다음 도로를 포장하는 경우
                if (now.count < k && now.cost < distances[next.node][now.count + 1]) {
                    distances[next.node][now.count + 1] = now.cost;
                    queue.offer(new Node(next.node, now.cost, now.count + 1));
                }
            }
        }

        System.out.println(Arrays.stream(distances[n]).min().getAsLong());
    }

    static class Node implements Comparable<Node> {
        int node;
        long cost;
        int count;

        Node(int node, long cost, int count) {
            this.node = node;
            this.cost = cost;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
