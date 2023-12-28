import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            answer[i] = check(binary);
        }
        
        return answer;
    }
    
    
    static int check(String num) {
        int i = 1;
        int targetLength = (int) Math.pow(2, i) - 1;
        
        // (2^n - 1) 중 n 구하기
        while (targetLength < num.length()) {
            targetLength = (int) Math.pow(2, ++i) - 1;
        }
        
        // (2^n - 1)로 채우기
        while (num.length() < targetLength) {
            num = "0" + num;
        }
        
        // 이진트리 가능?
        if (isValid(num)) return 1;
        else return 0;
    }
    
    
    static boolean isValid(String num) {
        /*
        midChar == 0: 
            leftChar == 1 || rightChar == 1:
                return false
        return true
        */
        int mid = num.length() / 2;
        int left = mid / 2;
        int gap = mid - left;
        int right = mid + gap;
        
        char midChar = num.charAt(mid);
        char leftChar = num.charAt(left);
        char rightChar = num.charAt(right);
        
        // 아래 살펴볼 필요 없을 경우
        if (midChar == '0' && (leftChar == '1' || rightChar == '1')) {
            return false;
        }
        
        // 재귀 로직
        if (gap > 1) {
            boolean leftValid = isValid(num.substring(0, mid));
            boolean rightValid = isValid(num.substring(mid + 1));
            return (leftValid && rightValid);
        }
        else {
            return true;
        }
        
        
    }
}