import java.io.*;
import java.util.*;

public class boj_16235 {
    static final int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
    static final int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};
    static int N, M, K;
    static int[][] feed, additionalFeed;
    static PriorityQueue<Integer>[][] tree;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N, M, K 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // map 정보 입력 받기
        feed = new int[N][N];
        additionalFeed = new int[N][N];
        tree = new PriorityQueue[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                feed[i][j] = 5;
                additionalFeed[i][j] = Integer.parseInt(st.nextToken());
                tree[i][j] = new PriorityQueue<>();
            }
        }

        // x, y, z 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            tree[x-1][y-1].add(z);
        }

        // K년 동안 봄 여름 가을 겨울 돌리기
        for (int i = 0; i < K; i++) {
            spring_summer();
            fall();
            winter();
        }

        System.out.println(getAliveTrees());
    }

    static void spring_summer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tree[i][j].isEmpty()) continue;
                PriorityQueue<Integer> newTree = new PriorityQueue<>();

                // 현재 좌표의 나이 순으로 죽는 나무, 안죽는 나무 결정
                while (!tree[i][j].isEmpty()) {
                    int curTree = tree[i][j].poll();
                    if (feed[i][j] >= curTree) {
                        newTree.offer(curTree + 1);
                        feed[i][j] -= curTree;
                    }
                    else {
                        tree[i][j].offer(curTree);
                        break;
                    }
                }

                while (!tree[i][j].isEmpty()) {
                    feed[i][j] += tree[i][j].poll() / 2;
                }

                tree[i][j] = newTree;
            }
        }
    }

    static void fall() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int curTree : tree[x][y]) {
                    if (curTree % 5 != 0) continue;

                    for (int i = 0; i < 8; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }

                        tree[nx][ny].add(1);
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                feed[i][j] += additionalFeed[i][j];
            }
        }
    }

    static int getAliveTrees() {
        int aliveTrees = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                aliveTrees += tree[i][j].size();
            }
        }
        return aliveTrees;
    }
}
