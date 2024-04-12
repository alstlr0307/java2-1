package chapter03;

import java.util.Scanner;

public class Ev3_8 {
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("5개의 정수를 입력하세요.");
		int foo[] = new int[5]; 
		double sum=0.0;
		for(int i=0; i<foo.length; i++)
			foo[i] = scanner.nextInt(); 
		
		for(int i=0; i<foo.length; i++)
			sum += foo[i];

		System.out.print("평균은 " + sum/foo.length);
		
		scanner.close();
	}
    
}
