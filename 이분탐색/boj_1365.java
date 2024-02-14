package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1365 {
    static ArrayList<Integer> asc = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        asc.add(arr[0]);
        for (int i = 1; i < N; i++) {
            int lastIndex = asc.size() - 1;

            if (arr[i] > asc.get(lastIndex)) {
                asc.add(arr[i]);
            } else if (arr[i] < asc.get(lastIndex)) {
                int index = binarySearch(0, lastIndex, arr[i]);
                asc.set(index, arr[i]);
            }
        }

        int answer = N - asc.size();
        System.out.println(answer);
    }

    static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (asc.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
