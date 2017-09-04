/* Andy Rock
 * June 25, 2016
 * 
 * World CodeSprint #4
 */

import java.io.*;
import java.math.*;
import java.util.*;

public class Main2
{
 public static void main(String[] args) throws IOException
 {
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st = new StringTokenizer(in.readLine());

  int N = Integer.parseInt(st.nextToken());
  int M = Integer.parseInt(st.nextToken());

  int[][] graph = new int[M][2];
  for(int i=0;i<M;i++)
  {
   st = new StringTokenizer(in.readLine());
   int A = Integer.parseInt(st.nextToken()) - 1;
   int B = Integer.parseInt(st.nextToken()) - 1;
   int C = Integer.parseInt(st.nextToken());

   graph[C][0] = A;
   graph[C][1] = B;
  }

  int[] set = new int[N];
  for(int i=0;i<N;i++)
   set[i] = i;

  List<List<Integer>> tree = new ArrayList<List<Integer>>(), weight = new ArrayList<List<Integer>>();
  for(int i=0;i<N;i++)
  {
   tree.add(new ArrayList<Integer>());
   weight.add(new ArrayList<Integer>());
  }

  for(int i=0;i<M;i++)
   if(find(graph[i][0], set) != find(graph[i][1], set))
   {
    union(graph[i][0], graph[i][1], set);

    tree.get(graph[i][0]).add(graph[i][1]);
    tree.get(graph[i][1]).add(graph[i][0]);
    weight.get(graph[i][0]).add(i);
    weight.get(graph[i][1]).add(i);
   }

  int[] size = new int[N];
  size[0] = fill(0, tree, size);

  BigInteger ans = BigInteger.ZERO, Nb = BigInteger.valueOf(N);
  for(int i=0;i<N;i++)
   for(int j=0;j<tree.get(i).size();j++)
   {
    BigInteger a = BigInteger.valueOf(Math.min(size[i], size[tree.get(i).get(j)]));

    ans = ans.add(a.multiply(Nb.subtract(a)).shiftLeft(weight.get(i).get(j)));
   }

  System.out.println(ans.shiftRight(1).toString(2));
 }

 static int fill(int pos, List<List<Integer>> graph, int[] size)
 {
  size[pos] = 1;
  for(int next : graph.get(pos))
   if(size[next] == 0)
    size[pos] += fill(next, graph, size);

  return size[pos];
 }

 static void union(int u, int v, int[] set)
 {
  set[find(u, set)] = find(v, set);
 }

 static int find(int u, int[] set)
 {
  if(set[u] == u)
   return u;
  return set[u] = find(set[u], set);
 }
}