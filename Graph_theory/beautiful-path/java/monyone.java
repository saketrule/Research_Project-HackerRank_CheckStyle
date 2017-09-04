import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
 
public class Main {
 
 public static class State implements Comparable<State> {
  int cost, node;
  
  public State(int node, int cost){
   this.node = node;
   this.cost = cost;
  }

  @Override
  public int compareTo(State o) {
   return Integer.compare(this.cost, o.cost);
  }
 }
 
 public static void main(String[] args) throws IOException {
  Scanner sc = new Scanner(System.in);
  
  final int N = sc.nextInt();
  final int M = sc.nextInt();
  
  ArrayList<HashMap<Integer, Integer>> adj = new ArrayList<HashMap<Integer, Integer>>();
  for(int i = 0; i < N; i++){
   adj.add(new HashMap<Integer, Integer>());
  }
  
  for(int i = 0; i < M; i++){
   final int U = sc.nextInt() - 1;
   final int V = sc.nextInt() - 1;
   final int C = sc.nextInt();
   
   if(!adj.get(U).containsKey(V)){
    adj.get(U).put(V, Integer.MAX_VALUE);
   }
   if(!adj.get(V).containsKey(U)){
    adj.get(V).put(U, Integer.MAX_VALUE);
   }
   
   if(adj.get(U).get(V) > C){
    adj.get(U).put(V, C);
   }
   if(adj.get(V).get(U) > C){
    adj.get(V).put(U, C);
   }
  }
  
  final int A = sc.nextInt() - 1;
  final int B = sc.nextInt() - 1;
  
  boolean[][] visited = new boolean[N][1024];
  boolean[][] in_queue = new boolean[N][1024];
  in_queue[A][0] = true;
  
  PriorityQueue<State> queue = new PriorityQueue<State>();
  
  queue.add(new State(A, 0));
  
  while(!queue.isEmpty()){
   State state = queue.poll();
   
   in_queue[state.node][state.cost] = false;
   
   if(visited[state.node][state.cost]){
    continue;
   }else{
    visited[state.node][state.cost] = true;
   }
   
   
   //System.out.println(state.node + " " + state.cost);
   
   for(final Entry<Integer, Integer> entry : adj.get(state.node).entrySet()){
    final int next_node = entry.getKey();
    final int next_cost = state.cost | entry.getValue();
    
    if(visited[next_node][next_cost]){
     continue;
    }else if(in_queue[next_node][next_cost]){
     continue;
    }
    
    queue.add(new State(next_node, next_cost));
    in_queue[next_node][next_cost] = true;
   }
  }
  
  
  for(int v = 0; v < visited[B].length; v++){
   if(visited[B][v]){
    System.out.println(v);
    return;
   }
  }
  
  System.out.println(-1);
  
 }
 
 public static class Scanner implements Closeable {
  private BufferedReader br;
  private StringTokenizer tok;
 
  public Scanner(InputStream is) throws IOException {
   br = new BufferedReader(new InputStreamReader(is));
  }
 
  private void getLine() throws IOException {
   while (!hasNext()) {
    tok = new StringTokenizer(br.readLine());
   }
  }
 
  private boolean hasNext() {
   return tok != null && tok.hasMoreTokens();
  }
 
  public String next() throws IOException {
   getLine();
   return tok.nextToken();
  }
 
  public int nextInt() throws IOException {
   return Integer.parseInt(next());
  }
 
  public long nextLong() throws IOException {
   return Long.parseLong(next());
  }
 
  public void close() throws IOException {
   br.close();
  }
 } 
}