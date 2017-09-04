import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        
        int test = sc.nextInt();
        while(test > 0){
            
            int x = sc.nextInt();
            int y = sc.nextInt();
            
            int x1 = x%4;
            int y1 = y%4;
            
            if(y1==0 || y1==3 || x1==0 || x1==3)
                System.out.println("First");
            else
                System.out.println("Second");
            
            test--;
        }
    }
}