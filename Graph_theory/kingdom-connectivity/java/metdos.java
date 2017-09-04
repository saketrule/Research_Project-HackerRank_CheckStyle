import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {
 private static Map <Integer, List <Integer> > incoming;
 private static Set<Integer> set;
 private static long[] dp;
 private static Set<Integer> cycleSet;

 private static long paths(Integer to,ArrayList<Integer> path){
  if(path.contains(to)){
   cycleSet.add(to);
  }
  
  if(set.contains(to)){
   return dp[to];
  }else{
   set.add(to);
  }
   
  ArrayList<Integer> list=(ArrayList<Integer>) incoming.get(to);
  for(Integer x : list){
   ArrayList<Integer> temp=new ArrayList<Integer>(path);
   temp.add(to);
   dp[to]+=paths(x,temp); 
   dp[to]=dp[to]%1000000000;
  }
  return dp[to];
 }


 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  Integer N  = sc.nextInt();
  Integer M  = sc.nextInt();

  dp=new long[N+1];
  set=new HashSet<Integer>();
  incoming=new HashMap<Integer, List <Integer>>();
  cycleSet=new HashSet<Integer>();

  for(Integer i=1;i<N+1;i++){
   incoming.put(i, new ArrayList<Integer>());
  }
  dp[1]=1;

  for(int i=0;i<M;i++){
   Integer from  = sc.nextInt();
   Integer to  = sc.nextInt();
   ArrayList<Integer> list=(ArrayList<Integer>) incoming.get(to);
   list.add(from);
  }
  
  long res=paths(N,new ArrayList<Integer>()); 
  boolean hasCycle=false;
  
  for(Integer tmp : cycleSet){
   if(dp[tmp]!=0){
    System.out.println("INFINITE PATHS");
    hasCycle=true;
    break;
   }
  }
  
  if(!hasCycle){
   System.out.println(res);
  }
 }
}