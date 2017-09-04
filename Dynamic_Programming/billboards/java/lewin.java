import java.io.*;
import java.util.*;

public class Solution {
 private static Reader in;
 private static PrintWriter out;
 
 public static void main (String [] args) throws IOException {
  in = new Reader();
  out = new PrintWriter (System.out, true);
  
  int N = in.nextInt(), K = in.nextInt();
  min_queue pq = new min_queue (N);
  pq.add (0, 0);
  long sum = 0;
  for (int i = 1; i <= N; i++) {
   int a = in.nextInt();
   sum += a;
   pq.add (i, pq.query (i - K - 1) + a);
  }
  out.println (sum - pq.query(N - K));
  out.close();
  System.exit(0);
 }
 
 static class min_queue {
  public int [] index;
  public long [] value;
  public int front, back, N;
  
  public min_queue (int N) {
   this.N = N;
   index = new int [N + 1];
   value = new long [N + 1];
   front = back = 0;
  }
  
  public void add (int idx, long val) {
   while (front < back && value [back - 1] > val) back--;
   value [back] = val;
   index [back++] = idx;
  }
  
  public long query (int idx) {
   while (front < back && index [front] < idx) front++;
   return front == back ? Integer.MAX_VALUE : value [front];
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