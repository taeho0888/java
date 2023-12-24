package 그리디;

import java.io.*;
import java.util.*;

public class boj_1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < line.length(); j++) {
                char now = line.charAt(j);
                int curVal = map.getOrDefault(now, 0);
                map.put(now, curVal + (int) Math.pow(10, line.length() - j - 1));
            }
        }

        // 정렬을 위해 HashMap을 Map.Entry의 List로 변환
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

        // Value의 내림차순으로 배열을 정렬
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        // 정답 계산
        long answer = 0;
        int num = 9;
        for (Map.Entry<Character, Integer> entry : list) {
            answer += entry.getValue()*num--;
        }

        System.out.println(answer);
    }
}
