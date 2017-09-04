import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System. in);
        int t = sc.nextInt();
        
        for (int i =0;i<t;i++)
        {
            int r=0;
        int N = sc. nextInt();
            int M = sc. nextInt();
        
   for(int j=0;j<N;j++)
   {
        r =(((M ==1)||(N%2==0))?2:1);
   
   }
        System.out.println(r);
        }
    }}
        
    