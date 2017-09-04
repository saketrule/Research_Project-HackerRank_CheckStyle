import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Solution {
          
      static int[] connected =  new int[ 100001 ];
      
    public static void main(String[] args) {
          int N , M;
        Scanner scan = new Scanner(System.in);
         N = scan.nextInt();
         M = scan.nextInt();
         for(int i = 0 ; i <= N ; ++i )
               connected[i] = i;
          
         ArrayList< Pair > array =  new ArrayList< Pair >();   
         for(int i = 0 ; i < M ; ++i ){
             int u , v , cost;
             u = scan.nextInt();
             v = scan.nextInt();
             cost = scan.nextInt();
             Pair obj = new Pair( 0 , 0 , 0 );
             obj.setA(cost);
             obj.setB(u);
             obj.setC(v);
             array.add(obj);
         }
         int[] arr = new int[ 200001 ];
           
        for(int i = 0 ; i < M ; ++i )
            arr[array.get(i).getA()] = i + 1;
         
        int[][] Ar = new int[M][3];
        int sz = 0;
         for(int i = 0 ; i <= 200000 ; ++i ){
              if(arr[i] > 0 ){
                  Ar[sz][0] = i;
                  Ar[sz][1] = array.get(arr[i]-1).getB();
                  Ar[sz][2] = array.get(arr[i]-1).getC();
                  ++sz;
              }
         }
          int id = 0;
          int[][] newAr = new int[M][3];
          for(int i = 0 ; i < M ; ++i ){
              int c = Ar[i][0];
              int u = Ar[i][1];
              int v = Ar[i][2];
              int X = root(u);
              int Y = root(v);
              if(X != Y ){
                  connected[X] = Y;
                  newAr[id][0] = c;
                  newAr[id][1] = u;
                  newAr[id][2] = v;
                  ++id;
              }
          }
            Graph g = new Graph(N);
          for(int i = 0 ; i < id ; ++i  ){
               g.addEdge( newAr[i][1] , newAr[i][2]);
          }
         g.DFS(1);
        BigInteger b1 , b2 , b3 , b4 , b5;
        b1 = new BigInteger("2");
        b5 = new BigInteger("0");
          for(int i = 0 ; i < id ; ++i ){
              int  cst =  newAr[i][0];
              int u = newAr[i][1];
              int v = newAr[i][2];
              if(g.parent[v] == u ){
                  int X = g.sub[v];
                  int Y = N - X;
                  Long div = (X * Y * 1L);
                  b2 = b1.pow(cst);
                  b3 = new BigInteger(Long.toString(div));
                  b4 = b2.multiply(b3);
                  b5 = b5.add(b4);
             }
              else {
                  int X = g.sub[u];
                  int Y = N - X;
                  Long div = (X * Y * 1L);
                  b2 = b1.pow(cst);
                  b3 = new BigInteger(Long.toString(div));
                  b4 = b2.multiply(b3);
                  b5 = b5.add(b4);
             }
          }
        String s3 = b5.toString(2);
        System.out.println(s3);
        
   }
    public static int root(int node ){
        if(connected[node] == node )
            return node;
          return (connected[node] = root(connected[node]));
    }
    
}
  class  Pair {
      int A;
      int B;
      int C;
       public Pair(int A , int B , int C ){
           this.A = A;
           this.B = B;
           this.C = C;  
       }
      public void setA(int A ){
          this.A = A;
      }
      public void setB(int B){
          this.B = B;
      }
      public void setC(int C){
          this.C = C;
      }
      public int getA(){
          return A;
      }
      public int getB(){
          return B;
      }
      public int getC(){
          return C;
      }
      
  }
class Graph
{
    public static int[] sub ;
    public static int[] parent ;
    private int V;   
    private LinkedList<Integer> adj[];
    
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v + 1];
        sub = new int[v+1];
        parent = new int[v+1];
        for (int i=0; i<= v; ++i){
            adj[i] = new LinkedList();
         sub[i] = 1;
         parent[i] = -1;   
        }
    }
 
    void addEdge(int v, int w)
    {
        adj[v].add(w);  
        adj[w].add(v);
    }
 
    void DFSUtil(int v,boolean visited[] )
    {
        visited[v] = true;
     Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n]){
                parent[n] = v;
                DFSUtil(n, visited);
                sub[v] += sub[n];
            }
        }
    }
    void DFS(int v)
    {
        boolean visited[] = new boolean[V + 1];
 
        DFSUtil(v, visited);
    }
}