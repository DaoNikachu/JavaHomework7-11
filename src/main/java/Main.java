import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Factorial f =  new Factorial();
        System.out.println(f.factorial(n));
    }
}
