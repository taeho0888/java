package 브루트포스;
import java.io.*;

public class boj_2231 {
    static String N;
    static long NUM;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = br.readLine();
        NUM = Integer.parseInt(N);
        long answer = 0;

        for (long i = NUM; i > 0; i--) {
            if (isConstructor(i)) answer = i;
        }

        // 정답 출력
        bw.write(answer + "\n");
        br.close();
        bw.close();
    }

    static boolean isConstructor(long num) {
        long sum = num;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum == NUM;
    }
}
