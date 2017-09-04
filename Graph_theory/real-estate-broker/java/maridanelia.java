import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  static ArrayList<Integer> [] houses;
 static ArrayList<Integer> [] people;
 public static void main(String[] args) throws IOException {
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer tok = new StringTokenizer(in.readLine());
  int n = Integer.parseInt(tok.nextToken());
  int m = Integer.parseInt(tok.nextToken());
  readGraph(n, m, in);
  boolean [] visited = new boolean [n];
  int [] matched = new int [m];
  Arrays.fill(matched, -1);
  for(int i=0; i<n; i++){
   if(visited[i]){
    continue;
   }
   bipartite(new boolean [n], i, matched);
  }
  int res = 0;
  for(int a:matched){
   if(a!=-1){
    res++;
   }
  }
  System.out.println(res);
 }

 static void readGraph(int n, int m, BufferedReader in)
   throws IOException {
  houses = new ArrayList [m];
  people = new ArrayList [n];
  
  
  for (int i = 0; i < n; i++) {
   people[i] = new ArrayList<>();
  }
  
  for (int i = 0; i < m; i++) {
   houses[i] = new ArrayList<>();
  }
  
  int[] clientPrice = new int[n];
  int[] clientArea = new int[n];
  for (int i = 0; i < n; i++) {
   StringTokenizer tok = new StringTokenizer(in.readLine());
   clientArea[i] = Integer.parseInt(tok.nextToken());
   clientPrice[i] = Integer.parseInt(tok.nextToken());
  }

  int[] housePrice = new int[m];
  int[] houseArea = new int[m];

  for (int i = 0; i < m; i++) {
   StringTokenizer tok = new StringTokenizer(in.readLine());
   houseArea[i] = Integer.parseInt(tok.nextToken());
   housePrice[i] = Integer.parseInt(tok.nextToken());
  }

  for (int i = 0; i < n; i++) {
   for (int j = 0; j < m; j++) {
    if (clientPrice[i] >= housePrice[j]
      && clientArea[i] < houseArea[j]) {     
     people[i].add(j);
     houses[j].add(i);
    }
   }
  }
  
 }
 
 private static boolean bipartite(boolean [] visited, int cur, int [] matched){
  if(visited[cur]){
   return false;
  }
  
  visited[cur] = true;
  ArrayList<Integer> neighs = people[cur];
  
  for(int neigh:neighs){
   if(matched[neigh] == -1){
    matched[neigh] = cur;
    return true;
   }
   if(bipartite(visited,matched[neigh],matched)){
    matched[neigh] = cur;
    return true;
   }
  }
  
  return false;
 }
}