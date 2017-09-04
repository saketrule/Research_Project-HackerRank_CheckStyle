import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        int n=s.nextInt();
        int k=s.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
            arr[i]=s.nextInt();
 /*       long[] dpArr=new long[n];
        dpArr[0]=arr[0];
        for(int i=1;i<n;i++){
            int start=Math.max(i-(2*k)-1,0);
            for(int j=start;j<i;j++){
                dpArr[i]=dpArr[j]+arr[j+k];
            }
        }*/
        int min=arr[0];
        for(int i=0;i<n;i++)
            min=Math.min(min,arr[i]);
        if(n<=2*k+1)
        System.out.println(min);
        else
            System.out.println(arr[0]);
    }
}