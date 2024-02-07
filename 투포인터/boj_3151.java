package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_3151 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(arr);

        long answer = 0;
        for (int left = 0; left < N - 2; left++) {
            if (arr[left] > 0) break;

            int mid = left + 1;
            int right = N - 1;

            while (mid < right) {
                int sum = arr[left] + arr[right] + arr[mid];

                if (sum == 0) {
                    int midCount = 1;
                    int rightCount = 1;

                    if (arr[mid] == arr[right]) {
                        int n = right - mid + 1;
                        answer += nC2(n);
                        break;
                    }

                    while (arr[mid] == arr[mid + 1]) {
                        midCount++;
                        mid++;
                    }

                    while (arr[right] == arr[right - 1]) {
                        rightCount++;
                        right--;
                    }

                    answer += midCount * rightCount;
                }

                if (sum < 0) mid++;
                else right--;
            }
        }

        System.out.println(answer);
    }

    static long nC2(int n) {
        return (long) n * (n - 1) / 2;
    }
}
