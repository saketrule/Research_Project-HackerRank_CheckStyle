import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
         boolean isPrime[]=new boolean[10000000+1];
            for(int k=2;k<=10000000;k++)
                isPrime[k]=true;
            for(int factor=2;factor*factor<=10000000;factor++){
                if(isPrime[factor]){
                    for(int j=factor;j*factor<=10000000;j++)
                        isPrime[j*factor]=false;
                }
            }
        while(t-->0){
            int n=sc.nextInt();int val=0;
            int a[]=new int[n];
            for(int i=0;i<n;i++){
                a[i]=sc.nextInt();
           
                int count=0;
                if(isPrime[a[i]])
                    count=1;
                else{
                for(int k=2;k<=a[i];k++)
                    if(isPrime[k]){
                    if(a[i]%k==0){
                        a[i]=a[i]/k;
                        k=k-1;
                        count++;
                    }
                }
                }
                    
                    val^=count;
                //System.out.println(count);
            }//System.out.println(val);
            if(val!=0) System.out.println("1");
            else System.out.println("2");
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}