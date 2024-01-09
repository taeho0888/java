import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class boj_11279 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 최대 힙
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());

            if (command == 0) {
                if (queue.size() > 0) bw.write(queue.poll() + "\n");
                else bw.write("0\n");
            } else {
                queue.offer(command);
            }
        }

        bw.close();
    }
}
