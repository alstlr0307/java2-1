package chapter02;

import java.util.Scanner;

public class practice09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1~99 사이의 정수를 입력하세요>>");
        int a = scanner.nextInt();
        if (a >= 10) {
            int b = a / 10;
            int c = a % 10;
            if (b % 3 == 0 && c % 3 == 0)
                System.out.print("박수짝짝");
            else if ((b % 3 != 0 && c % 3 == 0) || (b % 3 == 0 && c % 3 != 0)) {
                System.out.print("박수짝");
            }else
            System.out.print("박수없음");
        } else if (a % 3 == 0) {
            System.out.print("박수짝");
        } else
            System.out.print("박수없음");

        scanner.close();
    }
}
