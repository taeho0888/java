package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_19238 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static Node curLocation;
    static ArrayList<Node[]> client = new ArrayList<>();
    static boolean[] completed;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        curLocation = new Node(x, y, 0);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;

            Node[] curClient = new Node[] {new Node(sx, sy, 0), new Node(ex, ey, 0)};
            client.add(curClient);
        }

        completed = new boolean[M];
        for (int i = 0; i < M; i++) {
            if (fuel <= 0) break;
            fuel = taxi(fuel);
            if (fuel <= 0) break;
        }

        if (fuel < 0) System.out.println(-1);
        else System.out.println(fuel);
    }

    static int taxi(int fuel) {
        // 가장 가까운 승객 찾기
        int[] next = nextClient();
        int nextID = next[0];
        if (nextID == -1) return -1;
        int distFromNowToStart = next[1];

        Node[] nextClient = client.get(nextID);
        Node nextStart = nextClient[0];
        Node nextEnd = nextClient[1];

        int distFromStartToEnd = getDistance(nextStart, nextEnd, Integer.MAX_VALUE);

        if (distFromNowToStart + distFromStartToEnd > fuel) {
            fuel = -1;
        }
        else {
            fuel -= distFromNowToStart; // 현재 위치에서 승객 위치까지
            fuel += distFromStartToEnd; // 도착지까지 이동거리 충전
            curLocation = nextEnd; // 도착지로 현재 위치 갱신
        }

        return fuel;
    }
    
    static int[] nextClient() {
        int next = -1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            if (completed[i]) {
                continue;
            }

            Node[] cur = client.get(i);
            int curDist = getDistance(curLocation, cur[0], minDist);
            // System.out.printf("dist from (%d, %d) to (%d, %d) is %d\n", curLocation.x, curLocation.y, cur[0].x, cur[0].y, curDist);
            if (curDist < minDist) {
                minDist = curDist;
                next = i;
            } 
            else if (curDist == minDist && curDist != Integer.MAX_VALUE) {
                Node[] nextNode = client.get(next);

                if (cur[0].x < nextNode[0].x) {
                    next = i;
                } else if (cur[0].x == nextNode[0].x) {
                    if (cur[0].y < nextNode[0].y) {
                        next = i;
                    }
                }
            }
        }

        if (next != -1) completed[next] = true;
        return new int[] {next, minDist};
    }

    static int getDistance(Node start, Node end, int minDist) {
        boolean[][] visited = new boolean[N][N];
        visited[start.x][start.y] = true;

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == end.x && now.y == end.y) {
                return now.dist;
            }

            if (now.dist > minDist) {
                return Integer.MAX_VALUE;
            }

            for (int k = 0; k < 4; k++) {
                int nx = now.x + dx[k];
                int ny = now.y + dy[k];

                if (0 > nx || nx >= N || 0 > ny || ny >= N || visited[nx][ny] || map[nx][ny] == 1) {
                    continue;
                }

                queue.offer(new Node(nx, ny, now.dist + 1));
                visited[nx][ny] = true;
            }
        }
        return Integer.MAX_VALUE;
    }

    static class Node {
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
