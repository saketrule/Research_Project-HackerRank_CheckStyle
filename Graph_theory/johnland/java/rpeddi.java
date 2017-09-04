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
 
}

class UnionFind{
  private int id[],sz[];
  private int count;
  
  public UnionFind(int N){
   count = N;
   id = new int[N];sz = new int[N];
   for(int i=0;i<N;i++){
    id[i] = i;sz[i] =1;
   }
  }
  
  public int count(){
   return count;
  }
  
  public boolean connected(int p,int q){
   return find(p)==find(q);
  }
  
  public int find(int n){
            if(id[n]==n) return n;
   return id[n]= find(id[n]);
  }
  
  public void union(int p,int q){
   int pRoot = find(p);
   int qRoot = find(q);
   
   if(pRoot==qRoot) return;
   
   if(sz[pRoot]>sz[qRoot]) { id[qRoot] = pRoot; sz[pRoot]+=sz[qRoot];}
   else { id[pRoot]= qRoot; sz[qRoot]+=sz[pRoot];}
   
   count--;
  } 
 }

class FastReader{
    BufferedReader br;
    StringTokenizer st;

    public FastReader()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next()
    {
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException  e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt()
    {
        return Integer.parseInt(next());
    }

    long nextLong()
    {
        return Long.parseLong(next());
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }
    
}

public class Solution {
    
    public static boolean[] marked;
    public static BigInteger ans = new BigInteger("0"),one = new BigInteger("1"),two = new BigInteger("2");
    public static boolean set =false;
    public static int tot ;
    
    public static int prevWeight=0,currentWeight=0;
    public static BigInteger prevPow = new BigInteger("1"),currentPow=one;
    
    public static int dfs(EdgeWeightedGraph G, int v, int count){
        marked[v] = true; count++;
        int after=0;
        for(Edge ed: G.adj(v)){
            int w = ed.other(v);
            
            int versafter=0;
            if(!marked[w]) {
                versafter= dfs(G,w,count);
                int num = (versafter+1)*(tot-(versafter+1));
                if(!set){
                    set = true;
                    currentWeight = ed.weight();
                    currentPow = two.pow(currentWeight);                    
                } else if(set) {                    
                    currentWeight = ed.weight();
                    int shift = currentWeight - prevWeight;
                    currentPow = prevPow.shiftLeft(shift);
                }
                ans = ans.add((currentPow).multiply(new BigInteger(Integer.toString(num))));
                prevPow = currentPow; prevWeight = currentWeight;
                after+= versafter+1;
            }
        }
        
        return after;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();
        int V = scan.nextInt(),E = scan.nextInt();
        
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(11, new Comparator<Edge>(){
            @Override
            public int compare(Edge ed1, Edge ed2){
                if(ed1.weight()>ed2.weight()) {return 1;}
                else if(ed1.weight()<ed2.weight()) {return -1;}
                else {return 0;}                    
            }
        });
        PriorityQueue<Edge> mst = new PriorityQueue<Edge>(11, new Comparator<Edge>(){
            @Override
            public int compare(Edge ed1, Edge ed2){
                if(ed1.weight()>ed2.weight()) {return 1;}
                else if(ed1.weight()<ed2.weight()) {return -1;}
                else {return 0;}                    
            }
        });
        
        
        for(int i=0; i<E; i++){
            int v = scan.nextInt(),w = scan.nextInt(),weight = scan.nextInt();
            Edge ed = new Edge(v,w,weight);
            pq.add(ed);
        }
        
        UnionFind uf = new UnionFind(V+1);
        
        while(!pq.isEmpty()){
            Edge ed = pq.poll();
            int v = ed.either(),w = ed.other(v);
            
            if(uf.connected(v,w)) continue;
            
            mst.add(ed);
            uf.union(v,w);            
        }
        
        
        EdgeWeightedGraph G = new EdgeWeightedGraph(V+1);
        
        for(Edge ed:mst){
            int weight = ed.weight();
            G.addEdge(ed);
        }
        
        tot = V;
        marked = new boolean[V+1];
        dfs(G,1,0);
        
        System.out.println(ans.toString(2));
        
    }
}