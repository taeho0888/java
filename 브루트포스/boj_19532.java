package 브루트포스;
import java.io.*;
import java.util.*;

public class boj_19532 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        // ax + by = c
        // dx + ey = f

        // aex + bey = ce
        // bdx + bey = bf
        // x = (ce - bf) / (ae - bd)
        // adx + bdy = cd
        // adx + aey = af
        // y = (cd - af) / (bd - ae)

        int x = (int)((c * e - b * f) / (a * e - b * d));
        int y = (int)((c * d - a * f) / (b * d - a * e));

        bw.write(x + " " + y + "\n");
        br.close();
        bw.close();
    }
}
