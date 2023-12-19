import java.io.*;
import java.util.*;

public class boj_28278 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> stack = new ArrayDeque<Integer>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                stack.push(Integer.parseInt(st.nextToken()));
            }
            else if (command == 2) {
                try {
                    bw.write(stack.pop() + "\n");
                } catch (Exception e) {
                    bw.write("-1\n");
                }
            }
            else if (command == 3) {
                bw.write(stack.size() + "\n");
            }
            else if (command == 4) {
                if (stack.isEmpty()) bw.write("1\n");
                else bw.write("0\n");
            }
            else {
                if (stack.isEmpty()) bw.write("-1\n");
                else bw.write(stack.peek() + "\n");
            }
        }

        br.close();
        bw.close();
    }
}
