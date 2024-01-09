import java.io.*;
import java.util.*;

public class boj_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int K = Integer.parseInt(st1.nextToken());
        char[] num = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            while (K > 0 && !stack.isEmpty() && stack.peek() < num[i]) {
                stack.pop();
                K--;
            }
            stack.push(num[i]);
        }

        while (K > 0) {
            stack.pop();
            K--;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);
        System.out.println(sb);
    }
}
