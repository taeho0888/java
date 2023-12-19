package 브루트포스;
import java.io.*;

public class boj_1436 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int count = 1, answer = 666;

        while (count < N) {
            answer++;
            if (Integer.toString(answer).contains("666")) count++;
        }

        bw.write(answer + "\n");
        br.close();
        bw.close();
    } 
}
