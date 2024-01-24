package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_2887 {
    static int N;
    static Node[] planet;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 0;

        N = Integer.parseInt(br.readLine());
        planet = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            
            planet[i] = new Node(i, x, y, z);
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        Arrays.sort(planet, Comparator.comparingInt(arr -> arr.x));
        for (int i = 0; i < N - 1; i++) {
            int dist = Math.abs(planet[i].x - planet[i+1].x);
            edges.offer(new Edge(planet[i].n, planet[i+1].n, dist));
        }

        Arrays.sort(planet, Comparator.comparingInt(arr -> arr.y));
        for (int i = 0; i < N - 1; i++) {
            int dist = Math.abs(planet[i].y - planet[i+1].y);
            edges.offer(new Edge(planet[i].n, planet[i+1].n, dist));
        }

        Arrays.sort(planet, Comparator.comparingInt(arr -> arr.z));
        for (int i = 0; i < N - 1; i++) {
            int dist = Math.abs(planet[i].z - planet[i+1].z);
            edges.offer(new Edge(planet[i].n, planet[i+1].n, dist));
        }

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        int count = 0;
        while (count < N - 1) {
            Edge edge = edges.poll();
            if (find(edge.x) != find(edge.y)) {
                union(edge.x, edge.y);
                answer += edge.dist;
                count++;
            }
        }

        System.out.println(answer);
    }


    static int find(int x) {
        if (x != parent[x]) parent[x] = find(parent[x]);
        return parent[x];
    }


    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA > parentB) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
        }
    }


    static class Node {
        int n;
        int x;
        int y;
        int z;

        public Node(int n, int x, int y, int z) {
            this.n = n;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    
    static class Edge implements Comparable<Edge> {
        int x;
        int y;
        int dist;

        public Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
