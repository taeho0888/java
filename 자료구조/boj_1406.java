package 자료구조;

import java.io.*;
import java.util.*;

public class boj_1406 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) left.push(str.charAt(i));

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if (command.equals("L")) {
                if (!left.isEmpty()) right.push(left.pop());
            } else if (command.equals("D")) {
                if (!right.isEmpty()) left.push(right.pop());
            } else if (command.equals("B")) {
                if (!left.isEmpty()) left.pop();
            } else {
                left.push(st.nextToken().charAt(0));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : left) sb.append(ch);
        sb.reverse();
        for (char ch : right) sb.append(ch);

        System.out.println(sb);
    }
}
