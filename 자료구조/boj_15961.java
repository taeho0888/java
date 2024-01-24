package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_15961 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < k; i++) {
            count.put(sushi[i], count.getOrDefault(sushi[i], 0) + 1);
        }

        int curSushi = count.size() + (count.containsKey(c) ? 0 : 1);
        int maxSushi = curSushi;

        for (int i = 1; i < N; i++) {
            int left = sushi[i - 1];

            if (count.get(left) == 1) {
                count.remove(left);
            } else {
                count.put(left, count.get(left) - 1);
            }

            int right = sushi[(i + k - 1) % N];
            count.put(right, count.getOrDefault(right, 0) + 1);

            curSushi = count.size() + (count.containsKey(c) ? 0 : 1);
            maxSushi = Math.max(maxSushi, curSushi);
        }

        System.out.println(maxSushi);
    }
}
