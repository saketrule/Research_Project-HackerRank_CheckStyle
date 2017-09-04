/**
 * Created by Jiho on 2016. 10. 1..
 */

/*
*
* for search shift + command + F
*
* Topics
*
*   while loop inputs
*   int array
*
*
*
* */



import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solusion {
    //public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int r = 0;
            for (int i = 0; i < n; i++)
                if (scanner.nextInt() % 2 == 1)
                    r ^= i;
            if (r == 0)
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
        /*
        Scanner sc = new Scanner(System.in);

        int total_number_of_rounds = sc.nextInt();


        int number_of_rounds = 0;
        while (number_of_rounds < total_number_of_rounds ) {
            int num_of_squares = sc.nextInt();

            int[] input_array = new int[num_of_squares];;
            int sum = 0;
            for (int i=0; i < num_of_squares; i++) {
                input_array[i] = sc.nextInt();
                sum += input_array[i];
            }

            String ans = "First";

            int sum2toEnd = 0;
            for (int i=2; i < num_of_squares; i++) {
                input_array[i] = sc.nextInt();
                sum2toEnd += input_array[i];
            }


            if (input_array.length == 2) {
                if (input_array[1] % 2 == 1) {
                    ans = "Second";
                }
            }
            if (sum2toEnd == 0) {
                if (sum2toEnd % 2 == 0) {
                    ans = "Second";
                }
                if (sum2toEnd % 1 == 0) {
                    ans = "First";
                }
            }
            System.out.println(ans);
            number_of_rounds++;
        }

    }
    */

}