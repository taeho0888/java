# 스택, 큐, 덱

## 스택
- 후입선출(LIFO) 자료구조
- DFS를 구현할 때 사용
- Java에서는 스택을 구현할 때 ArrayDeque를 사용하는 게 가장 좋다.

### 선언
```java
import java.util.ArrayDeque;

public class Main {
    public static void main(String args[]) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
    }
}
```

### 삽입
```java
ArrayDeque<Integer> stack = new ArrayDeque<>();
stack.push(1);
stack.push(2);
stack.push(3);
```

### 삭제
```java
System.out.println(stack.pop()); // 3
System.out.println(stack.pop()); // 2
System.out.println(stack.pop()); // 1
```

## 큐
- 선입선출(FIFO) 자료구조
- BFS를 구현할 때 사용
- 스택과 동일하게 ArrayDeque를 이용하여 구현한다.

### 선언
```java
import java.util.ArrayDeque;

public class Main {
    public static void main(String args[]) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
    }
}
```

### 삽입
```java
ArrayDeque<Integer> queue = new ArrayDeque<>();
queue.offer(1);
queue.offer(2);
queue.offer(3);
```

### 삭제
```java
System.out.println(queue.poll()); // 1
System.out.println(queue.poll()); // 2
System.out.println(queue.poll()); // 3
```