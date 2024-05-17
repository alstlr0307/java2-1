package chapter02;
import java.util.Scanner;

public class practice07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("학점을 입력하세요>> ");
        String foo = scanner.next();

        switch (foo) {
            case "A":
            case "B":
                System.out.println("Excellent");
                break;
            case "C":
            case "D":
                System.out.println("Good");
                break;
            case "F":
                System.out.println("Bye");
            default:
                System.out.println("F도 안나오면 넌 그냥 나가라 ㅋ");
                break;
        }
        scanner.close();
    }
}
