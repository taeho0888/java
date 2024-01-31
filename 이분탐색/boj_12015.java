package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_12015 {
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        arr.add(num[0]);

        for (int i = 1; i < N; i++) {
            int lastIndex = arr.size() - 1;

            // 다음 수가 수열의 마지막 수보다 큰 경우 = 추가
            if (num[i] > arr.get(lastIndex)) {
                arr.add(num[i]);
            }
            // 다음 수가 수열의 마지막 수보다 작은 경우 = 대치
            else if (num[i] < arr.get(lastIndex)) {
                int index = binarySearch(0, lastIndex, num[i]);
                arr.set(index, num[i]);
            }
        }

        System.out.println(arr.size());
    }

    static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
