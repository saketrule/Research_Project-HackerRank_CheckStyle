import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        
        for(int i=0;i<t;i++){
            int n=in.nextInt();
            
            if(check(n)==1)
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
        public static int check(int n){
            int dp[]=new int[120];
            dp[0]=0;
            dp[1]=0;
            dp[2]=1;
            dp[3]=1;
            dp[4]=1;
            dp[5]=1;
            for(int i=6;i<=n;i++){
                if(dp[i-2]==0 || dp[i-3]==0 || dp[i-5]==0)
                    dp[i]=1;
                else 
                    dp[i]=0;
            }
            return dp[n];
        }
    }