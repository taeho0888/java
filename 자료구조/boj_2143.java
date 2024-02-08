package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_2143 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int target = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] arr1 = new int[n];
        int[] sum1 = new int[n+1];
        sum1[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
            sum1[i+1] = sum1[i] + arr1[i];
        }
        
        int m = Integer.parseInt(br.readLine());
        int[] arr2 = new int[m];
        int[] sum2 = new int[m+1];
        sum2[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
            sum2[i+1] = sum2[i] + arr2[i];
        }
        
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int sum = sum1[j] - sum1[i];
                map1.put(sum, map1.getOrDefault(sum, 0) + 1);
            }
        }
        
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                int sum = sum2[j] - sum2[i];
                map2.put(sum, map2.getOrDefault(sum, 0) + 1);
            }
        }

        long answer = 0;
        if (map1.size() < map2.size()) {
            for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
                answer += (long) entry.getValue() * map2.getOrDefault(target - entry.getKey(), 0);
            }
        } else {
            for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
                answer += (long) entry.getValue() * map1.getOrDefault(target - entry.getKey(), 0);
            }
        }
        
        System.out.println(answer);
    }
}
