// https://school.programmers.co.kr/learn/courses/30/lessons/92343?language=java
import java.util.*;

class Solution {
    static int N;
    static int answer = 0;
    static int[] isWolf;
    static ArrayList<Integer>[] tree;
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        isWolf = info;
        
        // 트리 선언
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        
        // 트리에 인풋값 채우기
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
        
        // dfs
        ArrayList<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        dfs(0, 0, 0, nextNodes);
        
        return answer;
    }
    
    static void dfs(int now, int sheep, int wolf, ArrayList<Integer> nextNodes) {
        // 현재 양 & 늑대 갯수 수정
        if (isWolf[now] == 1) wolf++;
        else sheep++;
        
        // 탈출 조건
        if (sheep <= wolf) {
            return;
        }
        
        // 정답 갱신
        answer = Math.max(answer, sheep);
        
        // nextNodes에서 현재 노드 빼고, 현재 노드에서 갈 수 있는 노드 넣음
        ArrayList<Integer> newList = new ArrayList<>(nextNodes);
        newList.remove(Integer.valueOf(now));
        newList.addAll(tree[now]);
        
        // dfs
        for (int node : newList) {
            dfs(node, sheep, wolf, newList);
        }
    }
}