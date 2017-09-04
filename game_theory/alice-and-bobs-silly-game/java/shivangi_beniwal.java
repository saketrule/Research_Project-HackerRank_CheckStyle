import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{
    static boolean[] prime=new boolean[100001];
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        Arrays.fill(prime,true);
    
        prime[0]=false;
        prime[1]=false;
        
        for(int i=2;i<100001;i++)
        {
            if(prime[i])
            {
                for(int j=2;j*i<100001;j++)
                    prime[i*j]=false;
            }
        }
        
        for(int a0 = 0; a0 < g; a0++)
        {
            int n = in.nextInt();
            boolean[] clo = prime.clone();
            int win = 1; //bob
            
            for(int i=1;i<=n;i++)
                if(clo[i]) 
                {
                   for(int j=2;j*i<=n;j++)
                       clo[i*j]=false;
                   
                   win = 1- win;
                }
            
            if(win==0)
            System.out.println("Alice");
            else 
            System.out.println("Bob");
        }
    }
}