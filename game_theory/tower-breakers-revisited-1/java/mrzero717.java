import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 final static int mx = 1000006;
    public static void main(String[] args) {
     Scanner s = new Scanner(System.in);
        ArrayList<Integer> V = new ArrayList<Integer>(Collections.nCopies(mx, 0));
        nk7(V);
        int T=s.nextInt();
        while(T-->0){
         int n;
         n= s.nextInt();
         int omar =0;
         for(int i=0;i<n;++i){
          int x = s.nextInt();
          omar^=V.get(x);
         }
        
         System.out.println((omar>0)?1:2);
        }
    }
    
    static void nk7(ArrayList<Integer> ar){
     boolean prime[] = new boolean[mx];
     for(int i=2;i<mx;++i)
      prime[i] = true;
     for(int i=2;i<mx;++i){
      if(prime[i]){
       for(int j=i;j<mx;j+=i){
        prime[j] = false;
        int tmp =j;
        while(tmp%i==0 && tmp>0){
         ar.set(j, ar.get(j)+1);
         tmp/=i;
        }
       }
      }
     }
     ar.set(1, 0);
    }
}