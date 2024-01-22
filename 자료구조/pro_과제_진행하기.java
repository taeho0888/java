// https://school.programmers.co.kr/learn/courses/30/lessons/176962
package 자료구조;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class pro_과제_진행하기 {
    public static void main(String args[]) {
        String[][] plans = new String[][] {
            // {"korean", "11:40", "30"}, 
            // {"english", "12:10", "20"}, 
            // {"math", "12:30", "40"}

            {"science", "12:40", "50"},
            {"music", "12:20", "40"},
            {"history", "14:00", "30"},
            {"computer", "12:30", "100"}
        };

        String[] answer = solution(plans);
        for (String a : answer) System.out.print(a + " ");
        System.out.println();
    }

    static PriorityQueue<Plan> planQueue = new PriorityQueue<>();
    static Deque<Plan> stack = new ArrayDeque<>();

    public static String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int index = 0;
        
        for (String[] cur : plans) {
            String[] time = cur[1].split(":");
            int start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            int playTime = Integer.parseInt(cur[2]);
            
            planQueue.offer(new Plan(cur[0], start, playTime));
        }
        
        while (!planQueue.isEmpty()) {
            Plan cur = planQueue.poll();

            int curEndTime = cur.start + cur.playTime;
            int nextStartTime = (planQueue.isEmpty()) ? Integer.MAX_VALUE: planQueue.peek().start;
            
            // 현재 작업이 더 오래 걸리는 경우
            if (curEndTime > nextStartTime) {
                stack.push(new Plan(cur.name, -1, curEndTime - nextStartTime));
            } else { // 현재 작업을 마칠 수 있는 경우   
                answer[index++] = cur.name;
                
                while (!stack.isEmpty()) {
                    Plan next = stack.pop();
                    curEndTime += next.playTime;
                    
                    if (curEndTime > nextStartTime) {
                        stack.push(new Plan(next.name, -1, curEndTime - nextStartTime));
                        break;
                    } else {
                        answer[index++] = next.name;
                    }
                }
            }
        }
        
        return answer;
    }

    static class Plan implements Comparable<Plan>{
        String name;
        int start;
        int playTime;
        
        public Plan(String name, int start, int playTime) {
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
        
        @Override
        public int compareTo(Plan o) {
            return Integer.compare(this.start, o.start);
        }
    }
}
