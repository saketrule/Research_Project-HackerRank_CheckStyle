/* Andy Rock
 * <date>
 * 
 * <problem>
 */

import java.io.*;
import java.math.*;
import java.util.*;

public class Main
{
 static Map<Long, Long> map = new HashMap<Long, Long>();
 static Map<Long, Set<Long>> nap = new HashMap<Long, Set<Long>>();

 public static void main(String[] args) throws IOException
 {
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st = new StringTokenizer(in.readLine());

  int N = Integer.parseInt(st.nextToken());
  int M = Integer.parseInt(st.nextToken());

  st = new StringTokenizer(in.readLine());
  long[] C = new long[N];
  for(int i=0;i<N;i++)
   C[i] = Integer.parseInt(st.nextToken());

  boolean[][] graph = new boolean[N][N];
  while(M --> 0)
  {
   st = new StringTokenizer(in.readLine());
   int A = Integer.parseInt(st.nextToken()) - 1;
   int B = Integer.parseInt(st.nextToken()) - 1;

   graph[A][B] = graph[B][A] = true;
  }

  long[] ans = fast(N, M, C, graph);
  System.out.println(ans[0]+" "+ans[1]);
  /*while(true)
  {
   map = new HashMap<Long, Long>();
   nap = new HashMap<Long, Set<Long>>();
   N = (int)(1+Math.random()*2);
   M = N;
   C = new long[N];
   for(int i=0;i<N;i++)
    C[i] = (int)(Math.random()*100);
   graph = new boolean[N][N];
   while(M --> 0)
   {
    int A = (int)(Math.random()*N);
    int B = (int)(Math.random()*N);
    if(A != B)
     graph[A][B] = graph[B][A] = true;
   }

   long[] ans1 = fast(N, M, C, graph);
   long[] ans2 = slow(N, M, C, graph);

   if(!Arrays.equals(ans1, ans2))
   {
    System.out.println(N+" "+M);
    System.out.println(Arrays.toString(C));
    for(int i=0;i<N;i++)
     for(int j=i;j<N;j++)
      if(graph[i][j])
       System.out.println(i+" "+j);
    System.out.println(Arrays.toString(ans1));
    System.out.println(Arrays.toString(ans2));
    return;
   }
   else
    System.out.println("woot");
  }*/
 }

 static long[] slow(int N, int M, long[] C, boolean[][] graph)
 {
  long[] ans = new long[2];
  for(int i=0;i<(1 << N);i++)
  {
   long sum = 0;
   for(int j=0;j<N;j++)
    if((i & (1 << j)) != 0)
     sum += C[j];

   boolean good = true;
   for(int j=0;j<N;j++)
    for(int k=0;k<N;k++)
     if((i & (1 << j)) * (i & (1 << k)) != 0)
      if(graph[j][k])
       good = false;

   if(good)
    if(sum > ans[0])
    {
     System.out.println(i);
     ans[0] = sum;
     ans[1] = 1;
    }
    else if(sum == ans[0])
     ans[1]++;
  }

  return ans;
 }

 static long[] fast(int N, int M, long[] C, boolean[][] graph)
 {
  int[] comps = new int[N];
  int pos = 0;
  for(int i=0;i<N;i++)
   if(comps[i] == 0)
    fill(i, graph, comps, ++pos);

  long[] size = new long[pos];
  for(int i=0;i<N;i++)
   size[comps[i]-1] += (1L << i);

  long ans = 0, num = 1;
  for(int i=0;i<pos;i++)
  {
   solve(size[i], size[i], graph, C);
   ans += map.get(size[i]);
   num *= nap.get(size[i]).size();
  }

  int[] freq = new int[1 << 8];
  for(int i=0;i<N;i++)
   freq[comps[i]]++;
  for(int i=0;i<N;i++)
   if(C[i] == 0 && freq[comps[i]] == 1)
    num *= 2L;
  return new long[]{ans, num};
 }

 static void fill(int cur, boolean[][] graph, int[] set, int A)
 {
  set[cur] = A;
  for(int i=0;i<graph.length;i++)
   if(graph[cur][i] && set[i] == 0)
    fill(i, graph, set, A);
 }

 static void solve(long pos, long take, boolean[][] graph, long[] C)
 {
  if(pos == 0)
  {
   map.put(pos, 0L);
   Set<Long> set = new HashSet<Long>();
   set.add(pos);
   nap.put(pos, set);
   return;
  }

  if(map.get(pos) != null)
   return;

  long ans = 0L;
  for(int i=0;i<graph.length;i++)
   if((pos & (1L << i)) != 0)
   {
    long next = pos - (1L << i);
    long text = take - (1L << i);
    for(int j=0;j<graph.length;j++)
     if(graph[i][j])
      next &= ~(1L << j);

    solve(next, text, graph, C);
    long score = C[i] + map.get(next).longValue();
    Set<Long> set = nap.get(next);

    if(score > ans)
    {
     ans = score;
     Set<Long> nset = new HashSet<Long>();
     for(Long v : set)
      nset.add(v.longValue() | (1L << i));
     nap.put(pos, nset);
    }
    else if(score == ans)
    {
     if(nap.get(pos) == null)
      nap.put(pos, new HashSet<Long>());
     for(Long v : set)
      nap.get(pos).add(v.longValue() | (1L << i));
    }
   }

  map.put(pos, ans);
 }
}