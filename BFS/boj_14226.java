package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class boj_14226 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int targetNum = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[1002][1002];
        visited[1][0] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {1, 0, 0});

        int answer = -1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int curNum = cur[0];
            int curClipboard = cur[1];
            int curTime = cur[2];

            if (curNum == targetNum) {
                answer = curTime;
                break;
            }

            // 클립보드 복사
            if (!visited[curNum][curNum]) {
                queue.offer(new int[] {curNum, curNum, curTime + 1});
                visited[curNum][curNum] = true;
            }

            // 클립보드 붙여넣기
            if (curNum + curClipboard < 1002 && !visited[curNum + curClipboard][curClipboard]) {
                queue.offer(new int[] {curNum + curClipboard, curClipboard, curTime + 1});
                visited[curNum + curClipboard][curClipboard] = true;
            }

            // 하나 삭제
            if (curNum - 1 >= 0 && !visited[curNum - 1][curClipboard]) {
                queue.offer(new int[] {curNum - 1, curClipboard, curTime + 1});
                visited[curNum - 1][curClipboard] = true;
            }
        }
     
        System.out.println(answer);
    }
}
