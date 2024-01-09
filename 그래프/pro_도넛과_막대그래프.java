package 그래프;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class pro_도넛과_막대그래프 {
    public int[] solution(int[][] edges) {
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();
        Set<Integer> vertex = new HashSet<>();
        int addVertex = 0, donut = 0, stick = 0, eight = 0;

        // 정점 찾기
        for (int[] edge : edges) {
            vertex.add(edge[0]);
            vertex.add(edge[1]);
        }

        for (int v : vertex) {
            start.put(v, 0);
            end.put(v, 0);
        }

        for (int[] edge : edges) {
            int s = edge[0], e = edge[1];

            start.put(s, start.get(s) + 1);
            end.put(e, end.get(e) + 1);
        }

        for (int v : vertex) {
            int curStart = start.get(v);
            int curEnd = end.get(v);

            if (curStart >= 2 && curEnd == 0) {
                addVertex = v;
            } else if (curStart == 0) {
                stick++;
            } else if (curStart >= 2 && curEnd >= 2) {
                eight++;
            }
        }
        donut = start.get(addVertex) - stick - eight;
        return new int[] {addVertex, donut, stick, eight};
    }
}
