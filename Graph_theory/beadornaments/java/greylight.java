import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static long v[], c[];
 static long mod = 1000000007;
 static int T, N;
    public static void main(String[] args){
     Scanner sc = new Scanner(System.in);
     T = sc.nextInt();
     
     initV();
     for(int i=0;i<T;i++){
      N = sc.nextInt();
      long arr[] = new long[N];
      long tmp1 = 0; 
      long tmp2 = 1;
      
      for(int j=0;j<N;j++){
       arr[j] = sc.nextLong();
       tmp1 += arr[j];
       tmp2 = (tmp2*arr[j])%mod;
      }
      long result = 1;
      for(long l:arr){
       result = (result * v[(int) l])%mod;
      }
      for(int j=2; j<N; j++)
       result = (result*tmp1)%mod;
      if(N>1)
       result = (result*tmp2)%mod;
      System.out.println(result);
     }
    }
    
    public static void initV(){
     v = new long[31];
     for(long i=0;i<31;i++){
      if(i<=2){
       v[(int) i] = 1;
      }
      else{
       v[(int) i] = 1;
       for(long j=0; j<i-2; j++){
        v[(int) i] = (v[(int) i]*i)%mod;
       }
      }
     }     
    }
}