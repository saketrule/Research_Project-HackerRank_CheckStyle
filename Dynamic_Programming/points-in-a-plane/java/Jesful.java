/*
Please write complete compilable code.
Your class should be named Solution
Read input from standard input (STDIN) and print output to standard output(STDOUT).
For more details, please check http://www.interviewstreet.com/recruit/challenges/faq/view#stdio
*/
import java.util.*;
import java.io.*;

public class Solution
{
 class Point
 {
  int x,y;
  
  Point() {}
  Point(int p,int q)
  {
   x=p;
   y=q;
  }
  
  int cross(Point p)
  {
   return x*p.y-y*p.x;
  }
  Point minus(Point p)
  {
   return new Point(x-p.x,y-p.y);
  }
 }
 
 final int mod=1000000007;
 
 int counter=0;
 
 int N;
 int T;
 
 int[] bin=new int[20];
 
 int[] dp=new int[1 << 16];
 int[][] c=new int[1 << 16][140];
 int[][] vis=new int[1 << 16][140];
 int[] ones=new int[1 << 16];
 
 int[][] subset=new int[1 << 16][];
 int[] len=new int[1 << 16];
 
 int dest,mx;
 int[][] one=new int[1 << 16][20];
 
 Point[] point=new Point[20];
 int[] line=new int[1 << 16];
 
 int[] states=new int[140];
 int S;
 
 Scanner scan;
 
 void DFS(int current,int value,int cnt)
 {
  if (current==mx)
  {
   if (cnt>=2)
   {
    subset[dest][len[dest]++]=value;
   }
   return;
  }
  DFS(current+1,value,cnt);
  DFS(current+1,value+bin[one[dest][current]],cnt+1);
 }
 
 void buildSubset()
 {
  for (int i=0;i<bin[16];i++)
  {
   mx=0;
   for (int j=0;j<16;j++)
   {
    if ((i & bin[j])!=0)
    {
     ones[i]++;
     one[i][mx++]=j;
    }
   }
   /*subset[i]=new int[bin[mx]];
   if (mx>=2)
   {
    dest=i;
    DFS(0,0,0);
   }*/
  }
 }
 void buildSubset(int state)
 {
  if (subset[state]!=null) return;
  subset[state]=new int[bin[ones[state]]];
  mx=ones[state];
  if (mx>=2)
  {
   dest=state;
   DFS(0,0,0);
  }
 }
 
 void init()
 {
  N=scan.nextInt();
  for (int i=0;i<N;i++)
  {
   point[i]=new Point(scan.nextInt(),scan.nextInt());
  }
  S=0;
  for (int i=0;i<N;i++)
  {
   states[++S]=bin[i];
   for (int j=i+1;j<N;j++)
   {
    int current=bin[i]+bin[j];
    for (int k=0;k<N;k++)
    {
     if (k==i || k==j) continue;
     if ((point[k].minus(point[j])).cross(point[k].minus(point[i]))==0)
     {
      current+=bin[k];
     }
    }
    line[bin[i]+bin[j]]=current;
    states[++S]=current;
   }
  }
  Arrays.sort(states,1,S+1);
  int s=S;
  S=0;
  for (int i=1;i<=s;)
  {
   int current=states[i];
   states[++S]=current;
   for (;i<=s && current==states[i];i++) ;
  }
 }
 
 /*int DP(int state)
 {
  if (state==0) return 0;
  if (dp[state]!=0) return dp[state];
  dp[state]=0x7FFFFFFF;
  for (int i=0;i<N;i++)
  {
   if ((state & bin[i])==0) continue;
   dp[state]=Math.min(dp[state],DP(state ^ bin[i])+1);
   for (int j=i+1;j<N;j++)
   {
    if ((state & bin[j])==0) continue;
    int l=line[bin[i]+bin[j]];
    l=l & state;
    dp[state]=Math.min(dp[state],DP(state ^ l)+1);
   }
  }
  return dp[state];
 }*/
 
 void DP()
 {
  dp[0]=0;
  for (int i=1;i<bin[N];i++)
  {
   dp[i]=1 << 20;
   for (int j=0;j<ones[i];j++)
   {
    dp[i]=Math.min(dp[i],dp[i ^ bin[one[i][j]]]+1);
    for (int k=j+1;k<ones[i];k++)
    {
     int l=line[bin[one[i][j]]+bin[one[i][k]]] & i;
     dp[i]=Math.min(dp[i],dp[i ^ l]+1);
    }
   }
  }
 }
 
