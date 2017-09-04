import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int q=sc.nextInt();
        for(int loop=1;loop!=q+1;loop++){
            int n=sc.nextInt();
            int nprime=0;
            long sum=0;
            long[] f;
            int[] a=new int[n];
            int i,k;
            for(i=0;i<n;i++)
                a[i]=sc.nextInt();
            Arrays.sort(a);
            for(i=0;i<n-1;i++){
                if(a[i]==a[i+1])
                    nprime++;
            }
            f=new long[nprime+1];
            f[nprime]=fact(n-nprime);
            for(k=nprime;k>=1;k--){
                f[k-1]=(((((f[k])*k)%1000000007)*(n-k+1)%1000000007)*inv(2*(nprime-k+1)))%1000000007;
            }
            for(k=0;k<=nprime;k++){
                if(k%2==0){
                    sum=(sum+f[k])%1000000007;
                }
                else{
                    sum=((sum+1000000007)-f[k])%1000000007;
                }
            }
            System.out.println(sum);
        }
    }
    public static long inv(int n){
        long a=n;
        return computepow(a,1000000005);
    }
    public static long computepow(long a,long n){
        if(n<1)
            return 1;
        long prod=1;
        if(n%2==0){
            prod=computepow(a,n/2);
            return ((prod*prod)%1000000007);
        }
        else{
            prod=computepow(a,n/2);
            prod=(((prod*prod)%1000000007)*a)%1000000007;
            return prod;
        }
    }
    public static long fact(int n){
        if(n<=1)
            return 1;
        long prod=1;
        for(int i=1;i<=n;i++)
            prod=(prod*i)%1000000007;
        return prod;
    }
}