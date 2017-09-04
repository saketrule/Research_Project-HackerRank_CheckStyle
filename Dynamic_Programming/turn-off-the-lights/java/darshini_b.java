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
        int []a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        int j=1;
        int cost=0;int i;
        while(j*(2*k+1)<=a.length){
            cost+=a[k];j++;
        }if(a.length%(2*k+1)!=0){
            i=a.length%(2*k+1);
            while(i<a.length){
                cost+=a[i];
            }
        }System.out.println(cost);
    }
}