package chapter03;
public class Ev3_3 {
    public static void main(String[] args) {
        char a= 'a';
        char A = 'A';

        do {
            System.out.println(a);
            a=(char)(a+1);
        } while(a<='z');

        while(A<='Z'){
            System.out.println(A);
            A=(char)(A+1);
        }
    }
    
}
