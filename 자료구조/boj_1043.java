package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1043 {
    static int N, M;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int truth = 0;
        for (int i = 0; i < n; i++) {
            if (truth == 0) truth = Integer.parseInt(st.nextToken());
            else union(truth, Integer.parseInt(st.nextToken()));
        }
        
        Queue<int[]> queue = new ArrayDeque<>();

        // union 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            int[] arr = new int[num];
            for (int j = 0; j < num; j++) arr[j] = Integer.parseInt(st.nextToken());

            queue.offer(arr);

            for (int j = 1; j < num; j++) {
                union(arr[0], arr[j]);
            }
        }
        
        // 정답 카운트
        int answer = 0;
        while (!queue.isEmpty()) {
            int[] line = queue.poll();
            boolean flag = false;
            
            for (int person : line) {
                if (find(truth) == find(person)) {
                    flag = true;
                }
            }

            if (!flag) answer++;
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (x != parent[x]) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA > parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }
}
