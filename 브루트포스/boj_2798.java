package 브루트포스;
import java.io.*;
import java.util.*;

public class boj_2798 {
    // static 변수 선언
    static int[] card;
    static int n, m;
    static int answer;

    public static void main(String args[]) throws IOException {
        // 입출력을 위한 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // n과 m 입력 받음
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 카드 정보 입력 받음
        card = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) card[i] = Integer.parseInt(st2.nextToken());

        // 브루트포스 알고리즘
        answer = Integer.MIN_VALUE;
        dfs(0, 0, 0);
        
        // 정답 출력
        bw.write(answer + "\n");
        br.close();
        bw.close();
    }

    static void dfs(int node, int depth, int sum) {
        if (depth == 3) {
            if (sum <= m) {
                answer = Math.max(answer, sum);
            }
            return;
        }
        for (int i = node; i < n; i++) {
            if (sum + card[i] > m) continue;
            dfs(i + 1, depth + 1, sum + card[i]);
        }
    }
}
