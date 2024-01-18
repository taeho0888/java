package 그리디;

import java.util.Arrays;
import java.util.Scanner;

public class boj_24337 {
    static int N, a, b;
    static int[] answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        sc.close();

        if (a + b > N + 1) {
            System.out.println(-1);
            return;
        }

        answer = new int[N];
        Arrays.fill(answer, 1);
        solution();
        
        StringBuilder sb = new StringBuilder();
        for (int num : answer) sb.append(num + " ");
        System.out.println(sb);
    }

    static void solution() {
        // a가 크면 왼쪽 부터 b가 크면 오른쪽 부터 채워넣음
        if (a >= b) {
            if (b == 1) {
                int index = N - 1;
                for (int i = a; i > 1; i--) {
                    answer[index--] = i;
                }
            } else {
                int index = N - 1;
                for (int i = 1; i < b; i++) {
                    answer[index--] = i;
                }
                for (int i = a; i > 1; i--) {
                    answer[index--] = i;
                }
            }
        } else {
            if (a == 1) {
                int index = N - 1;
                for (int i = 1; i < b; i++) {
                    answer[index--] = i;
                }
                answer[0] = b;
            }
            else {
                int index = N - 1;
                for (int i = 1; i <= b; i++) {
                    answer[index--] = i;
                }

                for (int i = a - 1; i > 1; i--) {
                    answer[index--] = i;
                }
            }
        }
    }
}
