import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
public static int  factorial(int a)
    {
    int p=1;
    for(int i=1;i<=a;i++)
    p=p*i;
    return p;
}
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner scan=new Scanner(System.in);
        int t=scan.nextInt();
        
        for(int a0=0;a0<t;a0++)
            {
            
            int n=scan.nextInt();int[] array=new int[n];
            for(int i=0;i<n;i++)
             array[i]=scan.nextInt();
            
            int tot=factorial(n);System.out.println("tot1 ="+tot);
            int next=0;
            Arrays.sort(array);
int count=0;
            for(int j=0;j<n-1;j++)
                {
                
                if(array[j]==array[j+1]){count++; j++;}   
                
                
                
            }
            if(count!=0)
            {tot=tot/(2*count);
             next=factorial(n-count);}
            
            
            System.out.println("tot2 ="+tot);
            
            
            
            
            
            System.out.println("next= "+next);
         // System.out.println(tot-next);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        }
        
        
        
        
        
        
        
        
        
        
    }
}