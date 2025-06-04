// 202401833 신해솔

import java.util.Scanner;


public class Fibonacci {
    public static void main(String[] args) {
        // n 입력 받기
        Scanner sc = new Scanner(System.in);
        System.out.print("[Fibonacci] input an integer number >> ");
        int n = sc.nextInt();

        // for 문으로 구현한 fibonacci 함수(No Recursive)
        long startTime, endTime;
        startTime = System.nanoTime();
        System.out.println("No Recursive: " + n + " = " + fibonacci(n));
        endTime = System.nanoTime();
        System.out.printf("Duration: %d nano sec.\n", (endTime-startTime));

        // 재귀로 구현한 fibonacci 함수 (Recursive)
        startTime = System.nanoTime();
        System.out.println("Recursive: " + n + " = " + fibonacciR(n));
        endTime = System.nanoTime();
        System.out.printf("Duration: %d nano sec.\n", (endTime-startTime));
    }

    public static long fibonacci(int n) {
        // n+1 개의 요소를 갖는 long type의 fibo list 초기화
        long[] fibo = new long[n+1];
        // 0번째와 1번째 값 할당
        fibo[0] = 0;
        fibo[1] = 1;
        // n번째 요소까지 순회하며 i번째 값에 i-1번째 값과 i-2번째 값을 더한 값을 할당
        for (int i = 2; i <= n; i++) {
            fibo[i] = fibo[i-1] + fibo[i-2];
        }
        return fibo[n];
    }

    public static long fibonacciR(int n) {
        // n이 0 -> 0 return
        // n이 1 -> 1 return
        // 나머지 -> 재귀호출
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacciR(n-1) + fibonacciR(n-2);
        }
    }
}