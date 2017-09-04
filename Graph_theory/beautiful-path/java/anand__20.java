import java.io.*;
import java.util.*;
public final class feb2
{
    static FastScanner sc=new FastScanner(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out=new PrintWriter(System.out);
 
    @SuppressWarnings("unchecked")
    public static void main(String args[]) throws Exception
    {
  int n=sc.nextInt(),m=sc.nextInt();
  ArrayList<Node>[] a=new ArrayList[n];
  long[] dis=new long[n];
  for(int i=0;i<n;i++)
  {
   a[i]=new ArrayList<Node>();
   dis[i]=Long.MAX_VALUE;
  }
  while(m>0)
  {
   int u=sc.nextInt()-1,v=sc.nextInt()-1;
   long cost=sc.nextLong();
   a[u].add(new Node(v,cost));
   a[v].add(new Node(u,cost));
   m--;
  }
  int st=sc.nextInt()-1,ed=sc.nextInt()-1;
  PriorityQueue<Node> pq=new PriorityQueue<Node>();
  pq.add(new Node(st,0));
  dis[st]=0;
  while(pq.size()>0)
  {
   Node curr=pq.poll();
   for(Node x:a[curr.val])
   {
    if(dis[x.val]>(dis[curr.val]|x.cost))
    {
     dis[x.val]=dis[curr.val]|x.cost;
     pq.add(new Node(x.val,dis[x.val]));
    }
   }
  }
  out.println((dis[ed]<Long.MAX_VALUE)?dis[ed]:"-1");
  out.close();
    }
}
class Node implements Comparable<Node>
{
 int val;
 long cost;
 public Node(int val,long cost)
 {
  this.val=val;
  this.cost=cost;
 }
 public int compareTo(Node x)
 {
  return Long.compare(this.cost,x.cost);
 }
}
class FastScanner
{
    BufferedReader in;
    StringTokenizer st;

    public FastScanner(BufferedReader in) {
        this.in = in;
    }
 
    public String nextToken() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine());
        }
        return st.nextToken();
    }
 
 public String next() throws Exception {
  return nextToken().toString();
 }
 
    public int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() throws Exception {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() throws Exception {
        return Double.parseDouble(nextToken());
    }
}