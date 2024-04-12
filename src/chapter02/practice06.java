package chapter02;

import java.util.Scanner;

public class practice06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("돈의 액수를 입력하세요: ");
        int money = scanner.nextInt();
        
        int a = money / 50000;
        money %= 50000;        
        int b = money / 10000;
        money %= 10000;
        int c = money / 1000;
        money %= 1000;        
        int d = money / 500;
        money %= 500;       
        int e = money / 100;
        money %= 100;        
        int f = money / 10;
        money %= 10;        
        int g = money;
        
        System.out.println("오만원권: " + a + " 장");
        System.out.println("만원권: " + b + " 장");
        System.out.println("천원권: " + c + " 장");
        System.out.println("500원짜리 동전: " + d + " 개");
        System.out.println("100원짜리 동전: " + e + " 개");
        System.out.println("10원짜리 동전: " + f + " 개");
        System.out.println("1원짜리 동전: " + g + " 개");
        
        scanner.close();
    }
    
}
