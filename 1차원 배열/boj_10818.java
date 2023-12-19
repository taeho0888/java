import java.io.*;
import java.util.*;

public class boj_10818 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Integer.parseInt(br.readLine());
        long max = Integer.MIN_VALUE;
        long min = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp > max) max = tmp;
            if (tmp < min) min = tmp;
        }

        bw.write(String.format("%d %d", min, max));
        bw.close();
        br.close();
    }
}
