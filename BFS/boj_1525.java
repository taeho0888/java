package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1525 {
    static int[][] move = new int[][] {
        {1, 3}, {0, 2, 4}, {1, 5}, {0, 4, 6}, {1, 3, 5, 7},
        {2, 4, 8}, {3, 7}, {4, 6, 8}, {5, 7}
    };
    // 0 1 2
    // 3 4 5
    // 6 7 8

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
            }
        }

        int answer = bfs(sb.toString());
        System.out.println(answer);
    }

    static int bfs(String start) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);

        Map<String, Integer> visited = new HashMap<>();
        visited.put(start, 0);

        while (!queue.isEmpty()) {
            String now = queue.poll();
            int nowMove = visited.get(now);
            int zero = now.indexOf('0');

            if (now.equals("123456780")) {
                return nowMove;
            }

            for (int index : move[zero]) {
                char nextChar = now.charAt(index);

                String next = now.replace(nextChar, 't');
                next = next.replace('0', nextChar);
                next = next.replace('t', '0');

                if (!visited.containsKey(next)) {
                    queue.offer(next);
                    visited.put(next, nowMove + 1);
                }
            }
        }

        return -1;
    }
}
