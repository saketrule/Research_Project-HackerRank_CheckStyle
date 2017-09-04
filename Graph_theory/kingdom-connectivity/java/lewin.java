import java.io.*;
import java.util.*;

public class Solution {
 private static Reader in;
 private static PrintWriter out;
 
 private static long mod =  1000000000;
 private static int [] eadj, eprev, elast;
 private static int eidx, N, M;
 
 private static void addEdge (int a, int b) {
  eadj [eidx] = b; eprev [eidx] = elast [a]; elast [a] = eidx++;
 }
 
 private static boolean [] vis;
 private static boolean reachable (int node) {
  if (vis [node]) return false;
  if (node == N - 1) return true;
  vis [node] = true;
  for (int e = elast [node]; e != -1; e = eprev [e]) {
   if (reachable (eadj [e]))
    return true;
  }
  return false;
 }
 
 public static void main (String [] args) throws IOException {
  in = new Reader();
  out = new PrintWriter (System.out, true);
  
  N = in.nextInt();
  M = in.nextInt();
  
  elast = new int [N];
  eprev = new int [M];
  eadj = new int [M];
  eidx = 0;
  Arrays.fill (elast, -1);
  
  for (int i = 0; i < M; i++) {
   int a = in.nextInt(), b = in.nextInt();
   addEdge (a - 1, b - 1);
  }
  
  vis = new boolean [N];
  if (!reachable (0)) {
   out.println (0);
   out.close();
   System.exit(0);
  }
  
  tarjan();
  
  seadj = new int [M];
  selast = new int [gidx];
  seprev = new int [M];
  sindeg = new int [gidx];
  seidx = 0;
  Arrays.fill (selast, -1);
  
  for (int i = 0; i < N; i++)
   for (int e = elast [i]; e != -1; e = eprev [e])
    if (part [i] != part [eadj [e]])
     saddedge (part [i], part [eadj [e]]);
  
  int [] queue = new int [gidx];
  int front = 0, back = 0;
  for (int i = 0; i < gidx; i++)
   if (sindeg [i] == 0)
    queue [back++] = i;
  
  while (front < back) {
   int cur = queue [front++];
   for (int e = selast [cur]; e != -1; e = seprev [e]) 
    if (--sindeg [seadj [e]] == 0)
     queue [back++] = seadj [e];
  }
  
  long [] numways = new long [gidx];
  int [] numcycles = new int [N];
  for (int i = 0; i < gidx; i++)
   numcycles [i] = size [i] > 1 ? 1 : 0;
  boolean [] canReach = new boolean [gidx];
   
  numways [part [N - 1]] = 1;
  canReach [part [N - 1]] = true;
  for (int i = back - 1; i >= 0; i--) {
   int cur = queue [i];
   for (int e = selast [cur]; e != -1; e = seprev [e]) {
    numways [cur] = (numways [cur] + numways [seadj [e]]) % mod;
    canReach [cur] |= canReach [seadj [e]];
    if (canReach [seadj [e]])
     numcycles [cur] += numcycles [seadj [e]];
   }
  }
  
  out.println (numcycles [part [0]] > 0 ? "INFINITE PATHS" : numways [part [0]]);
  out.close();
  System.exit(0);
 }
 
 private static int [] seadj, selast, seprev, sindeg;
 private static int seidx;
 
 private static void saddedge (int a, int b) {
  sindeg [b]++;
  seadj [seidx] = b; seprev [seidx] = selast [a]; selast [a] = seidx++;
 }
 
 private static int [] part, tidx, lowlink, stack, size;
 private static int pidx, sidx, gidx;
 private static boolean [] instack;
 
 private static void tarjan() {
  stack = new int [N];
  tidx = new int [N];
  part = new int [N];
  lowlink = new int [N];
  instack = new boolean [N];
  size = new int [N];
  Arrays.fill (tidx, -1);
  
  pidx = 0; sidx = 0; gidx = 0;
  for (int i = 0; i < N; i++) {
   if (tidx [i] == -1) {
    strongconnect (i);
   }
  }
 }
 
 private static void strongconnect (int node) {
  tidx [node] = pidx;
  lowlink [node] = pidx;
  pidx++;
  stack [sidx++] = node;
  instack [node] = true;
  
  for (int e = elast [node]; e != -1; e = eprev [e]) {
   if (tidx [eadj [e]] == -1) {
    strongconnect (eadj [e]);
    if (lowlink [eadj [e]] < lowlink [node])
     lowlink [node] = lowlink [eadj [e]];
   } else if (instack [eadj [e]]) {
    if (tidx [eadj [e]] < lowlink [node])
     lowlink [node] = tidx [eadj [e]];
   }
  }
  
  if (lowlink [node] == tidx [node]) {
   int cur, csize = 0;
   do {
    cur = stack [--sidx];
    csize++;
    part [cur] = gidx;
    instack [cur] = false;
   } while (cur != node);
   size [gidx++] = csize; 
  }
 }
 
 static class Reader {
  final private int BUFFER_SIZE = 1 << 16;
  private DataInputStream din;
  private byte [] buffer;
  private int bufferPointer, bytesRead;
  
  public Reader () {
   din = new DataInputStream (System.in);
   buffer = new byte[BUFFER_SIZE];
   bufferPointer = bytesRead = 0;
  }
  
  public int nextInt () throws IOException {
   int ret = 0;
   byte c = read ();
   while (c <= ' ')
    c = read ();
   boolean neg = (c == '-');
   if (neg)
    c = read ();
   do {
    ret = ret * 10 + c - '0';
   } while ((c = read ()) >= '0' && c <= '9');
   if (neg)
    return -ret;
   return ret;
  }
  
  private void fillBuffer () throws IOException {
   bytesRead = din.read (buffer, bufferPointer = 0, BUFFER_SIZE);
   if (bytesRead == -1)
    buffer[0] = -1;
  }
  
  private byte read () throws IOException {
   if (bufferPointer == bytesRead)
    fillBuffer ();
   return buffer[bufferPointer++];
  }
 }
}