package 정렬;

import java.io.*;
import java.util.*;

public class boj_17140 {
    static int R, C, K;
    static int[][] arr = new int[100][100];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int row = 3, col = 3;
        int answer = -1;
        int time = 0;

        while (time <= 100) {
            if (arr[R][C] == K) {
                answer = time;
                break;
            }
            
            // R 연산
            if (row >= col) {
                for (int i = 0; i < row; i++) {
                    Map<Integer, Integer> count = new HashMap<>();
                    for (int j = 0; j < col; j++) {
                        if (arr[i][j] == 0) continue;

                        count.put(arr[i][j], count.getOrDefault(arr[i][j], 0) + 1);
                        arr[i][j] = 0;
                    }

                    List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(count.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            int compareResult = o1.getValue().compareTo(o2.getValue());

                            if (compareResult != 0) return compareResult;
                            return o1.getKey().compareTo(o2.getKey());
                        }
                    });

                    int index = 0;
                    for (Map.Entry<Integer, Integer> entry : entries) {
                        arr[i][index++] = entry.getKey();
                        arr[i][index++] = entry.getValue();

                        if (index == 100) break;
                    }

                    col = Math.max(col, index);
                }
            } 
            // C 연산
            else {
                for (int i = 0; i < col; i++) {
                    Map<Integer, Integer> count = new HashMap<>();
                    for (int j = 0; j < row; j++) {
                        if (arr[j][i] == 0) continue;

                        count.put(arr[j][i], count.getOrDefault(arr[j][i], 0) + 1);
                        arr[j][i] = 0;
                    }

                    List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(count.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            int compareResult = o1.getValue().compareTo(o2.getValue());

                            if (compareResult != 0) return compareResult;
                            return o1.getKey().compareTo(o2.getKey());
                        }
                    });

                    int index = 0;
                    for (Map.Entry<Integer, Integer> entry : entries) {
                        arr[index++][i] = entry.getKey();
                        arr[index++][i] = entry.getValue();

                        if (index == 100) break;
                    }

                    row = Math.max(row, index);
                }
            }
 
            time++;
        }

        System.out.println(answer);
    }
}
