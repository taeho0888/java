package 그리디;

import java.io.*;
import java.util.*;

public class boj_1197 {
    static int V, E;
    static int[] parent;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st1.nextToken());
        E = Integer.parseInt(st1.nextToken());

        parent = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            parent[i] = i;
        }

        graph = new int[E][3];
        for (int i = 0; i < E; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int c = Integer.parseInt(st2.nextToken());

            graph[i] = new int[] {a, b, c};
        }

        Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));

        int answer = kruskal();
        System.out.println(answer);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA > parentB) {
            parent[parentA] = parentB;
        }
        else {
            parent[parentB] = parentA;
        }
    }

    static int find(int x) {
        if (x != parent[x]) {
            return find(parent[x]);
        }
        return parent[x];
    }

    static int kruskal() {
        int answer = 0;

        for (int[] edge : graph) {
            if (find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                answer += edge[2];
            }
        }

        return answer;
    }
}
