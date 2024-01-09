import java.io.*;
import java.util.*;

public class boj_1717 {
    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st1.nextToken());
        m = Integer.parseInt(st1.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) parent[i] = i;

        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int c = Integer.parseInt(st2.nextToken());

            if (a == 0) { // 합집합
                union(b, c);
            }
            else { // 확인
                if (find(b) == find(c)) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }
        bw.close();
    }    

    static int find(int x) {
        if (x != parent[x]) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA > parentB) parent[parentA] = parentB;
        else parent[parentB] = parentA;
    }
}
