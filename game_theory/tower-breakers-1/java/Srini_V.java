import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc=new Scanner(System.in);
        
        int t=sc.nextInt();
        
        int[] towers=new int[t];
        int[] heights=new int[t];
        
        for(int i=0;i<t;i++)
        {
            towers[i]=sc.nextInt();
            heights[i]=sc.nextInt();
        }
        for(int i=0;i<t;i++)
        {
           if(towers[i]%2==0 || heights[i]==1)
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