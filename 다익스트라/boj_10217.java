package 다익스트라;

import java.io.*;
import java.util.*;

public class boj_10217 {
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            graph.clear();
            for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(u).add(new Node(v, c, d));
            }

            for (int i = 1; i <= N; i++) Collections.sort(graph.get(i));
            String answer = getMinTime(N, M);

            System.out.println(answer);
        }
    }

    static String getMinTime(int N, int maxCost) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0, 0));

        int[][] distances = new int[N+1][maxCost+1];
        for (int i = 0; i < N+1; i++) Arrays.fill(distances[i], INF);
        distances[1][0] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.node == N) {
                return String.format("%d", now.time);
            }

            if (now.time > distances[now.node][now.cost]) {
                continue;
            }

            for (Node next : graph.get(now.node)) {
                int nextNode = next.node;
                int nextCost = now.cost + next.cost;
                int nextTime = now.time + next.time;

                if (nextCost > maxCost) continue;
                if (distances[nextNode][nextCost] <= nextTime) continue;

                distances[nextNode][nextCost] = nextTime;
                queue.offer(new Node(nextNode, nextCost, nextTime));
            }
        }

        return "Poor KCM";
    }

    static class Node implements Comparable<Node> {
        int node;
        int cost;
        int time;

        Node(int node, int cost, int time) {
            this.node = node;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
