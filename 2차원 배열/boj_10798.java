import java.io.*;

public class boj_10798 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[][] board = new char[5][15];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                board[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (('0' <= board[j][i] && board[j][i] <= '9') ||
                ('A' <= board[j][i] && board[j][i] <= 'Z') ||
                ('a' <= board[j][i] && board[j][i] <= 'z')) bw.write(board[j][i]);
            }
        }
        bw.write("\n");
        bw.close();
        br.close();
    }
}
