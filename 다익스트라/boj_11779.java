package 다익스트라;

import java.io.*;
import java.util.*;

public class boj_11779 {
    static int n, m;
    static final long INF = Long.MAX_VALUE;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static boolean found = false;
    static int[] route;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        route = new int[n+1];

        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }
        for (int i = 1; i <= n; i++) Collections.sort(graph.get(i));

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        long distance = dijkstra(start, end);
        ArrayList<Integer> path = getPath(end);

        StringBuilder sb = new StringBuilder();
        sb.append(distance + "\n");
        sb.append(path.size() + "\n");
        for (int i = path.size() - 1; i >= 0; i--) {
            sb.append(path.get(i) + " ");
        }

        System.out.println(sb);
    }

    static long dijkstra(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));

        long[] distances = new long[n+1];
        Arrays.fill(distances, INF);
        distances[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.cost > distances[now.node]) continue;

            for (Node next : graph.get(now.node)) {
                long nextCost = now.cost + next.cost;

                if (distances[next.node] > nextCost) {
                    queue.offer(new Node(next.node, nextCost));
                    distances[next.node] = nextCost;
                    route[next.node] = now.node;
                }
            }
        }

        return distances[end];
    }

    static ArrayList<Integer> getPath(int cur) {
        ArrayList<Integer> path = new ArrayList<>();
        while (cur != 0) {
            path.add(cur);
            cur = route[cur];
        }

        return path;
    }

    static class Node implements Comparable<Node> {
        int node;
        long cost;

        Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
