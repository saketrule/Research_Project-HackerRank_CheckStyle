import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class EdgeJ implements Comparable<EdgeJ> {
 
 int end;
 int weight;
 
 EdgeJ(int end, int weight) {
  this.end = end;
  this.weight = weight;
 }
 
 public int compareTo(EdgeJ o) {
  
  return Integer.compare(this.weight, o.weight);
 }
 
}
public class Solution {
 static int max = 0;
 static int dfsMax(int start, int t, ArrayList<ArrayList<EdgeJ>> al, boolean[] l) {
  int currentMax = 0;
  
  int ix = -1;
  if(l[start]) {
   ix = start;
  }
  
  for(EdgeJ e : al.get(start)) {
   int v = e.end;
   
   if(v != t) {
    v = dfsMax(v, start, al, l);
    
    if(v != -1) {
     int current = max + e.weight;
     
     if(current > currentMax) {
      currentMax = current;
      ix = v;
     }
    }
   }
  }
  max = currentMax;
  return ix;
 }
 
 static int dfsSum(int start, int t, ArrayList<ArrayList<EdgeJ>> al, boolean[] l) {
  
  int sum = 0;
  
  for(EdgeJ e : al.get(start)) {
   int v = e.end;
   
   if(v != t) {
    sum = sum + dfsSum(v, start, al, l);
    if(l[v]) {
     l[start] = true;
     sum = sum + 2 * e.weight;
    }
   }
  }
  return sum;
 }
 public static void main(String [] args) {
  Scanner sc = new Scanner(System.in);
  
  int N = sc.nextInt();
  int K = sc.nextInt();
  
  boolean[] letter = new boolean[N];
  
  int startingP = 0;
  
  for(int i = 0; i < K; i++) {
   startingP = sc.nextInt() - 1;
   letter[startingP] = true;
  }
  
  ArrayList<ArrayList<EdgeJ>> adj = new ArrayList<ArrayList<EdgeJ>>();
  
  for(int i = 0; i < N; i++) {
   adj.add(new ArrayList<EdgeJ>());
  }
  
  for(int i = 0; i < N - 1; i++) {
   int x = sc.nextInt() - 1;
   int y = sc.nextInt() - 1;
   int z = sc.nextInt();
   
   adj.get(x).add(new EdgeJ(y, z));
   adj.get(y).add(new EdgeJ(x, z));
  }
  
  dfsMax(dfsMax(startingP, -1, adj, letter), -1, adj, letter);
  int temp2 = dfsSum(startingP, -1, adj, letter.clone());
  System.out.println(temp2 - max);
 }
}