import java.io.*;
import java.util.*;
 
public class Main
{
   public static void main(String[] args) throws IOException
   {
      new Main().run();
   }
 
   StreamTokenizer in;
   PrintWriter out;
 
   int nextInt() throws IOException
   {
      in.nextToken();
      return (int)in.nval;
   }
   
   long nextLong() throws IOException
   {
    in.nextToken();
    return (long) in.nval;
   }
   
   double nextDouble() throws IOException
   {
    in.nextToken();
    return in.nval;
   }
   
   String nextString() throws IOException
   {
    in.nextToken();
    return in.sval;
   }
 
   void run() throws IOException
   {
      in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
      out = new PrintWriter(new OutputStreamWriter(System.out));
      solve();
      out.flush();
   }
   
   int n, m, u, v;
   ArrayList<Integer>[] adj;
   int[] distance;
   
   void bfs(int s)
   {
    Queue<Integer> queue = new LinkedList<Integer>();
    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> q2 = new LinkedList<Integer>();
    queue.add(s);
       for(int i = 1; i <= n; i++)
       {
           if(i != s) q1.add(i);
       }
    while( ! queue.isEmpty() )
    {
     u = queue.poll();
     while(! q1.isEmpty())
           {
               v = q1.poll();
               if(Collections.binarySearch(adj[u], v) >= 0) {q2.add(v);}
               else { if(distance[v] == 0) { distance[v] = 1 + distance[u]; queue.add(v);} }
           }
     while(! q2.isEmpty() ) { q1.add(q2.poll());}
    }
   }
   
   void solve() throws IOException
   {
    for(int t = nextInt(); t > 0; t--)
    {
     n = nextInt(); m = nextInt();
     adj = new ArrayList[n + 1];
     distance = new int[n + 1];
     for(int i = 1; i <= n; i++) adj[i] = new ArrayList<Integer>();
     for(int i = 1; i <= m; i++)
     {
      u = nextInt(); v = nextInt();
      adj[u].add(v);
      adj[v].add(u);
     }
           for(int i = 1; i <= n; i++) Collections.sort(adj[i]);
     int s = nextInt();
     bfs(s);
     for(int i = 1; i <= n; i++)
     {
      if(i != s) out.print(distance[i] + " ");
     }
     out.println();
    }
   }
}