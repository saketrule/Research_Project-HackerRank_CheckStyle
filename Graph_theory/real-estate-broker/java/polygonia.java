import java.io.*;
import java.util.*;
public class e {
public static void main(String[] args) throws IOException {
 input.init(System.in);
 PrintWriter out = new PrintWriter(System.out);
 int n = input.nextInt(), m = input.nextInt();
 int[] as = new int[n], ps = new int[n];
 for(int i = 0; i<n; i++)
 {
  as[i] = input.nextInt();
  ps[i] = input.nextInt();
 }
 int[] xs = new int[m], ys = new int[m];
 for(int i = 0; i<m; i++)
 {
  xs[i] = input.nextInt();
  ys[i] = input.nextInt();
 }
 TidalFlow tf = new TidalFlow(n+m);
 for(int i = 0; i<n; i++) tf.add(tf.s, i, 1);
 for(int i = 0; i<m; i++) tf.add(i+n, tf.t, 1);
 for(int i = 0; i<n; i++)
  for(int j = 0; j<m; j++)
  {
   if(xs[j] > as[i] && ys[j] <= ps[i])
    tf.add(i, j+n, 1);
  }
 out.println(tf.getFlow());
 out.close();
}
static class TidalFlow {
 ArrayDeque<Edge> stk = new ArrayDeque<Edge>();
 int N, s, t, oo = 987654321, fptr, bptr;
 ArrayList<Edge>[] adj;
 int[] q, dist, pool;

 @SuppressWarnings("unchecked")
 TidalFlow(int NN) {
  N=(t=(s=NN)+1)+1;
  adj = new ArrayList[N];
  for(int i = 0; i < N; adj[i++] = new ArrayList<Edge>());
  dist = new int[N];
  pool = new int[N];
  q = new int[N];
 }
 void add(int i, int j, int cap) {
  Edge fwd = new Edge(i, j, cap, 0);
  Edge rev = new Edge(j, i, 0, 0);
  adj[i].add(rev.rev=fwd);
  adj[j].add(fwd.rev=rev);
 }
 int augment() {
  Arrays.fill(dist, Integer.MAX_VALUE);
  pool[t] = dist[s] = fptr = bptr = 0;
  pool[q[bptr++] = s] = oo;
  while(bptr > fptr && q[fptr] != t)
   for(Edge e : adj[q[fptr++]]) {
    if(dist[e.i] < dist[e.j])
     pool[e.j] += e.carry = Math.min(e.cap - e.flow, pool[e.i]);
    if(dist[e.i] + 1 < dist[e.j] && e.cap > e.flow)
     dist[q[bptr++] = e.j] = dist[e.i] + 1;
   }
  if(pool[t] == 0) return 0;
  Arrays.fill(pool, fptr = bptr = 0);
  pool[q[bptr++] = t] = oo;
  while(bptr > fptr) 
   for(Edge e : adj[q[fptr++]]) {
    if(pool[e.i] == 0) break;
    int f = e.rev.carry = Math.min(pool[e.i], e.rev.carry);
    if(dist[e.i] > dist[e.j] && f != 0) {
     if(pool[e.j] == 0) q[bptr++] = e.j;
     pool[e.i] -= f;
     pool[e.j] += f;
     stk.push(e.rev);
    }
   }
  int res = pool[s];
  Arrays.fill(pool, 0);
  pool[s] = res;
  while(stk.size() > 0) {
   Edge e = stk.pop();
   int f = Math.min(e.carry, pool[e.i]);
   pool[e.i] -= f;
   pool[e.j] += f;
   e.flow += f;
   e.rev.flow -= f;
  }
  return res;
 }
 int getFlow() {
  int res = 0, f = 1;
  while(f != 0)
   res += f = augment();
  return res;
 }
 class Edge {
  int i, j, cap, flow, carry;
  Edge rev;
  Edge(int ii, int jj, int cc, int ff) {
   i=ii; j=jj; cap=cc; flow=ff;
  }
 }
}
public static class input {
 static BufferedReader reader;
 static StringTokenizer tokenizer;

 static void init(InputStream input) {
  reader = new BufferedReader(new InputStreamReader(input));
  tokenizer = new StringTokenizer("");
 }

 static String next() throws IOException {
  while (!tokenizer.hasMoreTokens())
   tokenizer = new StringTokenizer(reader.readLine());
  return tokenizer.nextToken();
 }

 static int nextInt() throws IOException {
  return Integer.parseInt(next());
 }

 static double nextDouble() throws IOException {
  return Double.parseDouble(next());
 }

 static long nextLong() throws IOException {
  return Long.parseLong(next());
 }
}
}