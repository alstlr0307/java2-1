package chapter03;
public class Ev3_4 {
    public static void main(String[] args) {
        for(int i=1; i<10; i++){
            for(int t=1; t<10; t++){
                System.out.print(i + "*" + t + "=" + i*t);
                System.out.print('\t');
            }
            System.out.println();
        }
    }
    
}
