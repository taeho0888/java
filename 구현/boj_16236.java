package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_16236 {
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    static Node babyShark;
    static int[][] map;
    static int N, M;
    static int curEatCount = 0;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    babyShark = new Node(i, j, 2);
                    map[i][j] = 0;
                }
            }
        }

        play();
        System.out.println(time);
    }


    static void play() {
        while (true) {
            Node nextFish = getEatableFish();
            int cx = nextFish.x;
            int cy = nextFish.y;
            int dist = nextFish.distance;

            if (cx == -1) return;

            babyShark.x = cx;
            babyShark.y = cy;
            map[cx][cy] = 0;
            time += dist;
            curEatCount++;

            if (curEatCount == babyShark.size) {
                babyShark.size++;
                curEatCount = 0;
            }
        }
    }


    static Node getEatableFish() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(babyShark);

        boolean[][] visited = new boolean[N][N];
        visited[babyShark.x][babyShark.y] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.size != 0 && now.size < babyShark.size) {
                return now;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isOkay(nx, ny) && !visited[nx][ny]) {
                    queue.offer(new Node(nx, ny, map[nx][ny], now.distance + 1));
                    visited[nx][ny] = true;
                }
            }
        }

        return new Node(-1, -1);
    }


    static boolean isOkay(int x, int y) {
        return (x >=0 && x < N && y >= 0 && y < N && map[x][y] <= babyShark.size);
    }


    static class Node implements Comparable<Node> {
        int x;
        int y;
        int size;
        int distance;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public Node(int x, int y, int size, int distance) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node now) {
            int compareResult = Integer.compare(this.distance, now.distance);

            if (compareResult == 0) {
                compareResult = Integer.compare(this.x, now.x);
            }

            if (compareResult == 0) {
                compareResult = Integer.compare(this.y, now.y);
            }

            return compareResult;
        }
    }
}
