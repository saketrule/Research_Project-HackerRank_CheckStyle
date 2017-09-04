import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        long cost[]=new long[n];
        long ans=0;
        for(int i=0;i<n;i++){
            cost[i]=sc.nextLong();
        }
        if(k==0){
            for(int i=0;i<n;i++){
                ans+=cost[i];
            }
            System.out.println(ans);
        }
        else if(k>n){
            long min=Long.MAX_VALUE;
            for(int i=0;i<n;i++)
            {
                if(cost[i]<min)
                    min=cost[i];
            }
            System.out.println(min);
        }
        else{
            
        }
    }
}