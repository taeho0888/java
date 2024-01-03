package 브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_1107 {
    static int N, M;
    static Set<Integer> button;
    static int answer;
    static int maxDepth;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        button = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                button.remove(num);
            }   
        }

        answer = Math.abs(N - 100);
        maxDepth = Integer.toString(N).length() + 1;
        dfs("");
        System.out.println(answer);
    }
    
    static void dfs(String str) {
        if (str.length() > 0) {
            int curNum = Math.abs(Integer.parseInt(str) - N) + str.length();
            answer = Math.min(answer, curNum);
        }

        if (str.length() == maxDepth) {
            return;
        }

        for (int num : button) {
            dfs(str + Integer.toString(num));
        }
    }
}
