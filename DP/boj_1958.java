package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1958 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();

        int[][][] dp = new int[str1.length() + 1][str2.length() + 1][str3.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                for (int k = 1; k <= str3.length() ; k++) {
                    char ch1 = str1.charAt(i - 1);
                    char ch2 = str2.charAt(j - 1);
                    char ch3 = str3.charAt(k - 1);

                    if (ch1 == ch2 && ch2 == ch3) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        int[] tmp = new int[] {
                            dp[i-1][j][k],
                            dp[i][j-1][k],
                            dp[i][j][k-1],
                        };
                        dp[i][j][k] = Arrays.stream(tmp).max().getAsInt();
                    
                    }
                }
            }
        }
        
        System.out.println(dp[str1.length()][str2.length()][str3.length()]);
    }
}
