package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_12100 {
    static int maxBlock = 0;
    static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, board);
        System.out.println(maxBlock);
    }

    static void dfs(int depth, int[][] board) {
        if (depth == 5) {
            maxBlock = Math.max(maxBlock, findMaxBlock(board));
            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            int[][] newBoard = new int[N][N];
            for (int i = 0; i < N; i++) newBoard[i] = board[i].clone();
            dfs(depth + 1, moveBoard(newBoard, direction));
        }
    }

    static int[][] moveBoard(int[][] board, int direction) {
        switch (direction) {
            case 0: return moveUp(board);
            case 1: return moveDown(board);
            case 2: return moveLeft(board);
            case 3: return moveRight(board);
            default: return board;
        }
    }

    static int[][] moveLeft(int[][] board) {
        for (int i = 0; i < N; i++) {
            int[] line = board[i];

            ArrayList<Integer> queue = moveLine(line);

            Arrays.fill(board[i], 0);
            for (int j = 0; j < queue.size(); j++) {
                board[i][j] = queue.get(j);
            }
        }
        return board;
    }

    static int[][] moveDown(int[][] board) {
        for (int i = 0; i < N; i++) {
            // 현재 라인 생성
            int[] line = new int[N];
            for (int j = 0; j < N; j++) {
                line[j] = board[N - 1 - j][i];
            }

            ArrayList<Integer> queue = moveLine(line);

            // 라인 채워넣기
            for (int j = 0; j < N; j++) board[j][i] = 0;
            for (int j = 0; j < queue.size(); j++) {
                board[N - 1 - j][i] = queue.get(j);
            }
        }
        return board;
    }

    static int[][] moveUp(int[][] board) {
        for (int i = 0; i < N; i++) {
            int[] line = new int[N];
            for (int j = 0; j < N; j++) line[j] = board[j][i];

            ArrayList<Integer> queue = moveLine(line);

            for (int j = 0; j < N; j++) board[j][i] = 0;
            for (int j = 0; j < queue.size(); j++) {
                board[j][i] = queue.get(j);
            }
        }
        return board;
    }

    static int[][] moveRight(int[][] board) {
        for (int i = 0; i < N; i++) {
            int[] line = board[i];

            ArrayList<Integer> queue = moveLine(line);

            Arrays.fill(board[i], 0);
            for (int j = 0; j < queue.size(); j++) {
                board[i][N - 1 - j] = queue.get(j);
            }
        }
        return board;
    }

    static ArrayList<Integer> moveLine(int[] line) {
        ArrayList<Integer> queue = new ArrayList<>();

        int ready = 0;
        for (int next : line) {
            if (next == 0) continue;

            // 줄 단위로 분석해서 비교 변수에 할당 (기본값 0)
            if (ready == 0) {
                ready = next;
            } 
            // 비교변수가 0이 아니라면 다음 들어갈 것과 비교
            else {
                // 같다면 x2 해서 최종 스택에 넣음
                if (ready == next) {
                    queue.add(ready*2);
                    ready = 0;
                }
                // 다르다면 ready 큐에 넣고 next를 ready에 할당
                else {
                    queue.add(ready);
                    ready = next;
                }
            }
        }
        if (ready != 0) queue.add(ready);

        return queue;
    }


    static int findMaxBlock(int[][] board) {
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxNum = Math.max(maxNum, board[i][j]);
            }
        }
        return maxNum;
    }
}
