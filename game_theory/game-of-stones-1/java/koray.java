import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        for(int t = sc.nextInt() ; t >=0 ; t--)
        {
            int x = sc.nextInt();
            if(x==1)
                System.out.println("Second");
            else if(x % 7== 0 || x%7 == 1){
                System.out.println("Second");
            }else
                System.out.println("First");
        }
    }
}