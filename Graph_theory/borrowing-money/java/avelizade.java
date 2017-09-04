import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
public int n,m,maxMoney = 0;
 int[] c;
 boolean[] isBlocked;
 boolean[] isUsed;
 int[][] ways;
 HashSet<Long> maxSet = new HashSet<Long>();
 
 boolean hasWay()
 {
  boolean has = false;
  for(int i = 1;i<=n;i++)
  {  
   if(!(isBlocked[i] || isUsed[i])){
    return true;
   }
  }
  
  return has;
 }
 
 ArrayList<Integer> blockNeighbors (int nodeNum)
 {
  ArrayList<Integer> blockedNodes = new ArrayList<Integer>();
  for(int i = 1;i<=n;i++)
  {
   if(ways[nodeNum][i]==1 && !isBlocked[i]){
    isBlocked[i] = true;
    blockedNodes.add(i);
   }
  }
  return blockedNodes;
 }
 
 void unlockNeighbors (ArrayList<Integer> blockedNodes)
 {
  for(int nodeNumber : blockedNodes)
  {
   isBlocked[nodeNumber] = false;
  }
 }
 
 void rec(int nodeNum,int curMoney,long nodeSet)
 {
  isUsed[nodeNum] = true;
  ArrayList<Integer> neighbors = blockNeighbors(nodeNum);
  nodeSet = nodeSet | (1 << nodeNum);
  //System.out.printf("NodeNum : %d CurNodeSet : %d %d %d\n",nodeNum,nodeSet,curMoney,maxMoney);
  //if(!hasWay())
  //{
   if(curMoney > maxMoney)
   {
    maxMoney = curMoney;
    maxSet.clear();
    maxSet.add(nodeSet);
    //System.out.printf("NodeSet1 : %d\n",nodeSet);
   }
   else if(curMoney == maxMoney)
   {
    maxSet.add(nodeSet);
    //System.out.printf("NodeSet2 : %d\n",nodeSet);
   }
   //return;
  //}
  
  for(int i = nodeNum + 1; i <= n; i++)
  {
   if(!isBlocked[i] ){
    rec( i, curMoney + c[i], nodeSet);   
   }
  }
  nodeSet = nodeSet & (~(1 << nodeNum));
  unlockNeighbors(neighbors);
  isUsed[nodeNum] = false;
 }
 
 void solve()
 {
  int a,b;
  Scanner sc = new Scanner(System.in);
  n = sc.nextInt();
  m = sc.nextInt();
  c = new int[n+1];
  isBlocked = new boolean[n+1];
  isUsed = new boolean[n+1];
  ways = new int[n+1][];
  for(int i = 0;i<n+1;i++)
   ways[i] = new int[n+1];
  for(int i = 1;i<=n;i++)
   c[i] = sc.nextInt();
  for(int i = 0;i<m;i++)
  {
   a = sc.nextInt();
   b = sc.nextInt();
   ways[a][b] = 1;
   ways[b][a] = 1;
  }
  
  for(int i = 1; i <= n; i++)
  {
   rec( i, c[i], 0);  
   //System.out.println("-------");   
  }
  
  System.out.printf("%d %d\n",maxMoney,maxSet.size());
 }
 
 public static void main(String[] args) {
  /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
  //int qq = 8;
  //System.out.println(~qq);
  new Solution().solve();
 }
}