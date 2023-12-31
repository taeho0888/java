import java.io.*;
import java.util.*;

public class boj_10807 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int n = Integer.parseInt(br.readLine());
    	int[] array = new int[n];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < n; i ++) {
    		array[i] = Integer.parseInt(st.nextToken());
    	}
    	int v = Integer.parseInt(br.readLine());
    	int answer = 0;
    	
    	for (int i = 0; i < n; i++) {
    		if (array[i] == v) answer++;
    	}
    	
    	bw.write(Integer.toString(answer) + "\n");
    	bw.close();
    	br.close();
    }
}