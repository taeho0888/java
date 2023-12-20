package BFS;

import java.io.*;
import java.util.*;

public class boj_1260 {
    static ArrayDeque<Integer> stack = new ArrayDeque<>();
    static ArrayDeque<Integer> queue = new ArrayDeque<>();
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int N, M, V;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M, V 입력 받음
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        V = Integer.parseInt(st1.nextToken());

        // graph 변수 선언
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 그래프 값 입력 받음
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            graph.get(s).add(e);
            graph.get(e).add(s);
        }
        
        // 그래프 오름차순으로 정렬
        for (ArrayList<Integer> list : graph) {
            Collections.sort(list);
        }
        
        // visited 초기화 후 dfs
        visited = new boolean[N + 1];
        dfs(V);

        // visited 초기화 후 bfs
        visited = new boolean[N + 1];
        bfs(V);

        br.close();
    }    

    static void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");

        for (int next : graph.get(start)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    static void bfs(int start) {
        queue.offer(start);
        visited[start] = true;
        System.out.print("\n" + start + " ");

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                    System.out.print(next + " ");
                }
            }
        }
        System.out.println();
    }
}
