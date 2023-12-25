package 브루트포스;
import java.io.*;
import java.util.*;

public class boj_1759 {
    static int L, C;
    static char[] alphabet;
    static final Set<Character> moSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    static BufferedWriter bw; // main 밖의 메서드에서 bw를 호출할 때는 static 선언이 메모리 효율적임.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st1.nextToken());
        C = Integer.parseInt(st1.nextToken());
        
        alphabet = new char[C];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabet[i] = st2.nextToken().charAt(0);
        }
        
        Arrays.sort(alphabet);

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        dfs(0, 0, 0, 0, "");
        bw.close();
    }
    
    static void dfs(int depth, int start, int ja, int mo, String str) throws IOException {
        if (depth == L) {
            if (ja >= 2 && mo >= 1) {
                bw.write(str + "\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            char curChar = alphabet[i];

            if (moSet.contains(curChar)) {
                dfs(depth + 1, i + 1, ja, mo + 1, str + curChar);
            }
            else {
                dfs(depth + 1, i + 1, ja + 1, mo, str + curChar);
            }
        }
    }
}
