package 기초;
import java.io.*;
import java.util.*;

public class boj_25304 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    	long x = Integer.parseInt(br.readLine());
    	long n = Integer.parseInt(br.readLine());
    	long sum = 0;
    	
    	for (int i = 0; i < n; i ++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	long a = Integer.parseInt(st.nextToken());
        	long b = Integer.parseInt(st.nextToken());
        	
        	sum += (a * b);
    	}

    	if (x == sum) {
    		bw.write("Yes");
    	} 
    	else {
    		bw.write("No");
    	}
    	
    	br.close();
    	bw.close();
    }
}