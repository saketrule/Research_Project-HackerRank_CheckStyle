import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
//(m == 1 || n % 2 == 0 ? 2 : 1)
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
 
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        
        for(int i=0;i<n;i++)
        {
            int m=sc.nextInt();
            int r=sc.nextInt();
            
            if(r==1 || m%2==0)
            {
                System.out.println(2);
            }
            else
            {
                System.out.println(1);
            }
        }
    }
}