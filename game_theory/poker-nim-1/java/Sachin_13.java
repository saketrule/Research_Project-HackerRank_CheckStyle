import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
  int t=in.nextInt();
  
  while(t>0){
   t--;
   int n=in.nextInt();
            int m=in.nextInt();
            int xor=0;
            for(int i=0;i<n;i++)
                {
                int x=in.nextInt();
                xor^=x;
            }
            if(xor==0)
                System.out.println("Second");
            else
                System.out.println("First");
                
        }
    }
}