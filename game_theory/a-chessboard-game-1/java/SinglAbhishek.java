import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t= in.nextInt();
        for(int i=0;i<t;i++)
            {
            int x= in.nextInt();
            int y= in.nextInt();
            if((x%4==1 || x%4==2) && (y%4==1 || y%4==2))
                System.out.println("Second");
            else
                System.out.println("First");
        }
        
    }
}