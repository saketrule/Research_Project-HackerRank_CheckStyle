import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        for (;a>0;a--) {
            int b = sc.nextInt();
            if ((b%7==0)||(b%7==1)) {
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
    }
}