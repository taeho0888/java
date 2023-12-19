import java.io.*;
import java.util.*;

public class boj_13458 {
    static int classNum, adminA, adminB;
    static int[] classes;
    static long answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        classNum = Integer.parseInt(br.readLine());

        classes = new int[classNum];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < classNum; i++) classes[i] = Integer.parseInt(st1.nextToken());

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        adminA = Integer.parseInt(st2.nextToken());
        adminB = Integer.parseInt(st2.nextToken());

        for (int i = 0; i < classNum; i++) {
            classes[i] -= adminA;
            if (classes[i] <= 0) continue;
            answer += classes[i] / adminB;
            if (classes[i] % adminB != 0) {
                answer++;
            }
        }

        bw.write((answer + classNum) + "\n");
        bw.close();
    }
}
