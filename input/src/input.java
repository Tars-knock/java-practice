
import java.util.Random;
import java.util.Scanner;

public class input {
    public static void main(String[] args)
    {
        System.out.println(args);
        System.out.println(args[1]);
        Scanner in =new Scanner(System.in);
        String input = in.nextLine();
        System.out.println("you input is\n"+input);
        
    }
}