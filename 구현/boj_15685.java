package 구현;

import java.io.*;
import java.util.*;
// 4시 13분
public class boj_15685 {
    static int N;
    static LinkedList<Node> visited = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int x, y, d, g;
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            addDragonCurve(x, y, d, g);
        }

    }

    static void addDragonCurve(int x, int y, int d, int g) {
        

    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
