import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        
        for(int j=0;j<test;j++)
        {
            int num = Integer.parseInt(br.readLine());
            int dp[] = new int[num+1];
            if(num>5)
            {    
            dp[0] = 2;
            dp[1] = 2;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 1;
            dp[5] = 1;

            for(int i=6;i<=num;i++)
            {
                if(dp[i-2]==2)
                {
                    dp[i] = 1;
                }   
                else if(dp[i-3]==2)
                {
                    dp[i] = 1;
                }    
                else if(dp[i-5]==2)
                {
                    dp[i] = 1;
                }    
                else
                {
                    dp[i] = 2;
                }    
            }
            if(dp[num]==1)
            {
                System.out.println("First");
            }    
            else
            {
                System.out.println("Second");
            }   
            }
            else
            {
                if(num == 0)
                {
                    System.out.println("Second");
                }
                else if(num == 1)
                {
                    System.out.println("Second");
                }
                else if(num == 2)
                {
                    System.out.println("First");
                }
                else if(num == 3)
                {
                    System.out.println("First");
                }
                else if(num == 4)
                {
                    System.out.println("First");
                }
                else if(num == 5)
                {
                    System.out.println("First");
                }
            }
        }
    }
}