package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16139 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int[][] sum = new int[26][str.length()+1];

        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            int i = (int) alphabet - 'a';

            if (str.charAt(0) == alphabet) {
                sum[i][1] = 1;
            }

            for (int j = 2; j <= str.length(); j++) {
                if (str.charAt(j-1) == alphabet) {
                    sum[i][j] = sum[i][j-1] + 1;
                } else {
                    sum[i][j] = sum[i][j-1];
                }
            }
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = (int) st.nextToken().charAt(0) - 'a';
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            sb.append(String.format("%d\n", sum[s][r+1] - sum[s][l]));
        }
        System.out.println(sb);
    }

}
