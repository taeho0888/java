package 그래프;

import java.io.*;
import java.util.*;

public class boj_1068 {
    static int N;
    static Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static int toDelete;
    static int answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) graph.put(i, new ArrayList<>());

        int root = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) graph.get(parent).add(i);
            else root = i;
        }

        toDelete = Integer.parseInt(br.readLine());

        dfs(root);
        System.out.println(answer);
    }

    static void dfs(int node) {
        if (node == toDelete) {
            return;
        }

        if (graph.get(node).isEmpty()) {
            answer++;
            return;
        }

        if (graph.get(node).size() == 1 && graph.get(node).contains(toDelete)) {
            answer++;
            return;
        }

        for (int next : graph.get(node)) {
            if (next != toDelete) dfs(next);
        }
    }
}
