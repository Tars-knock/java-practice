import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args)
    {
        Random ranint =new Random();
        Scanner input = new Scanner(System.in);
        int aim = ranint.nextInt(101);
        StringBuffer history = new StringBuffer();
        int count = 1;
        int in = input.nextInt();



        while(aim!=in) {
            history.append(in);
            if (in > aim)
                System.out.println("your input is bigger than the aim");
            else if (in < aim)
                System.out.println("your input is smaller than the aim");
            count++;
            in = input.nextInt();
        }
        System.out.println("success!");
        System.out.println("you have try "+ count +" times");
        System.out.println("your input history is\n" + history.toString());
    }
}
