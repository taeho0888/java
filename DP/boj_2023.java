package DP;

import java.util.ArrayList;
import java.util.Scanner;

public class boj_2023 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        ArrayList<ArrayList<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) dp.add(new ArrayList<>());

        dp.get(1).add(2);
        dp.get(1).add(3);
        dp.get(1).add(5);
        dp.get(1).add(7);

        for (int i = 2; i <= 8; i++) {
            for (int num : dp.get(i - 1)) {
                for (int postfix : new int[] {1, 3, 5, 7, 9}) {
                    int candidate = num * 10 + postfix;

                    if (isPrime(candidate)) dp.get(i).add(candidate);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int num : dp.get(N)) answer.append(num + "\n");
        System.out.println(answer);
    }

    static boolean isPrime(int num) {
        if (num <= 1) return false;
        else if (num <= 3) return true;
        else if (num % 2 == 0 || num % 3 == 0) return false;

        int i = 5;
        while (i * i <= num) {
            if (num % i == 0 || num % (i+2) == 0) {
                return false;
            }
            i += 6;
        }

        return true;
    }
}
