package 구현;

import java.io.*;
import java.util.*;

public class boj_17413 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        ArrayList<String> strArray = new ArrayList<>();

        // < > 로 일단 나누기
        boolean isOpen = false;
        int startId = 0, endId = 0;
        for (int i = 0; i < str.length(); i++) {
            if (!isOpen) {
                if (str.charAt(i) == '<') {
                    endId = i;
                    if (startId != endId) {
                        strArray.add(str.substring(startId, endId));
                    }
                    startId = i;
                    isOpen = true;
                } else if (str.charAt(i) == ' ') {
                    endId = i;
                    strArray.add(str.substring(startId, endId));
                    startId = i + 1;
                } else if (i == str.length() - 1) {
                    endId = str.length();
                    strArray.add(str.substring(startId, endId));
                }
            } else {
                if (str.charAt(i) == '>') {
                    isOpen = false;
                    endId = i + 1;
                    strArray.add(str.substring(startId, endId));
                    startId = i + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArray.size(); i++) {
            String curStr;

            if (!strArray.get(i).startsWith("<")) {
                StringBuilder curSb = new StringBuilder(strArray.get(i)).reverse();
                curStr = curSb.toString();
            } else {
                curStr = strArray.get(i);
            }

            if (curStr.startsWith("<")) {
                sb.append(curStr);
            } else if (i > 0 && strArray.get(i - 1).startsWith("<")) {
                sb.append(curStr);
            } else if (i > 0 && !strArray.get(i - 1).startsWith("<")) {
                sb.append(" ").append(curStr);
            } else {
                sb.append(curStr);
            }

        }
        System.out.println(sb);
    }
}
