import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
            arr[i]=sc.nextInt();
        Arrays.sort(arr);
        int i=0;
        int count=0;
        if(k==0)
            {
            for(i=0;i<n;i++)
                count+=arr[i];
             System.out.println(count);
              System.exit(0);
            }
        if(i+k<n)
            {
            i=i+k;
            count+=arr[i];
            }
            
        while((i+k+1)<n)
            {
            i=i+k+1;
            count+=arr[i];
            }
        System.out.println(count);
    }
}