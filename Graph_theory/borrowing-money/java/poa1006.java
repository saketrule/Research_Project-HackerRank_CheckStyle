import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  // TODO Auto-generated method stub
   Scanner in = new Scanner(System.in);
         
         int N = in.nextInt();
         int M = in.nextInt();
         
         int [] C = new int [N+1];
         boolean [][] road =  new boolean [N+1][N+1];
         
         
         for(int i=1;i<N+1;i++)
          C[i]=in.nextInt();
         
         for(int i=0;i<M;i++){
          int a = in.nextInt();
          int b = in.nextInt();
          road[a][b]=true;
          road[b][a]=true;
         }
         //boolean []visit=new boolean[N+1];
         int [] number = new int [1];
         int ans=-1;
         int []ansm = new int [1];
         ansm[0]=-1;
         for(int i=1;i<N+1;i++){
          //boolean[] n = deepCopy(visit);
    boolean []visit=new boolean[N+1];
    for(int j=i;j>0;j--)
     visit[j]=true;
    
    int tmp = sol(i,road,visit,C,N,0,number,ansm);
          if(tmp>ans)
           ans=tmp;
          //System.out.println(tmp);
         }
      System.out.println(ans+" "+number[0]);
    
 }
 
 public static int check(int ans,int[] max,int [] num){
  if(ans>max[0]){
   max[0] = ans;
   num[0]=1;
   return ans;
  }
  else if (ans==max[0]){
   num[0]++;
   return max[0];
  }
  else
   return max[0];
 }
 
 public static int sol(int sta,boolean [][] road,boolean []visit,int[] C,int N,int ans,int [] num,int[] max){
  ans+=C[sta];

  
  max[0] = check(ans,max,num);
  
  //System.out.println("sta = "+sta+"ans = "+ans+" max = "+max+" num= "+num[0]);
  /*
  for(int i=1;i<N+1;i++){
   if(visit[i])
    System.out.print("1 ");
   else
    System.out.print("0 ");
   
  }
  System.out.println();
  */
  boolean t = true;
  for(int i=1;i<N+1;i++)
   if(!visit[i])
    t = false;
  if(t)
   return ans;
  
  for(int i=1;i<N+1;i++)
   if(road[sta][i])
    visit[i]=true;
  
  int tmp=0,Max=ans;
  for(int i=sta+1;i<N+1;i++)
   if(!visit[i]){
    boolean[] n = deepCopy(visit);
    n[i]=true;
    tmp=sol(i,road,n, C,N,ans,num,max);
    if(tmp>Max)
     Max=tmp;
   }
  return Max;
 }
 public static boolean[] deepCopy(boolean [] a){
  boolean[] n = new boolean[a.length];
  for(int i=0;i<a.length;i++)
   n[i]=a[i];
  return n;
 }
}