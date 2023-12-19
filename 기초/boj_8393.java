package 기초;
import java.io.*;

public class boj_8393 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int n = Integer.parseInt(br.readLine());
    	int sum = 0;
    	for (int i = 1; i <= n; i++) {
    		sum += i;
    	}
    	bw.write(Integer.toString(sum) + "\n");
    	
    	br.close();
    	bw.close();
    }
}