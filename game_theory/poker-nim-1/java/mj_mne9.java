import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-->0)
            {
            int n = in.nextInt();
            int k = in.nextInt();
            int b = 0;
            while(n-->0)
                {
                b^=in.nextInt();
                
            }
            if(b==0)
                {
                System.out.println("Second");
                
            }
            else
                {
                System.out.println("First");
            }
        }
}
}