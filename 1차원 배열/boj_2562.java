import java.io.*;

public class boj_2562 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int max = Integer.MIN_VALUE;
        int max_id = 0;

        for (int i = 0; i < 9; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if (tmp > max) {
                max_id = i + 1;
                max = tmp; 
            }
        }

        bw.write(Integer.toString(max) + "\n");
        bw.write(Integer.toString(max_id) + "\n");

        bw.close();
        br.close();
    }
}
