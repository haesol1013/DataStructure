// 202401833 신해솔

import java.util.Scanner;


public class Hanoi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // integer n 입력 받기
        System.out.print("[Hanoi] input an integer number >> ");
        int n = sc.nextInt();

        // MoveDisk 함수 실행 및 실행 속도 측정
        long startTime, endTime;
        startTime = System.nanoTime();
        MoveDisk(n, 'x', 'y', 'z');
        endTime = System.nanoTime();
        System.out.printf("Duration: %d nane sec.\n", (endTime-startTime));
    }

    static void MoveDisk(int n, char pole_X, char pole_Y, char pole_Z) {
        // n == 0 일 때 호출 종료 (n == 1 함수가 재귀 함수 호출 시)
        if (n == 0) return;
        // X위의 n-1개의 원판을 Z를 이용하여 Y로 이동
        MoveDisk(n-1, pole_X, pole_Z, pole_Y);
        // X위의 가장 큰 원판을 Z로 이동
        System.out.printf("move disk from %s to %s\n", pole_X, pole_Z);
        // Y위의 n-1개의 원판을 X를 이용하여 Z로 이동
        MoveDisk(n-1, pole_Y, pole_X, pole_Z);
    }
}
