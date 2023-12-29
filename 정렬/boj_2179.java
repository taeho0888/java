package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2179 {
    static int N;
    static String answer1, answer2;
    static int answerLength = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                compare(words[i], words[j]);
            }
        }
        System.out.println(answer1);
        System.out.println(answer2);
    }

    static void compare(String str1, String str2) {
        int curAnswer = 0;
        int curMinLength = Math.min(str1.length(), str2.length());

        for (int i = 0; i < curMinLength; i++) {
            if (str1.charAt(i) == str2.charAt(i)) curAnswer++;
            else break;
        }

        if (curAnswer > answerLength) {
            answer1 = str1;
            answer2 = str2;
            answerLength = curAnswer;
        }
    }
}
