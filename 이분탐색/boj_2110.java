package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2110 {
    static int N, C;
    static int[] house;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        int left = 0; // 최소 거리
        int right = house[N - 1] - house[0]; // 최대 거리
        int result = 0; // 정답

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }

    static boolean isPossible(int distance) {
        int installedRouters = 1;
        int lastRoutersPosition = house[0];

        for (int i = 1; i < N; i++) {
            if (house[i] - lastRoutersPosition >= distance) {
                installedRouters++;
                lastRoutersPosition = house[i];
            }
        }
        return installedRouters >= C;
    }
}
