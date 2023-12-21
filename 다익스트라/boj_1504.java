package 다익스트라;

import java.io.*;
import java.util.*;

public class boj_1504 {
    static final int INF = 10000000;
    static int N, E;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        E = Integer.parseInt(st1.nextToken());

        // 그래프 선언
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 입력 받음
        for (int i = 0; i < E; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());
            int w = Integer.parseInt(st2.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        // 반드시 거쳐야 하는 두 정점 입력 받음
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st3.nextToken());
        int v2 = Integer.parseInt(st3.nextToken());

        // 해당 정점으로부터 최소 거리 저장
        int[] distFrom1To = dijkstra(1);
        int[] distFromV1To = dijkstra(v1);
        int[] distFromV2To = dijkstra(v2);

        // 답이 될 수 있는 두 가지 경우의 수
        int way1 = distFrom1To[v1] + distFromV1To[v2] + distFromV2To[N];
        int way2 = distFrom1To[v2] + distFromV2To[v1] + distFromV1To[N];
        int answer = Math.min(way1, way2);

        // 출력
        if (answer >= INF) System.out.println(-1);
        else System.out.println(answer);
    }  
    
    static int[] dijkstra(int start) {
        // 거리 저장하는 배열 선언 후 INF로 초기화
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        // 우선순위 큐 선언
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));

        // 최소 거리 찾기
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (distance[cur.node] < cur.dist) {
                continue;
            }

            for (Node next : graph.get(cur.node)) {
                int new_dist = next.dist + cur.dist;

                if (new_dist < distance[next.node]) {
                    distance[next.node] = new_dist;
                    queue.offer(new Node(next.node, new_dist));
                }
            }
        }
        return distance;
    }

    static class Node implements Comparable<Node> {
        int node;
        int dist;

        public Node (int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        // 거리 중심으로 비교할 수 있도록 메소드 오버라이드
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
