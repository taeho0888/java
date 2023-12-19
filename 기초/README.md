# 기초

## 🎂 입력 받기
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    // throws IOException할 때, Buffered 머시기 쓸 때 꼭 해줘야 함
    public static void main(String args[]) throws IOException { 
        // 1. 객체 선언

        // input을 위한 객체 BufferedReader 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 한 라인에서 여러 값 할당을 위한 객체 StringTokenizer
        StringTokenizer st = new StringTokenizer(br.readLine());



        // 2. 입력 받기 (한 줄에 할당할 게 하나만 있는 경우)

        // 문자열 입력 받기
        String str = br.readLine();

        // 정수 입력 받기
        int num = Integer.parseInt(br.readLine());



        // 3. 입력 받기 (한 줄에 할당할 게 여러 개 있는 경우)

        // 문자 A와 B 입력 받기
        StringTokenizer st1 = new StringTokenizer(br.readLine()); 
        char ch1 = st1.nextToken();
        char ch2 = st1.nextToken();
        
        // 정수 A와 B 입력 받기

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st2.nextToken());
        int B = Integer.parseInt(st2.nextToken());


        // output을 위한 객채 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        
    }
}

```


## 🎂 출력 하기
```java
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    // throws IOException할 때, Buffered 머시기 쓸 때 꼭 해줘야 함
    public static void main(String args[]) throws IOException { 
        // 1. 객체 선언

        // output을 위한 객채 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // 2.출력 (+로 연결)
        int age = 25;
        bw.write("Hello, World!\n");
        bw.write("나는 " + age + "살 입니다\n");
        
    }
}

```
