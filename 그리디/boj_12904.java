package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_12904 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append(br.readLine());

        boolean flag = false;
        while (true) {
            if (sb.toString().equals(target)) {
                flag = true;
                break;
            }

            if (sb.length() <= target.length()) {
                break;
            }

            if (sb.toString().endsWith("A")) {
                sb.deleteCharAt(sb.length() - 1);
            } else if (sb.toString().endsWith("B")) {
                sb.deleteCharAt(sb.length() - 1).reverse();
            }
        }

        if (flag) System.out.println(1);
        else System.out.println(0);
    }   
}
