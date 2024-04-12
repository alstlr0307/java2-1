package chapter03;

import java.util.Scanner;
public class Ev3_7 {
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int foo[]; 
		foo = new int[5]; 
		int max=0; 
		System.out.println("양수 5개를 입력하세요.");	

		for(int i=0; i<5; i++) {
			foo[i] = scanner.nextInt(); 
			if(foo[i] > max) 	
				max = foo[i];	
		}
		System.out.print("가장 큰 수는 " + max + "입니다.");
		
		scanner.close();
    }
}
