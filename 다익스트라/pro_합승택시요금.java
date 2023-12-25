// https://school.programmers.co.kr/learn/courses/30/lessons/72413
package 다익스트라;

import java.util.*;

class Solution {
    static final int INF = 100000000;
    static int N;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    
    public int solution(int n, int s, int a, int b, int[][] fares) {        
        N = n;
        // A와 B가 동시에 가는 경로 + A 따로 가는 경로 + B 따로 가는 경로
        
        // graph 선언 & 구하기
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : fares) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        
        // s에서 모든 경로로 갈 수 있는 거리 배열 distFromSTo 구하기
        int[] distFromSTo = dijkstra(s);
            
        // distFromSTo를 순회하면서 a와 b까지 도착할 수 있는 답 후보를 구하면서 갱신
        int answer = INF;
        for (int curStart = 1; curStart < N + 1; curStart++) {
            int cur_answer = distFromSTo[curStart];
            int[] distFromCurTo = dijkstra(curStart);
            cur_answer += distFromCurTo[a] + distFromCurTo[b];
            answer = Math.min(answer, cur_answer);
        }
        
        return answer;
    }
    
    static int[] dijkstra(int start) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, INF);
        distances[start] = 0;
        
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            if (distances[cur.node] < cur.dist) {
                continue;
            }
            
            for (Node next : graph.get(cur.node)) {
                int new_dist = next.dist + cur.dist;
                
                if (new_dist < distances[next.node]) {
                    distances[next.node] = new_dist;
                    queue.offer(new Node(next.node, new_dist));
                }
            }
        }
        return distances;
    }
    
    static class Node implements Comparable<Node> {
        int node;
        int dist;
        
        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
