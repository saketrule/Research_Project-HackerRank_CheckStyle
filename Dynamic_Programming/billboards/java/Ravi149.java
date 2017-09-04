/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
 public static void main(String argc[]) throws Exception{
  long sav[];
  int profit[];
  int n,k;
  Scanner sc=new Scanner(new InputStreamReader(System.in));
  n=sc.nextInt();
  k=sc.nextInt();
  sav=new long[n+1];
  profit=new int[n];
  for(int i=0;i<n;i++){
   profit[i]=sc.nextInt();
   sav[i]=-1;
  }
  sav[n-1]=profit[n-1];
  sav[n]=0;
  for(int i=n-2;i>=n-k;i--){
   sav[i]=profit[i]+sav[i+1];
  }
  int index=0;
  long max=0,sum=0,cur=0;
  for(int i=n-k-1;i>=0;i--){
   if(index>i&&index<i+k){
    sav[i]=profit[i]+sav[i+1];
   }
   else{
    max=0;sum=0;cur=0;
    for(int j=i;j<n&&j<i+k+1;j++){
     cur=sav[j+1]+sum;
     if(cur>max){
      max=cur;
      index=j;
     }
     sum+=profit[j];
    }
    sav[i]=max;
   }
  }
  System.out.println(sav[0]);
 }
}