package 그래프;

import java.io.*;
import java.util.*;

public class boj_1005 {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            int[] cost = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) cost[i] = Integer.parseInt(st.nextToken());
            
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(v).add(u);
            }
        
            int w = Integer.parseInt(br.readLine());
            answer.append(getMinTime(graph, cost, n, w) + "\n");
        }

        System.out.println(answer);
    }

    static int getMinTime(ArrayList<ArrayList<Integer>> graph, int[] cost, int n, int destination) {
        // destination을 짓기 위해 지어야하는 다른 건물을 모두 알아내야함

        int[] distances = new int[n+1];
        Arrays.fill(distances, INF);

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(destination);

        while (!stack.isEmpty()) {
            int now = stack.poll();
            
            if (graph.get(now).isEmpty()) {
                distances[now] = cost[now];
                continue;
            }

            if (canBuild(graph, distances, now)) {
                // max를 distance에 할당
                int curMax = 0;
                for (int next : graph.get(now)) {
                    curMax = Math.max(curMax, distances[next]);
                }
                distances[now] = curMax + cost[now];
            } else {
                stack.push(now);
                for (int next : graph.get(now)) stack.push(next);
            }
        }

        return distances[destination];
    }

    static boolean canBuild(ArrayList<ArrayList<Integer>> graph, int[] distances, int node) {
        for (int next : graph.get(node)) {
            if (distances[next] == INF) return false;
        }

        return true;
    }
}
