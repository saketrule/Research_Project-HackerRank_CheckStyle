import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i =0; i < tc; tc++){
            int x = sc.nextInt();
            if (x%7 == 0 || x%7 ==1){
                System.out.println("Second");
            } else {
                System.out.println("First");
            }
        }
    }
}