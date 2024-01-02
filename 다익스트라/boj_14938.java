package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_14938 {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] item;
    static int N, M, R;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /// N, M, R 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 아이템 갯수 정보 입력 받기
        item = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        // 그래프 선언
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Node>());
        }

        // 그래프 정보 입력 받기
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        // 예은이가 얻을 수 있는 최대 아이템 갯수로 정답 갱신
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, maxItem(i));
        }
        System.out.println(answer);
    }

    static int maxItem(int start) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (distances[now.node] < now.dist) {
                continue;
            }

            for (Node next : graph.get(now.node)) {
                int next_dist = now.dist + next.dist;

                if (distances[next.node] > next_dist) {
                    distances[next.node] = next_dist;
                    queue.offer(new Node(next.node, next_dist));
                }
            }
        }

        int items = 0;
        for (int i = 1; i < N + 1; i++) {
            if (distances[i] <= M) {
                items += item[i];
            }
        }
        
        return items;
    }

    static class Node implements Comparable<Node> {
        int node;
        int dist;

        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
