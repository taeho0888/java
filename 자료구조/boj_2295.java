package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_2295 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        int[] sums = new int[N*N];
        for (int i = 0, id = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sums[id++] = nums[i] + nums[j];
            }
        }
        Arrays.sort(sums);

        for (int i = N-1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (Arrays.binarySearch(sums, 0, sums.length, nums[i] - nums[j]) < 0) {
                    continue;
                }
                System.out.println(String.valueOf(nums[i]));
                return;
            }
        }
    }
}
