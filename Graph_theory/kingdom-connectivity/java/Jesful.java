/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution
{
 class ListNode
 {
  ListNode next;
  int index;
 }
 
 int[] vis=new int[10010];
 ListNode[] list=new ListNode[10010];
 ListNode[] rlist=new ListNode[100010];
 boolean[] vis1=new boolean[10010];
 boolean[] visn=new boolean[10010];
 int N,M;
 boolean hasCircle;
 final long mod=1000000000;
 
 void initList()
 {
  for (int i=1;i<=N;i++)
  {
   list[i]=new ListNode();
   rlist[i]=new ListNode();
  }
 }
 void addEdge(int p,int q)
 {
  ListNode t=new ListNode();
  t.next=list[p].next;
  t.index=q;
  list[p].next=t;
  t=new ListNode();
  t.next=rlist[q].next;
  t.index=p;
  rlist[q].next=t;
 }
 void DFS1(int current)
 {
  for (ListNode ite=list[current].next;ite!=null;ite=ite.next)
  {
   if (!vis1[ite.index])
   {
    vis1[ite.index]=true;
    DFS1(ite.index);
   }
  }
 }
 void DFS1()
 {
  vis1[1]=true;
  DFS1(1);
 }
 void DFSN(int current)
 {
  for (ListNode ite=rlist[current].next;ite!=null;ite=ite.next)
  {
   if (!visn[ite.index])
   {
    visn[ite.index]=true;
    DFSN(ite.index);
   }
  }
 }
 void DFSN()
 {
  visn[N]=true;
  DFSN(N);
 }
 void DFS(int current)
 {
  if (!vis1[current] || !visn[current]) return;
  vis[current]=1;
  for (ListNode ite=list[current].next;ite!=null;ite=ite.next)
  {
   if (vis[ite.index]==0)
   {
    vis[ite.index]=1;
    DFS(ite.index);
   }
   else if (vis[ite.index]==1 && vis1[ite.index] && visn[ite.index])
   {
    hasCircle=true;
   }
  }
  vis[current]=2;
 }
 void DFS()
 {
  hasCircle=false;
  DFS(1);
 }
 
 ArrayList<Integer> result=new ArrayList<Integer>();
 
 void tsort(int current)
 {
  for (ListNode ite=list[current].next;ite!=null;ite=ite.next)
  {
   if (vis[ite.index]==0)
   {
    vis[ite.index]=1;
    tsort(ite.index);
   }
  }
  result.add(current);
 }
 void tsort()
 {
  for (int i=0;i<vis.length;i++) vis[i]=0;
  tsort(1);
 }
 
 boolean[] calced=new boolean[10010];
 long[] dp=new long[10010];
 
 long DP(int x)
 {
  if (x==1) return 1;
  if (calced[x]) return dp[x];
  calced[x]=true;
  dp[x]=0;
  for (ListNode ite=rlist[x].next;ite!=null;ite=ite.next)
  {
   if (vis[ite.index]==0) continue;
   dp[x]+=DP(ite.index);
   dp[x]%=mod;
  }
  return dp[x];
 }
 void run()
 {
  Scanner scan=null;
//  try
//  {
//   scan=new Scanner(new File("/home/jesful/is/out.txt"));
//  }
//  catch (Exception e) {e.printStackTrace();}
  scan=new Scanner(System.in);
  N=scan.nextInt();
  M=scan.nextInt();
  initList();
  for (int i=1;i<=M;i++)
  {
   addEdge(scan.nextInt(),scan.nextInt());
  }
  DFS1();
  DFSN();
  DFS();
  if (hasCircle)
  {
   System.out.println("INFINITE PATHS");
  }
  else
  {
//   System.out.println(DP(N));
   tsort();
   dp[1]=1;
   for (int i=result.size()-1;i>=0;i--)
   {
    {
     for (ListNode ite=list[result.get(i)].next;ite!=null;ite=ite.next)
     {
      dp[ite.index]+=dp[result.get(i)];
      dp[ite.index]%=mod;
     }
    }
   }
   System.out.println(dp[N]);
  }
 }
 public static void main(String[] args)
 {
  new Solution().run();
 }
}