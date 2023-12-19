import java.io.*;
import java.util.*;

public class boj_14502 {
    static final int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int N, M;
    static int answer = Integer.MIN_VALUE;
    static int[][] originalLab;
    static ArrayList<int[]> virus;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        originalLab = new int[N][M];
        virus = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originalLab[i][j] = Integer.parseInt(st2.nextToken());
                if (originalLab[i][j] == 2) virus.add(new int[] {i, j});
            }
        }
        
        setWall(0);

        bw.write(answer + "\n");
        br.close();
        bw.close();
    }   
    
    static void setWall(int depth) {
        if (depth == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (originalLab[i][j] == 0) {
                    originalLab[i][j] = 1;
                    setWall(depth + 1);
                    originalLab[i][j] = 0;
                }
            }
        }
    }

    static void spreadVirus() {
        // lab copy
        int[][] copyLab = new int[N][M];
        for (int i = 0; i < N; i++) {
            copyLab[i] = originalLab[i].clone();
        }

        // bfs 위한 큐 선언
        Queue<int[]> q = new LinkedList<>();
        q.addAll(virus);

        // bfs 구현
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (copyLab[nx][ny] == 0) {
                        copyLab[nx][ny] = 2;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        
        // 안전 구역 카운트
        countSafe(copyLab);
    }

    static void countSafe(int[][] copyLab) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyLab[i][j] == 0) cnt++;
            }
        }

        if (cnt > answer) answer = cnt;
    }
}
