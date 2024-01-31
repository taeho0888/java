package 자료구조;

import java.io.*;
import java.util.*;

public class boj_4195 {
    static int[] parent;
    static int[] size;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());

            Map<String, Integer> index = new HashMap<>();
            int curIndex = 1;
            
            parent = new int[n * 2 + 1];
            for (int i = 1; i <= n*2; i++) parent[i] = i;

            size = new int[n * 2 + 1];
            Arrays.fill(size, 1);

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                
                String p1 = st.nextToken();
                if (!index.containsKey(p1)) {
                    index.put(p1, curIndex++);
                }
                
                String p2 = st.nextToken();
                if (!index.containsKey(p2)) {
                    index.put(p2, curIndex++);
                }

                union(index.get(p1), index.get(p2));

                bw.write(size[find(index.get(p1))] + "\n");
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

        if (parentA != parentB) {
            if (parentA > parentB) {
                parent[parentA] = parentB;
                size[parentB] += size[parentA];
            } else {
                parent[parentB] = parentA;
                size[parentA] += size[parentB];
            }
        }
    }
}