 /*int C(int state)
 {
  if (state==0) return 1;
  if (vis[state]) return c[state];
  vis[state]=true;
  c[state]=0;
  for (int i=0;i<N;i++)
  {
   if ((state & bin[i])!=0)
   {
    if (DP(state)==DP(state ^ bin[i])+1)
    {
     c[state]+=C(state ^ bin[i]);
     c[state]%=mod;
    }
   }
  }
  int[] tmp=new int[128];
  int cnt=0;
  for (int i=0;i<N;i++)
  {
   if ((state & bin[i])==0) continue;
   for (int j=i+1;j<N;j++)
   {
    if ((state & bin[j])==0) continue;
    tmp[cnt++]=line[bin[i]+bin[j]] & state;
   }
  }
  Arrays.sort(tmp, 0, cnt);
  for (int i=0;i<cnt;)
  {
   int current=tmp[i];
   for (;i<cnt && tmp[i]==current;i++) ;
   for (int j=0;j<len[current];j++)
   {
    if (DP(state)==DP(state ^ subset[current][j])+1)
    {
     c[state]+=C(state ^ subset[current][j]);
     c[state]%=mod;
    }
   }
  }
  return c[state];
 }*/
 
 int C(int state,int step)
 {
  if (step==0)
  {
   if (state==0) return 1;
   return 0;
  }
  if (state==0)
  {
   return 1;
  }
  if (vis[state][step]==T) return c[state][step];
  vis[state][step]=T;
  c[state][step]=C(state,step-1);
  if (ones[states[step]]!=1)
  {
   int l=states[step] & state;
   buildSubset(l);
   for (int i=0;i<len[l];i++)
   {
    if (dp[state]==dp[state ^ subset[l][i]]+1)
    //if (DP(state)==DP(state ^ subset[l][i])+1)
    {
     c[state][step]+=C(state ^ subset[l][i],step-1);
    }
   }
  }
  else
  {
   if ((states[step] & state)!=0)
   {
    if (dp[state]==dp[state ^ states[step]]+1)
    //if (DP(state)==DP(state ^ states[step])+1)
    {
     c[state][step]+=C(state ^ states[step],step-1);
    }
   }
  }
  return c[state][step];
 }
 
// void calc()
// {
//  c[0]=1;
//  for (int i=1;i<bin[N];i++)
//  {
//   c[i]=0;
//   for (int j=0;j<N;j++)
//   {
//    if ((i & bin[j])!=0 && DP(i)==DP(i ^ bin[j])+1)
//    {
//     c[i]+=c[i ^ bin[j]];
//     c[i]%=mod;
//    }
//   }
//   int cnt=0;
//   for (int j=0;j<N;j++)
//   {
//    if ((i & bin[j])==0) continue;
//    for (int k=j+1;k<N;k++)
//    {
//     if ((i & bin[k])==0) continue;
//     tmp[cnt++]=line[bin[j]+bin[k]] & i;
//    }
//   }
//   Arrays.sort(tmp,0,cnt);
//   for (int j=0;j<cnt;)
//   {
//    int current=tmp[j];
//    for (;j<cnt && tmp[j]==current;j++) ;
//    for (int k=0;k<len[current];k++)
//    {
//     if (DP(i)==DP(i ^ subset[current][k])+1)
//     {
//      c[i]+=c[i ^ subset[current][k]];
//      c[i]%=mod;
//     }
//    }
//   }
//  }
// }
 
 void work()
 {
  DP();
  //counter=0;
  //calc();
  int step=dp[bin[N]-1];
  long res=C(bin[N]-1,S)%mod;
  for (int i=1;i<=step;i++)
  {
   res*=i;
   res%=mod;
  }
  System.out.println(step+" "+res);
 }
 
 void run()
 {
  bin[0]=1;
  for (int i=1;i<20;i++) bin[i]=bin[i-1] << 1;
  buildSubset();
//  try
//  {
//   scan=new Scanner(new File("/home/jesful/is/points_in_a_plane.txt"));
//  }
//  catch (Exception e)
//  {
//   e.printStackTrace();
//  }
  scan=new Scanner(System.in);
  int t=scan.nextInt();
  for (T=1;T<=t;T++)
  {
   init();
   work();
  }
 }
 
 public static void main(String[] args)
 {
  new Solution().run();
 }
}