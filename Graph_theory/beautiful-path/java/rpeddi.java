import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Bag<Item extends Comparable<Item>> implements Iterable<Item> {
 
 private Node first;
 private int N;
 
 /************NODE CLASS************/
 
 private class Node{
  Item item; Node next;
  Node(Item k){
   this.item =k;
  }
 }
 
 /************isEmpty() IMPLEMENTATION************/
 
 private boolean isEmpty(){
  return N==0;
 }
 
 public int size(){return N;}
 
 /************ CONTAINS ************/
 
 public boolean contains(Item item){
  Node current = first;
  if(isEmpty()) return false;
  while(current != null){
   if(current.item.equals(item)){
    return true;
   }
   current = current.next;
  }
  return false;
 }
 
 
 /************ADD IMPLEMENTATION************/
 
 public void add(Item k){
  Node current = first;
  if(isEmpty()) { first = new Node(k);N++;return;}
  Node oldfirst = first;
  first = new Node(k);
  first.next = oldfirst;
  N++;
  return;  
 }
 
 /*********  DELETE IMPLEMENTATION ****************/
 
 public void delete(Item k){
  Node current = first;
  if(isEmpty()) return;
  
  Node previous = current;
  while(current != null){
   if(current.item.compareTo(k)==0) break;
   previous = current;   
   current = current.next;
  }
  
  if(current == first){first = first.next;return;}
  if(current == null) return;
  
  previous.next = current.next;
 }
 
 /************ITERATOR IMPLEMENTATION************/
 //returns an iterable list 
 public Iterator<Item> iterator(){
  return new ListIterator();
 }
 
 /************LIST ITERATOR CLASS ************/
 //making the datastructure iterable and returns an iterable list 
 private class ListIterator implements Iterator<Item>{

  private Node current = first;
  
  public boolean hasNext(){
   return current != null;
  }
  
  public Item next(){
   Item item = current.item;
   current = current.next;
   return item;
  }
        
        public void remove(){
            return;
        }
  
 }
}

class Edge implements Comparable<Edge> {
 
 private final int v,w;
 private final int weight;
 
 public Edge(int v, int w, int weight){
  this.v = v; this.w=w; this.weight = weight;
 }
 
 public int weight(){return this.weight;}
 public int either(){ return v;}
 public int other(int o){
  if(o == v) return w;
  else if(o==w) return v;
  else throw new RuntimeException("Invalid choice of vertex!");  
 }

 @Override
 public int compareTo(Edge that) {
  if(this.weight<that.weight) return -1;
  else if(this.weight> that.weight) return 1;
  else return 0;
 }
 
 public String toString(){
  return String.format("%d - %d, weight: %d ", v,w,weight);
 }

}

class EdgeWeightedGraph {
 
 private final int V;
 private int E;
 public Bag<Edge>[] adj;
 
 /*************** CONSTRUCTORS ****************/
 
 public EdgeWeightedGraph(int V){
  this.V = V; this.E =0;
  adj = (Bag<Edge>[]) new Bag[V];
  for(int i=0; i<V;i++)
   adj[i] = new Bag<Edge>();
 }
   
 /*************** DELETE EDGE IMPLEMENTATION ****************/
 
 public void deleteEdge(Edge e){
  int v= e.either(),w = e.other(v);
  adj[v].delete(e); adj[w].delete(e);
 }
 
 /*************** ADD EDGE IMPLEMENTATION ****************/
 
 public void addEdge(Edge e){
  int v = e.either(), w = e.other(v);
  adj[v].add(e); adj[w].add(e);
  E++;
 }
 
 /*************** UTILITY METHODS ****************/
 
 public int V(){return V;}
 public int E(){return E;}
 
 public Iterable<Edge> adj(int v){
  return adj[v];
 }
 
 public Iterable<Edge> edges(){
  int count=0;
  Bag<Edge> edges = new Bag<Edge>();
  for(int i=0; i<V;i++){
   for(Edge e: adj[i]){
    int w = e.other(i);
    if(i<w) {edges.add(e);count++;}
   }
  }
  System.out.println(count);
  return edges;
 }
}

class IndexMinPQ<Key extends Comparable<Key>> {
 
 private int N; 
 private int[] pq,qp;
 private Key[] keys;
 
 public IndexMinPQ(int cap){
  pq = new int[cap+1]; qp = new int[cap+1];
  keys = (Key[]) new Comparable[cap+1];
  for(int i=0; i<cap+1;i++)
   qp[i] =-1;
 }
 
 public boolean isEmpty(){return N==0;}
 public int size() { return N;}
 public boolean contains(int k){ return qp[k]!=-1;}
 
 public void insert(int k, Key key){
  N++; qp[k] = N; pq[qp[k]] = k;
  keys[k] = key;
  swim(N);
 }
 
 public void change(int k, Key key){
  Key current = keys[k];
  if(current.compareTo(key)>0){
   keys[k] = key; sink(k);
  } else if(current.compareTo(key)<0){
   keys[k] =key; swim(k);
  }
 }
 
 public Key min(){
  return keys[pq[1]];
 }
 
 public int delMin(){
  int indexOfMin = pq[1];
  exch(1,N--);
  sink(1);
  keys[pq[N+1]] = null;
  qp[pq[N+1]] = -1;
  return indexOfMin;  
 }
 
 private boolean greater(int i, int j){
  return keys[pq[i]].compareTo(keys[pq[j]]) >0;
 }
 
 private void exch(int i, int j){
  int swap = pq[i]; pq[i]=pq[j]; pq[j]= swap;
  qp[pq[i]]=i; qp[pq[j]]=j;
 }
 
 private void swim(int k){
  while(k>1 && greater(k/2 , k)){
   exch(k/2,k); k = k/2;
  }
 }
 
 private void sink(int k){
  while(2*k<=N){
   int j = 2*k;
   if(j<N && greater(j,j+1)) j++;
   if(!greater(k,j)) break;
   exch(k,j);
   k=j;
  }
 }
 
}

public class Solution {
    
    static Edge[] edgeTo;
    static int[] distTo;
    static IndexMinPQ<Integer> pq ;
    
    public static void relax(EdgeWeightedGraph ewg, int v){
        
        for(Edge ed: ewg.adj(v)){
            
            int w = ed.other(v);
            
   if(distTo[w]> (distTo[v]|ed.weight())){
    
    distTo[w]=distTo[v]|ed.weight(); 
                edgeTo[w]=ed;
    
                if(pq.contains(w)) pq.change(w, distTo[w]);
    else pq.insert(w, distTo[w]);
    
   }
   
  }
        
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int V = scan.nextInt(), E = scan.nextInt();
        
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(V+1);
        for(int e=0;e<E; e++){
            int v = scan.nextInt(),u = scan.nextInt(),d = scan.nextInt();
            Edge ed = new Edge(v,u,d);
            ewg.addEdge(ed);
        }
        
        int source = scan.nextInt(),sink = scan.nextInt();
        
        edgeTo = new Edge[V+1]; distTo = new int[V+1];
        pq = new IndexMinPQ<Integer>(V+1);
        for(int i=0; i<=V; i++){
            distTo[i] = Integer.MAX_VALUE;
        }
        
        distTo[source]=0;
        pq.insert(source,0);
        while(!pq.isEmpty()){
            relax(ewg,pq.delMin());
        }
        
        
        if(distTo[sink]<Integer.MAX_VALUE){System.out.println(distTo[sink]);}
        else {System.out.println(-1);}
        
    }
}