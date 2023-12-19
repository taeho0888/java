package 기초;
import java.io.*;
import java.util.*;

public class boj_15552 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	long t = Integer.parseInt(br.readLine());
    	for (int i = 0; i < t; i ++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		bw.write(Integer.toString(a + b) + "\n");
    	}
    	
    	br.close();
    	bw.close();
    }
}