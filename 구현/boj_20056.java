package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_20056 {
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M, K;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<List<ArrayList<Fireball>>> board = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<ArrayList<Fireball>> row = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                row.add(new ArrayList<>());
            }
            board.add(row);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            board.get(x-1).get(y-1).add(new Fireball(m, s, d));
        }

        for (int i = 0; i < K; i++) {
            board = moveFireballs(board);
        }

        System.out.println(getRemainedFireballs(board));
    }

    static List<List<ArrayList<Fireball>>> moveFireballs(List<List<ArrayList<Fireball>>> board) {
        List<List<ArrayList<Fireball>>> newBoard = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<ArrayList<Fireball>> row = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                row.add(new ArrayList<>());
            }
            newBoard.add(row);
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (board.get(x).get(y).size() == 0) {
                    continue;
                }

                for (Fireball fb : board.get(x).get(y)) {
                    int nx = (x + dx[fb.d]*fb.s) % N;
                    int ny = (y + dy[fb.d]*fb.s) % N;
                    while (nx < 0) nx += N;
                    while (ny < 0) ny += N;
                    newBoard.get(nx).get(ny).add(new Fireball(fb.m, fb.s, fb.d));
                }
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (newBoard.get(x).get(y).size() < 2) {
                    continue;
                }

                int mSum = 0, sSum = 0;
                boolean allEven = true, allOdd = true;
                for (Fireball fb : newBoard.get(x).get(y)) {
                    mSum += fb.m;
                    sSum += fb.s;

                    if (allEven && fb.d % 2 != 0) {
                        allEven = false;
                    }

                    if (allOdd && fb.d % 2 != 1) {
                        allOdd = false;
                    }
                }

                int newM = mSum / 5;
                int newS = sSum / newBoard.get(x).get(y).size();
                newBoard.get(x).get(y).clear();

                if (newM == 0) {
                    continue;
                }

                if (allOdd || allEven) {
                    for (int newD = 0; newD < 8; newD += 2) {
                        newBoard.get(x).get(y).add(new Fireball(newM, newS, newD));
                    }
                } else {
                    for (int newD = 1; newD < 8; newD += 2) {
                        newBoard.get(x).get(y).add(new Fireball(newM, newS, newD));
                    }
                }
            }
        }

        return newBoard;
    }

    static int getRemainedFireballs(List<List<ArrayList<Fireball>>> board) {
        int count = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (board.get(x).get(y).size() == 0) {
                    continue;
                }

                for (Fireball fb : board.get(x).get(y)) {
                    count += fb.m;
                }
            }
        }

        return count;
    }

    static class Fireball {
        int m; // 질랑
        int d; // 방향
        int s; // 속력

        public Fireball(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
