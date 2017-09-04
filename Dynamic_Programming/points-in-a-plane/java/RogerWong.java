import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Solution{
 private static double[][] sols;
 private static int[][] points;
 private static double[][] ks;
 public static void main(String[] argvs) 
 {
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  
  try {
   int CASE=Integer.valueOf(br.readLine());
   for(;CASE>0;CASE--)
   {
    int N=Integer.valueOf(br.readLine());
    sols=new double[(int) Math.pow(2, N)][2];
    int[] s=new int[N];
    for(int i=0;i<=s.length-1;i++)
    {
     s[i]=1;
    }
    points=new int[N][2];
    for(int i=0;i<=N-1;i++)
    {
     String line=br.readLine();
        //String line=String.valueOf((int)(Math.random()*100))+" "+String.valueOf((int)(Math.random()*100));
     //System.out.println(line);
        points[i][0]=Integer.valueOf(line.split(" ")[0]);
     points[i][1]=Integer.valueOf(line.split(" ")[1]);
    }
    ks=new double[N][N];
    getkk();
    double[] result=dp(s);
    
    System.out.println((long)result[0]+" "+(long)result[1]);
    
   }
  } catch (NumberFormatException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 
 private static double[] dp(int[] s)
 {
 
  //int pos=g(s);
  int pos=0;
  for(int i=0;i<=s.length-1;i++)
  {
   pos+=s[i]<<i;
  }
  double[] result=new double[2];
  if(sols[pos][1]!=0)
  {
   result[0]=sols[pos][0];
   result[1]=sols[pos][1];
   return result;
  }
  
  int sum =getsum(s);
  if(sum==0)
  {
   sols[pos][0]=0;
   sols[pos][1]=1;
   return sols[pos].clone();
  }
  if(sum==1 || sum==2)
  {
   sols[pos][0]=1;
   sols[pos][1]=1;
   return sols[pos].clone();
  }
  
  result[0]=999;
  result[1]=999;
  for(int t=0;t<=s.length-1;t++)
  {
   
   
   if(s[t]!=0)
   {
    s[t]=0;
    double[] dpr=dp(s);
    dpr[0]++;
    if(dpr[0]<result[0])
    {
     result[0]=dpr[0];
     result[1]=(dpr[1])%1000000007;
    }
    else if(dpr[0]==result[0])
    {
     result[1]=(result[1]+dpr[1])%1000000007;
    }
    for(int i=t+1;i<=s.length-1;i++)
    {
     if(s[i]==1)
     {
     double k=ks[i][t];
    
     ArrayList<Integer> gx=new ArrayList<Integer>();
     s[i]=0;
     for(int j=i+1;j<=s.length-1;j++)
     {
      if(s[j]==1 && ks[i][j]==k)
      {
       gx.add(j);
       
      }
     }
     dpr=dp(s);
     dpr[0]++;
     if(dpr[0]<result[0])
     {
      result[0]=dpr[0];
      result[1]=(dpr[1])%1000000007;
     }
     else if(dpr[0]==result[0])
     {
      result[1]=(result[1]+dpr[1])%1000000007;
     }
     for(int j=1;j<=(1<<gx.size())-1;j++)
     {
      int m=j;
      int p=0;
      
      while(m!=0)
      {
       s[gx.get(p)]=(m & 1)^1;
       m=m>>1;
          p++;
      }
      dpr=dp(s);
      dpr[0]++;
      if(dpr[0]<result[0])
      {
       result[0]=dpr[0];
       result[1]=(dpr[1])%1000000007;
      }
      else if(dpr[0]==result[0])
      {
       result[1]=(result[1]+dpr[1])%1000000007;
      }
     }
     
     
     s[i]=1;
     for(int j=0;j<=gx.size()-1;j++)
     {
      s[gx.get(j)]=1;
     }
     
    
     }
     
    }
    s[t]=1;
   }
   
  }
  sols[pos][0]=result[0];
  sols[pos][1]=result[1];
 // System.out.println(v);
  return result;
 }
 
 private static int g(int[] s)
 {
  int result=0;
  for(int i=0;i<=s.length-1;i++)
  {
   result+=s[i]<<i;
  }
  return result;
 }
 private static int getsum(int[] s)
 {
  int result=0;
  for(int i=0;i<=s.length-1;i++)
  {
   result+=s[i];
  }
  return result;
  
 }
 
 private static void getkk()
 {
  
   for(int i=0;i<=points.length-1;i++)
   {
    for(int j=i+1;j<=points.length-1;j++)
    {
     
     int[] x=points[i];
     int[] y=points[j];
     double result=0;
     if(y[0]==x[0])
     {
      result=Double.MAX_VALUE;
     }
     else
     {
     result=(double)(y[1]-x[1])/(double)(y[0]-x[0]);
     }
     ks[i][j]=result;
     ks[j][i]=result;
    }
   }
  
  
 }
 
}