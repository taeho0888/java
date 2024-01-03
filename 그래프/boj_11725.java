package 그래프;
import java.io.*;
import java.util.*;

public class boj_11725 {
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] parent = findParents();
        for (int i = 2; i < N + 1; i++) {
            bw.write(parent[i] + "\n");
        }
        bw.close();
    }

    static int[] findParents() {
        boolean[] visited = new boolean[N + 1];
        int[] parent = new int[N + 1];

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        visited[1] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();

            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    parent[next] = node;
                    stack.push(next);
                    visited[next] = true;
                }
            }
        }
        return parent;
    }    
}
