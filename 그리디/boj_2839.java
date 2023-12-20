package 그리디;

import java.io.*;
import java.util.*;

public class boj_2839 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int count5 = (n / 5) + 1;

        while (true) {
            if ((n - --count5 * 5) % 3 == 0) {
                if (count5 < 0) {
                    System.out.println(-1);
                    break;
                }
                System.out.println(count5 + ((n - count5 * 5) / 3));
                break;
            }
        }

        sc.close();
    }
}
