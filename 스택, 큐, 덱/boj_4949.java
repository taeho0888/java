import java.io.*;
import java.util.*;

public class boj_4949 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayDeque<String> stack = new ArrayDeque<>();

        while (true) {
            boolean no = false;
            stack.clear();

            String line = br.readLine();
            if (line.equals(".")) break;

            for (char c : line.toCharArray()) {
                if (c == '(' || c == '[') {
                    stack.push(String.valueOf(c));
                }
                else if (c == ')' || c == ']') {
                    if (stack.isEmpty()) {
                        no = true;
                        break;
                    }
                    
                    String top = stack.pop();
                    if ((c == ')' && !top.equals("(")) || (c == ']' && !top.equals("["))) {
                        no = true;
                        break;
                    }
                }
            }
            if (no || !stack.isEmpty()) bw.write("no\n");
            else bw.write("yes\n");
        }

        br.close();
        bw.close();
    }
}
