import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;


public class Solution {

 /**
  * @param args
  */
 @SuppressWarnings("unchecked")
 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int N = sc.nextInt();
  int M = sc.nextInt();
  ArrayList<Integer>[] adj = new ArrayList[M];
  for(int i=0; i<N; i++){
   adj[i] = new ArrayList<Integer>();
  }
  for(int i=0; i<M; i++){ 
   int fr = sc.nextInt()-1;
   int to = sc.nextInt()-1;
   adj[fr].add(to);
  }
  
  int[] dp = new int[N];
  Arrays.fill(dp,-1);
  dp[N-1] = 1;
  boolean[] cycle = new boolean[N];
  boolean[] flag = new boolean[N];
  int ret = doDp(adj, dp, 0, flag, cycle);
  if(infinite)
   System.out.println("INFINITE PATHS");
  else
   System.out.println(ret);
 }
 static boolean infinite = false;
 private static int doDp(ArrayList<Integer>[] adj, int[] dp, int i, boolean[] flag, boolean[] cycle) {
  if(flag[i]){
   cycle[i] = true;
   return 0;
  }
  
  if(dp[i] != -1){
   return dp[i];
  }
  
  flag[i] = true;
  
  int res = 0;
  for(int to : adj[i]){
   int ret = doDp(adj, dp, to, flag, cycle);
   res = (res + ret) % 1000000000;
   if(cycle[i] && res > 0){
    infinite = true;
    return 0;
   }
   
   if(infinite){
    return 0;
   }
  }
  
  cycle[i] = false;
  flag[i] = false;
  dp[i] = res;
  return res;
 }

}