import java.util.Scanner;

/**
 *
 * @author Maryam
 */
public class GameofStones {
    public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
            int t =sc.nextInt();
            int n;
            sc.nextLine();
            for (int i = 0; i < t; i++) {
            n=sc.nextInt();
           if(sc.hasNext() )
               sc.nextLine();
            
            if(n%7==0 || n%7==1)
                    System.out.println("Second");
            else
                    System.out.println("First");
                    
        }

    }
}