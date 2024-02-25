package 그래프;

import java.io.*;
import java.util.*;

public class boj_1167 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Node>> adList = new HashMap<>();
    private static int v, node, answer = 0;
    private static StringTokenizer st;
    private static boolean[] visited;

    public static void main(String args[]) throws IOException {
        v = Integer.parseInt(br.readLine());

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());

            int now = Integer.parseInt(st.nextToken());
            while (true) {
                int node = Integer.parseInt(st.nextToken());
                if (node == -1) break;

                int dist = Integer.parseInt(st.nextToken());
                adList.computeIfAbsent(now, k -> new ArrayList<>()).add(new Node(node, dist));
            }
        }

        visited = new boolean[v+1];
        dfs(1, 0);

        visited = new boolean[v+1];
        dfs(node, 0);

        System.out.println(answer);
    }

    public static void dfs(int now, int dist) {
        if (dist > answer) {
            answer = dist;
            node = now;
        }
        visited[now] = true;
        
        for (Node next : adList.get(now)) {
            if (!visited[next.node]) {
                dfs(next.node, next.dist + dist);
            }
        }
    }

    public static class Node {
        int node;
        int dist;

        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
