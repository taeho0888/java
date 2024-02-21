package 그래프;

import java.io.*;
import java.util.*;

public class boj_1516 {
    static int n;
    static int[] cost, answer;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        cost = new int[n+1];
        answer = new int[n+1];
        visited = new boolean[n+1];
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int node = Integer.parseInt(st.nextToken());

                if (node == -1) break;
                else graph.get(i).add(node);
            }
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            
            stack.push(i);
            while (!stack.isEmpty()) {
                int now = stack.pop();
                
                if (canBuild(now)) {
                    visited[now] = true;

                    int max = 0;
                    for (int next : graph.get(now)) {
                        max = Math.max(max, answer[next]);
                    }

                    answer[now] = cost[now] + max;
                } else {
                    stack.push(now);
                    for (int next : graph.get(now)) {
                        if (!visited[next]) stack.push(next);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(answer[i] + "\n");
        System.out.print(sb);
    }

    static boolean canBuild(int node) {
        for (int next : graph.get(node)) {
            if (!visited[next]) return false;
        }
        return true;
    }
}
