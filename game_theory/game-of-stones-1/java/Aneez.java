import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int N =s.nextInt();
        int i=0;
        int []arr = new int[N];
        int max = 0;
        while(i<N){
            arr[i++]=s.nextInt();
            if(max<arr[i-1])
                max = arr[i-1];
        }
        boolean[] dp = new boolean[max+1];
        dp[0]=false;
        dp[1]=false;
        dp[2]=true;
        dp[3]=true;
        dp[4]=true;
        dp[5]=true;
        for(i=6;i<=max;i++){
            if(dp[i-2]&&dp[i-3]&&dp[i-5])
                dp[i]= false;
            else
                dp[i]=true;
        }
        for(int j:arr){
            if(dp[j])
                System.out.println("First");
            else
                System.out.println("Second");
        }
        
    }
}