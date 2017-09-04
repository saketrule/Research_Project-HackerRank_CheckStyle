import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class Road {
  int v, r;
  Road() {}
  Road(int v, int r) {
   this.v = v;
   this.r = r;
  }
 }

 static List<List<Road>> graph = new ArrayList<List<Road>>();
 static int e , n;
 static long[] ans = new long[11];
 static int[] comp = new int[100000];
 static int nbrC ;
 static int[] last_dist = new int[100000];
 static  int tot ;
 static boolean[] cycle_found = new boolean[10];
 static int[] cur_ans = new int[10];

 static void DFS(int u , int rem){
  if(comp[u]==0) {
   tot++;
   comp[u]=nbrC;
   last_dist[u] = rem;
   cur_ans[rem]++ ;
  } else {
   int a = (rem - last_dist[u] + 10) %10  ;
   cycle_found[a] = true ;
   return ;
  }
  for(Road v : graph.get(u)){
   DFS(v.v , (rem + v.r) % 10);
  }

 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt(), e = in.nextInt();

  for (int i = 0; i < n; i++) {
   graph.add(new ArrayList<Road>());
  }

  int u, v, r;
  for(int i = 0 ; i < e ; i++){
   u = in.nextInt() - 1;
   v = in.nextInt() - 1;
   r = in.nextInt();
   graph.get(u).add(new Road(v, r % 10));
   graph.get(v).add(new Road(u, (10 - r % 10) % 10));
  }
  nbrC = 1 ;
  for(int i = 0 ; i < n ; i++){
   nbrC++;
   tot = 0;
   if(comp[i] == 0){
    init(cycle_found, false);
    init(cur_ans, 0);
    DFS(i,0) ;
    if(cycle_found[1] || cycle_found[3] || cycle_found[7] || cycle_found[9] || (cycle_found[5] && (cycle_found[2] || cycle_found[4] || cycle_found[6] || cycle_found[8] ) )){
     for(int j = 0 ; j < 10 ; j ++){
      ans[j] += 1l * tot * (tot - 1);
     }
    } else {
     long[] final_add = new long[10] ;
     init(final_add, 0);
     for(int k = 0 ; k < 10 ; k++){
      for(int j = 0 ; j < 10 ; j++){
       final_add[(k + j)%10] += 1l * cur_ans[(10 - k) % 10] * cur_ans[j];
      }
     }
     if(cycle_found[5]){
      for(int k = 0 ; k < 10 ; k ++){
       ans[k] += final_add[k] + final_add[(k + 5) % 10];
      }
      ans[0] -= tot ;
      ans[5] -= tot;
     }
     else if(cycle_found[2] || cycle_found[4]|| cycle_found[6]|| cycle_found[8]){
      for(int k = 0 ; k < 10 ; k++){
       for(int j = 0 ; j<9 ; j+=2){
        ans[k] += final_add[(k + j) % 10];
       }
      }
      ans[0]-=tot ;
      ans[2]-=tot;
      ans[4]-=tot;
      ans[6]-=tot;
      ans[8]-=tot;
     }else {
      for(int k = 0 ; k < 10 ; k++){
       ans[k] += final_add[k];
      }
      ans[0]-= tot ;
     }
    }
   }
  }
  for(int i = 0 ; i < 10 ; i++) {
   System.out.println(ans[i]);
  }
 }

 static void init(int[] array, int defaultValue) {
  for(int i = 0; i < array.length; i ++) {
   array[i] = defaultValue;
  }
 }

 static void init(long[] array, long defaultValue) {
  for(int i = 0; i < array.length; i ++) {
   array[i] = defaultValue;
  }
 }

 static void init(boolean[] array, boolean defaultValue) {
  for(int i = 0; i < array.length; i ++) {
   array[i] = defaultValue;
  }
 }
}