package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1976 {
    static int N, M;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) parent[i] = i;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int now = Integer.parseInt(st.nextToken());
                if (now == 1) {
                    if (find(i) != find(j)) {
                        union(i, j);
                    }
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] input = new int[st.countTokens()];
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        boolean answer = true;
        for (int i = 0; i < input.length - 1; i++) {
            if (find(input[i]) != find(input[i + 1])) {
                answer = false;
                break;
            }
        }

        if (answer) System.out.println("YES");
        else System.out.println("NO");
    }

    static int find(int x) {
        if (x != parent[x]) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA > parentB) {
            parent[parentA] = parentB;
        }
        else {
            parent[parentB] = parentA;
        }
    }
}
