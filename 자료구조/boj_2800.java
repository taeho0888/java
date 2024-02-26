package 자료구조;

import java.io.*;
import java.util.*;

public class boj_2800 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Set<String> stringSet = new TreeSet<>();
    private static int[][] bracket;
    private static String line;
    private static int n = 0;

    public static void main(String args[]) throws IOException {
        line = br.readLine();
        for (char ch : line.toCharArray()) {
            if (ch == '(') n++;
        }

        bracket = new int[n][2];
        int index = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                stack.push(i);
            } else if (line.charAt(i) == ')') {
                bracket[index][0] = stack.pop();
                bracket[index++][1] = i;
            }
        }

        dfs(new ArrayDeque<>(), new boolean[line.length()]);

        StringBuilder sb = new StringBuilder();
        for (String str : stringSet) sb.append(str + "\n");
        System.out.println(sb);
    }

    private static void dfs(Deque<Integer> stack, boolean[] checked) {
        if (!stack.isEmpty()) addString(checked);
        if (stack.size() == n) return;

        int start = (stack.isEmpty()) ? 0 : stack.peekLast() + 1;
        for (int i = start; i < n; i++) {
            stack.addLast(i);
            for (int num : bracket[i]) checked[num] = true;
            
            dfs(stack, checked);
            
            stack.removeLast();
            for (int num : bracket[i]) checked[num] = false;
        }
    }

    private static void addString(boolean[] checked) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            if (!checked[i]) sb.append(line.charAt(i));
        }

        stringSet.add(sb.toString());
    }
}
