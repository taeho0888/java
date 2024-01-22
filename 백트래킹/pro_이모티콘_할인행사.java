// https://school.programmers.co.kr/learn/courses/30/lessons/150368
package 백트래킹;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class pro_이모티콘_할인행사 {
    public static void main(String args[]) {
        int[][] users = new int[][] {
            {40, 2900},
            {23, 10000},
            {11, 5200},
            {5, 5900},
            {40, 3100},
            {27, 9200},
            {32, 6900}
        };
        int[] emoticons = new int[] {
            1300, 1500, 1600, 4900
        };

        int[] answer = solution(users, emoticons);
        System.out.println(Arrays.toString(answer));
    }

    static Deque<Integer> stack = new ArrayDeque<>();
    static int maxUsers = 0;
    static int maxSales = 0;
    static int[][] user;
    static int[] emoji;
    static int N;

    public static int[] solution(int[][] users, int[] emoticons) {
        N = emoticons.length;
        emoji = emoticons;
        user = users;

        dfs(0);
        return new int[] {maxUsers, maxSales};
    }

    static void dfs(int depth) {
        if (depth == N) {
            calculate();
            return;
        }

        for (int i = 10; i <= 40; i += 10) {
            stack.push(i);
            dfs(depth + 1);
            stack.pop();
        }
    }

    static void print() {
        for (int i : stack) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static void calculate() {
        Integer[] arr = stack.toArray(new Integer[0]);
        int[] discountRate = new int[N];

        for (int i = 0; i < arr.length; i++) {
            discountRate[i] = arr[i];
        }

        int curUsers = 0;
        int curSales = 0;
        for (int i = 0; i < user.length; i++) {
            boolean useEmoticonPlus = false;
            int curRate = user[i][0];
            int curPrice = user[i][1];
            int sale = 0;

            for (int item = 0; item < N; item++) {
                if (curRate <= discountRate[item]) {
                    int curItemPrice = emoji[item] - (emoji[item] * discountRate[item] / 100);
                    sale += curItemPrice;
                }

                if (sale >= curPrice) {
                    useEmoticonPlus = true;
                    break;
                }
            }

            if (useEmoticonPlus) {
                curUsers++;
            } else {
                curSales += sale;
            }
        }

        if (curUsers > maxUsers) {
            maxUsers = curUsers;
            maxSales = curSales;
        } else if (curUsers == maxUsers && curSales > maxSales) {
            maxUsers = curUsers;
            maxSales = curSales;
        }
    }
}
