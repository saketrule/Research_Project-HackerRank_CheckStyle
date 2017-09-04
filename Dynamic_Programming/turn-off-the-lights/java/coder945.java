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
        long a[]=new long[n];
        for(int i=0;i<n;i++){
            a[i]=s.nextLong();
        }
        long cost=0;
        int index=k;
        while(index<n){
            cost=cost+a[index];
            index=index+2*k+1;
        }
        System.out.println(cost);
    }
}