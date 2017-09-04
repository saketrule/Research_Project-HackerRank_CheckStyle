import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
 public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    long a[] = new long[n];
    
    for(int i = 0; i<n;i++)a[i]=sc.nextLong();
    Arrays.sort(a);
    long p = sc.nextLong();long q = sc.nextLong();
    long max = 0;
    long m = a[0];
    //if(p<a[0]) {max = a[0]-p;m = p;}
    for(int i = 0; i<n-1; i++){
     if(a[i]+a[i+1]%2==0){
      long min = (a[i]+a[i+1])/2;
      if(min<p) continue;
      if(min>q) break;
      if(min-a[i]>max) 
      {
       max = min-a[i];m = min;
      }
      
     }
     else{
      long min1 = (a[i]+a[i+1])/2;
      long min2 = ((a[i]+a[i+1])/2)+1;
      if(min2<p) continue;
      if(min1>q) break;
      if(min1-a[i]>max&&min1>=p) 
      {
       max = min1-a[i];m = min1;
      }
      if(-min2+a[i+1]>max&&min2<=p) 
      {
       max = a[i+1]-min2;m = min2;
      }
     }
    }
    //for(int i = 0; i<n; i++)System.out.print(a[i]+" ");
    for(int i = 0; i<n; i++){
     if(a[i]>p){
      //System.out.println(a[i]);
      if(i==0){
       if(a[i]-p>max){{m = p;max = a[i]-p;}} 
       else if(a[i]-p==max&&p<m){{m = p;max = a[i]-p;}}
      }
      else{
       if(Math.min(p-a[i-1],-p+a[i])>max) {m = p;max = Math.min(p-a[i-1],-p+a[i]);}
       else if(Math.min(p-a[i-1],-p+a[i])==max&&p<m){{m = p;}}
      }
      break; 
     }
    }
    //System.out.println(max+" "+m);
    for(int i = n-1; i>=0; i--){
     if(a[i]<q){
      //System.out.println(a[i]);
      if(i==n-1){
       if(-a[i]+q>max)m = q; 
       else if(-a[i]+q==max&&q<m){{m = q;}}
      }
      else{
       if(Math.min(-q+a[i+1],q-a[i])>max) m = q;
       else if(Math.min(-q+a[i+1],q-a[i])==max&&q<m){{m = q;}}
      }
      break; 
     }
    }
    //if(a[n-1]<q&&(q-a[n-1])>max)m = q;
    System.out.println(m);
 }
}