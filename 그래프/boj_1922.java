package 그래프;
import java.io.*;
import java.util.*;

public class boj_1922 {
    static int N, M;
    static int[] parent;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[M][3];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));

        int answer = 0;
        for (int[] g : graph) {
            if (find(g[0]) != find(g[1])) {
                union(g[0], g[1]);
                answer += g[2];
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
        }
        else {
            parent[parentB] = parentA;
        }
    }
}
