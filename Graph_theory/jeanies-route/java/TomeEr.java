import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int [][] distances;
 public static int sum=Integer.MAX_VALUE;

 public static void main(String ... args) throws Exception{
  Scanner sc = new Scanner(System.in);
  int N = sc.nextInt();

  int K=sc.nextInt();
  
  int [] cities = new int[K];
  for (int k=0;k<K;k++) {
   cities[k]=sc.nextInt()-1;
  }
  // we keep distances between cities in a 2D table;
  
  distances = new int[N][N];
  for (int i=0;i<N;i++) 
   for (int j=0;j<N;j++)  {
    if (i==j) distances[i][j]=0;
    else
    distances[i][j]=10000;
    
   }
  
  for (int n=0;n<N-1;n++) {
   int f = sc.nextInt()-1;
   int t = sc.nextInt()-1;
   int d = sc.nextInt();
   
   distances[f][t]=d;
   distances[t][f]=d;
  }
//  for (int i=0;i<N;i++) {
//   for (int j=0;j<N;j++) {
//    System.out.printf("%12d ",distances[i][j]);    
//   }
//   System.out.println();
//  }
  
  for (int k=0;k<N;k++) {
   for (int i=0;i<N;i++) {
    for (int j=0;j<N;j++) {    
     if (distances[i][j]>distances[i][k]+distances[k][j])
      distances[i][j]=distances[i][k]+distances[k][j];
    }        
   }
  }
  
//  System.out.println();
//  for (int i=0;i<N;i++) {
//   for (int j=0;j<N;j++) {
//    System.out.printf("%12d ",distances[i][j]);
//    
//   }
//   System.out.println();
//  }
  
  permute (cities,0);
  System.out.println(sum);
 }
 
 public static void print (int [] a) {
  for (int i=0;i<a.length;i++) {
   System.out.print(a[i]+" ");
  }
  System.out.println();
 }
 public static void permute (int [] a, int k){
  
  if (k==a.length) {
   
   int lsum=0;
   for (int i=0;i<a.length-1;i++) {
    lsum+=distances[a[i]][a[i+1]];
    
   }
//   print (a);
//   System.out.println(sum);
   if (lsum<sum) sum=lsum;
  }
  
  for (int i=k;i<a.length;i++) {
   int t = a[k];
   a[k]=a[i];
   a[i]=t;   
   permute(a,k+1);
   t = a[k];
   a[k]=a[i];
   a[i]=t;
  }
  
 }
}