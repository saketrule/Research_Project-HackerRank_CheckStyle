import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {

 private static final int MODULO_BY = 1000000000;
 private static final int FROM = 0;
 private static final int TO = 1;
 private static final int RES = 2;
 private static final int EDGE_SIZE = 3;
 
 private static final int UNDEFINED = 0;
 private static final int ORDINAL = 0;
 private static final int LOW_ORDINAL = 1;
 private static final int FIRST_EDGE = 2;
 private static final int IN_STACK = 3;
 private static final int INDEG = 4;
 private static final int VERTICEX_INFO_SIZE = 5;
 
 @SuppressWarnings("deprecation")
 private static int[] getIntArray(DataInputStream in, int[] ret) {
  try {
   String[] line = in.readLine().split(" ");
   if (ret == null)
    ret = new int[line.length];
   for (int i = 0; i < 2; i++)
    ret[i] = Integer.parseInt(line[i]);
   return ret;
  } catch (IOException e) {
   e.printStackTrace();
   return null;
  }
 }

 private static int s_token = 0;
 private static int strongconnect(int[][] vertices, int[][] edges, int[] stack, int[] recursion_stack)
 {
  int loopcount = 0;
  int index = 1;
  int tok = ++s_token;
  OUTER:
  while (recursion_stack[recursion_stack[0]] > 0)
  {
   int vindex = recursion_stack[recursion_stack[0]];
   int[] v = vertices[vindex];
   if (v[ORDINAL] == UNDEFINED)
   {
    v[LOW_ORDINAL] = v[ORDINAL] = index++;
    stack[(stack[0]++)+1] = vindex;
    v[IN_STACK] = 1;
   }
   for (int i = v[FIRST_EDGE]; i < edges.length && edges[i][FROM] == vindex; i++)
   {
    if (edges[i][RES] == tok) continue;
    edges[i][RES] = tok;
    int windex = edges[i][TO];
    int[] w = vertices[windex];
    ++w[INDEG];
    if (w[ORDINAL] == UNDEFINED && w[IN_STACK] != 1)
    {
     recursion_stack[++recursion_stack[0]] = edges[i][TO];
     continue OUTER;
    }
    else if (w[IN_STACK] == 1)
    {
     v[LOW_ORDINAL] = Math.min(v[LOW_ORDINAL], w[ORDINAL]);
    }
   }
   if ((v[LOW_ORDINAL] == v[ORDINAL]))
   {
    int loopsize = 0;
    for (int widx=stack[--stack[0]+1]; stack[0]>=0; widx = stack[--stack[0]+1])
    {
     int[] w = vertices[widx];
     w[IN_STACK] = 0;
     ++loopsize;
     if (widx==vindex) break;
    }
    if (loopsize > 1)
     ++loopcount;
   }
   if (--recursion_stack[0] != 0)
   {
    int pindex = recursion_stack[recursion_stack[0]];
    int[] pv = vertices[pindex];
    pv[LOW_ORDINAL] = Math.min(pv[LOW_ORDINAL], v[LOW_ORDINAL]);
   }
  }
  return loopcount;
 }
 
 private static void topo(int[][] vertices, int[][] edges, int[] queue, int qsize)
 {
  int curr = 0;
  int tok = ++s_token;
  while (curr != qsize)
  {
   int vindex = queue[curr++];
   curr%=queue.length;
   int[] v = vertices[vindex];
   for (int i = v[FIRST_EDGE]; i < edges.length && edges[i][FROM] == vindex; i++)
   {
    if (edges[i][RES] == tok) continue;
    edges[i][RES] = tok;
    int windex = edges[i][TO];
    int[] w = vertices[windex];
    w[ORDINAL] += v[ORDINAL];
    w[ORDINAL] %= MODULO_BY;
    ++w[LOW_ORDINAL];
    if ((w[LOW_ORDINAL] == w[INDEG]))
    {
     queue[qsize++] = edges[i][TO];
     qsize%=queue.length;
    }
   }
  }
 }

 public static void main(String[] args)
 {
  
  DataInputStream in = new DataInputStream(new BufferedInputStream(System.in));
  PrintStream out = new PrintStream(new BufferedOutputStream( System.out));

  int[] numStrings = getIntArray(in, null);
//  @SuppressWarnings("unused")
//  long strt = System.currentTimeMillis();
  int[][] vertices = new int[numStrings[0]+1][VERTICEX_INFO_SIZE];
  int[][] edges = new int[numStrings[1]][EDGE_SIZE];
  int res = 0;
  for (int s = 0; s < edges.length; s++) {
   getIntArray(in, edges[s]);
   res++;
  }
  Arrays.sort(edges, new Comparator<int[]>(){
   public int compare(int[] arg0, int[] arg1) {
    int ret = arg0[FROM] - arg1[FROM];
    if (ret != 0) return ret;
    return arg0[TO] - arg1[TO];
   }
  });
  for (int i = 0, current = -1; i < edges.length; i++) {
   if (current != edges[i][FROM])
    vertices[edges[i][FROM]][FIRST_EDGE] = i;
   current = edges[i][FROM];
  }
  
  int[] stack = new int[numStrings[0]+1];
  int[] recursion_stack = new int[numStrings[0]+1];
  
  recursion_stack[(recursion_stack[0]++)+1] = 1;
  //the res would contain the number of cycles, but these
  //cycles could be extraneous to the path from source to sink.
  res = strongconnect(vertices, edges, stack, recursion_stack);
  
  if (vertices[vertices.length-1][ORDINAL] == UNDEFINED)
  {
   out.println("" + 0);
  }
  else
  {
   for (int i = 0; i < vertices.length; i++)
    vertices[i][ORDINAL] = vertices[i][LOW_ORDINAL] = 0;
   vertices[1][ORDINAL] = 1;
   recursion_stack[0]=1;
   topo(vertices, edges, recursion_stack, 1);
   res = vertices[vertices.length-1][ORDINAL];
   if (res == 0)
    out.println("INFINITE PATHS");
   else
    out.println(""+res);
  }  
  out.flush();
//  System.out.println("delta : " + (System.currentTimeMillis()-strt));
//  out.flush();
 }
}